package com.github.sfidencio.vendas.infra.integration;

import com.github.sfidencio.vendas.domain.service.CEPService;
import com.github.sfidencio.vendas.infra.integration.dto.CEP;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CEPServiceImp implements CEPService {

    private final RestTemplate restTemplate;

    public CEPServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public CEP buscar(String cep) {
        //https://viacep.com.br/ws/01001000/json/
        var retorno = restTemplate.getForObject("https://viacep.com.br/ws/{cep}/json/", CEP.class, cep);
        return retorno;
    }
}
