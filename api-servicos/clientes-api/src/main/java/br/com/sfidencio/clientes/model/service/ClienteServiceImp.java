package br.com.sfidencio.clientes.model.service;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.sfidencio.clientes.exceptions.BusinessException;
import br.com.sfidencio.clientes.model.entity.Cliente;
import br.com.sfidencio.clientes.model.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteServiceImp implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente salvar(Cliente cliente) throws BusinessException {
        CPFFormatter formatter = new CPFFormatter();
        cliente.setCpf(formatter.unformat(cliente.getCpf())); //guardando cpf sem formatacao
        if (Objects.isNull(cliente.getId()))
            if (this.clienteRepository.existsByCpf(cliente.getCpf()))
                throw new BusinessException("Cliente " + cliente.getNome() + " já existe!");
               /*Optional<Cliente> clienteEncontrado = this.clienteRepository.findByCpf(cliente.getCpf());
        if (clienteEncontrado.isPresent() && Objects.isNull(cliente.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já existe: " + clienteEncontrado.get().toString());*/
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Optional<List<Cliente>> obterTodos() {
        return Optional.ofNullable(this.clienteRepository.findAll());
    }

    @Override
    public Optional<Cliente> obterPorId(UUID id) {
        return this.clienteRepository.findById(id);
    }

    @Override
    public void excluirPorId(UUID id) {
        this.clienteRepository.deleteById(id);
    }

}
