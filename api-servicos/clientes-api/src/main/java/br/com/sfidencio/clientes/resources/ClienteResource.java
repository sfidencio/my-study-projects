package br.com.sfidencio.clientes.resources;

import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.ServicoFacade;
import br.com.sfidencio.clientes.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/clientes")
//@CrossOrigin(origins = "http://localhost:4200") //Requisicoes vinda do angular!
public class ClienteResource {
    @Autowired
    private ServicoFacade facade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        try {
            return this.facade.salvarCliente(cliente);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    /*@PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente atualizar(@PathVariable UUID id, @Valid @RequestBody Cliente cliente) {
        this.clienteService.obterPorId(id).map(
                clienteEncontrado -> {
                    clienteEncontrado.setNome(cliente.getNome());
                    clienteEncontrado.setCpf(cliente.getCpf());
                    clienteEncontrado.setId(id);
                    this.clienteService.salvar(clienteEncontrado);
                    return this.clienteService.obterPorId(id);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe: " + id.toString()));
        return new Cliente(); //Se nada acontecer, retorna cliente vazio.
    }*/

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> obterTodos() {
        return this.facade.obterTodosClientes().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao buscar clientes"));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente obterPorId(@PathVariable UUID id) {
        return this.facade.obterClientePorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao buscar cliente: " + id.toString()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable UUID id) {
        this.facade.obterClientePorId(id).map(
                cliente -> {
                    this.facade.excluirClientePorId(cliente.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe: " + id.toString()));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable UUID id, @RequestBody Cliente clienteAtualizado) {
        this.facade.obterClientePorId(id).map(
                cliente -> {
                    clienteAtualizado.setId(cliente.getId());
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    try {
                        return this.facade.salvarCliente(clienteAtualizado);
                    } catch (BusinessException e) {
                        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
                    }
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao buscar cliente: " + id.toString()));
    }
}
