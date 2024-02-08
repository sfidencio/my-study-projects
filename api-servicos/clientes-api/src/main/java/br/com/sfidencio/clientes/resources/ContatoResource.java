package br.com.sfidencio.clientes.resources;

import br.com.sfidencio.clientes.model.entity.Contato;
import br.com.sfidencio.clientes.model.repository.ContatoRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/contatos")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200") //Requisicoes vinda do angular!
public class ContatoResource {
    @Autowired
    private ContatoRepository repository; //Aqui acesso repository de proposito, nao passarei pela Facade/Service

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Contato salvar(@Valid @RequestBody Contato contato) {
        contato.setFoto(null);
        return this.repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@Valid @PathVariable UUID id) {
        this.repository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@Valid @RequestBody Contato contato, @PathVariable UUID id) {
        this.repository.findById(id).map(
                item -> {
                    item.setNome(contato.getNome());
                    item.setEmail(contato.getEmail());
                    item.setFavorito(contato.getFavorito());
                    item.setFoto(null);
                    this.repository.save(item);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado!"));
    }


    /*@GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> obterTodosContatos() {
        return this.repository.findAll();
    }*/

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<Contato> obterTodosContatos(@RequestParam("pagina") String pagina,
                                            @RequestParam("tamanho") String tamanho) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pagina), Integer.parseInt(tamanho), sort);
        return this.repository.findAll(pageRequest);
    }

    @PatchMapping("{id}/favorito")
    @ResponseStatus(HttpStatus.OK)
    public void favoritarContato(@PathVariable("id") UUID id) {
        Optional<Contato> contato = this.repository.findById(id);
        contato.ifPresent(item -> {
            Boolean favorito = item.getFavorito() == Boolean.TRUE;
            item.setFavorito(!favorito);
            this.repository.save(item);
        });
    }

    @PatchMapping("{id}/foto")
    @ResponseStatus(HttpStatus.OK)
    public byte[] adicionarContato(@PathVariable("id") UUID id, @RequestParam("foto") Part arquivo) {
        Optional<Contato> contato = this.repository.findById(id);
        contato.map(item -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                item.setFoto(bytes);
                this.repository.save(item);
                is.close();
                return bytes;
            } catch (IOException ex) {
                return null;
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado!"));
        return this.repository.findById(id).get().getFoto();
    }
}
