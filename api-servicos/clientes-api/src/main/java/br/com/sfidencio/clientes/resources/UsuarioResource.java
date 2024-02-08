package br.com.sfidencio.clientes.resources;

import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.ServicoFacade;
import br.com.sfidencio.clientes.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/usuarios")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200") //Requisicoes vinda do angular!
public class UsuarioResource {
    @Autowired
    private ServicoFacade facade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario) {
        try {
            return this.facade.salvarUsuario(usuario);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
