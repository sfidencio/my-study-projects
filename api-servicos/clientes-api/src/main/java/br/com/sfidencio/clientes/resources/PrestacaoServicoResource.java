package br.com.sfidencio.clientes.resources;

import br.com.sfidencio.clientes.model.ServicoFacade;
import br.com.sfidencio.clientes.model.entity.Cliente;
import br.com.sfidencio.clientes.model.entity.PrestacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/prestacao-servicos")
//@CrossOrigin(origins = "http://localhost:4200") //Requisicoes vinda do angular!
public class PrestacaoServicoResource {
    @Autowired
    private ServicoFacade servicoFacade;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PrestacaoServico salvar(@Valid @RequestBody PrestacaoServico prestacaoServico) {

        //Usar abaixo somente se a data vier no formato abaixo, caso contrario nao prcisar fazer casting
        //String dataFormatoString = "12/02/2001"
        //LocalDate localDate = LocalDate.parse(dataFormatoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente clienteEncontrado = this.servicoFacade
                .obterClientePorId(prestacaoServico.getCliente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

        return this.servicoFacade.salvarPrestacaoServico(prestacaoServico);
    }

    //https://www.codementor.io/@pritishdeshpande1/entity-to-dto-conversion-1py2c8w4bh
    //https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PrestacaoServico> obterTodos() {
        return this.servicoFacade
                .obterTodasPrestacoesServicos()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clientes não encontrados!"));
    }

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<PrestacaoServico> obterPrestacaoServicosPorClienteEMes(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                                       @RequestParam(value = "mes") Integer mes) {
        return this.servicoFacade
                .obterPrestacaoServicosPorClienteEMes("%" + nome + "%", mes)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clientes não encontrados!"));
    }

}
