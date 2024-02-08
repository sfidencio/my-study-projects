package br.com.sfidencio.clientes;

import br.com.sfidencio.clientes.model.service.ClienteService;
import br.com.sfidencio.clientes.model.service.PrestacaoServicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"br.com.sfidencio"})
public class Principal implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PrestacaoServicoService prestacaoServicoService;


    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Cliente cliente = new Cliente();
        cliente.setNome("Fulano");
        cliente.setCpf("32365815065");
        this.clienteService.salvar(cliente);

        PrestacaoServico servico = new PrestacaoServico();
        servico.setCliente(cliente);
        servico.setDescricao("Formatacao PC");
        servico.setValor(new BigDecimal("220.98"));
        this.prestacaoServicoService.salvar(servico);
        Cliente clienteEncontrado = this.clienteService.obterPorId(cliente.getId()).get();
        clienteEncontrado.getServicoList().forEach(item -> {
            System.out.println(item.getId() + " - " + item.getDescricao() + " - " + item.getValor());
        });*/

    }

    /*@Bean
    public CommandLineRunner run2(@Autowired ClienteRepository clienteRepository) {
        return args -> {
            Cliente c1 = new Cliente()
                    .builder()
                    .nome("Beltrano").cpf("99999999").dataCadastro(LocalDate.now())
                    .build();
            clienteRepository.save(c1);
        };
    }*/
}
