package com.github.sfidencio.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${api-livro.openapi.dev-url}")
    private String devUrl;

    @Value("${api-livro.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL de servidor no ambiente de desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URL de servidor no ambiente de produção");

        Contact contact = new Contact();
        contact.setEmail("sfidencio@gmail.com");
        contact.setName("Sebastiao Fidencio");
        contact.setUrl("https://github.com/sfidencio");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Gerenciador de Livros API")
                .version("1.0")
                .contact(contact)
                .description("Esta api expõe endpoints para gerenciamento de livros")
                .termsOfService("https://sfidencio.github.io/gerenciador-livros-api/")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }}