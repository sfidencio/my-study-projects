package com.github.sfidencio;

import com.github.sfidencio.domain.entity.Agencia;
import com.github.sfidencio.domain.entity.Cliente;
import com.github.sfidencio.infra.repository.AgenciaRepository;
import com.github.sfidencio.infra.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
@Log4j2
public class Application {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner execute() {
        return args -> {

            var agencia1 = Agencia.builder()
                    .nome("Agencia 1")
                    .build();
            this.agenciaRepository.save(agencia1);
            log.info("Cadastro de agencia 1 efetuado com sucesso");
            agencia1 = this.agenciaRepository.findByNome("Agencia 1").orElseThrow(() -> new RuntimeException("Agencia não encontrada"));
            log.info("Busca de agencia 1 efetuado com sucesso");
            var agencia2 = Agencia.builder()
                    .nome("Agencia 2")
                    .build();
            this.agenciaRepository.save(agencia2);
            log.info("Cadastro de agencia 2 efetuado com sucesso");
            agencia2 = this.agenciaRepository.findByNome("Agencia 2").orElseThrow(() -> new RuntimeException("Agencia não encontrada"));
            log.info("Busca de agencia 2 efetuado com sucesso");


            //Cadastrando cliente
            var cliente1 = Cliente.builder()
                    .nome("Fulano 1")
                    .cpf("12345678901")
                    .limiteCredito(new BigDecimal("1000.00"))
                    .agencia(agencia1)
                    .build();
            this.clienteRepository.save(cliente1);
            log.info("Cadastro de cliente 1 efetuado com sucesso");

            var cliente2 = Cliente.builder()
                    .nome("Fulano 2")
                    .cpf("12345678901")
                    .limiteCredito(new BigDecimal("1200.00"))
                    .agencia(agencia1)
                    .build();
            this.clienteRepository.save(cliente2);
            log.info("Cadastro de cliente 2 efetuado com sucesso");

            var cliente3 = Cliente.builder()
                    .nome("Fulano 3")
                    .cpf("12345678901")
                    .limiteCredito(new BigDecimal("1400.00"))
                    .agencia(agencia1)
                    .build();
            this.clienteRepository.save(cliente3);
            log.info("Cadastro de cliente 3 efetuado com sucesso");

            var cliente4 = Cliente.builder()
                    .nome("Fulano 4")
                    .cpf("12345678901")
                    .limiteCredito(new BigDecimal("1600.00"))
                    .agencia(agencia2)
                    .build();
            this.clienteRepository.save(cliente4);
            log.info("Cadastro de cliente 4 efetuado com sucesso");

            this.agenciaRepository.findAll().forEach(item -> {
                log.info("ID Agencia: " + item.getId() + " - Nome Agencia: " + item.getNome());
                item.getClientes().forEach(cliente -> {
                    log.info("Cliente -> " + cliente);
                });

            });
            log.info("====================================");
            this.clienteRepository.findAll().forEach(item -> {
                //System.out.println(item);
                log.info("Agencia -> " + item.getAgencia() + " Cliente -> " + item);
            });


            //Atualizando registros
            cliente1 = this.clienteRepository.findById(cliente1.getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            cliente1.setNome("Fulano 1 Atualizado");
            cliente1.setLimiteCredito(new BigDecimal("2000.00"));
            this.clienteRepository.save(cliente1);
            //this.agenciaRepository.deleteById(agencia1.getId());
            this.agenciaRepository.findAll().forEach(item -> {
                log.info("ID Agencia: " + item.getId() + " - Nome Agencia: " + item.getNome());
                item.getClientes().forEach(cliente -> {
                    log.info("Cliente -> " + cliente);
                });
            });

            //Testando propagacao da entidade pai, vamos colocar Cascade.REMOVE  ou Cascade.ALL no relacionamento entre
            //Agencia e Cliente, e vamos remover a agencia 1, pra ve se todos o filhos serao removidos.
//            this.agenciaRepository.deleteById(agencia1.getId());
//            this.agenciaRepository.findAll().forEach(item -> {
//                log.info("ID Agencia: " + item.getId() + " - Nome Agencia: " + item.getNome());
//                item.getClientes().forEach(cliente -> {
//                   log.info("Cliente -> " + cliente);
//                });
//            });

        };
    }
}
