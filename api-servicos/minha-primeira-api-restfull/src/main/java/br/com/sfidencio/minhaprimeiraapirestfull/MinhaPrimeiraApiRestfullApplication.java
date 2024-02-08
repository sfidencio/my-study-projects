package br.com.sfidencio.minhaprimeiraapirestfull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
@Slf4j
//@ComponentScan(basePackages = {"br"})
public class MinhaPrimeiraApiRestfullApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinhaPrimeiraApiRestfullApplication.class, args);
    }

    @Autowired
    //@Qualifier(value = "nomeApp1")
    private MinhaConfiguracao minhaConfiguracao;

//    @Autowired
//    @Qualifier("gato")
//    public Animal animal;

    @Gato
    public Animal animal;

    @Bean(name = "executarBean2")
    public CommandLineRunner executarBean2() {
        return args -> {
            this.animal.fazerBarulho();
        };
    }


    @GetMapping(value = "/nome-app")
    @ResponseStatus(HttpStatus.OK)
    public String getNomeApp() {
        return minhaConfiguracao.getAppNome();
    }

    @GetMapping(value = "/hello-world", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Ola Mundo!";
    }


    @GetMapping(value = "/obtem-produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto obterProduto() {
        Produto p = new Produto()
                .builder()
                .id(UUID.randomUUID())
                .descricao("Arroz Cristal")
                .estoque(298.56)
                .build();
        log.info(p.toString());
        return p;
    }

}
