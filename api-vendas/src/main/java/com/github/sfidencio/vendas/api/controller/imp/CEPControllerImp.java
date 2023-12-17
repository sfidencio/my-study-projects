package com.github.sfidencio.vendas.api.controller.imp;

import com.github.sfidencio.vendas.api.controller.CEPController;
import com.github.sfidencio.vendas.domain.service.CEPService;
import com.github.sfidencio.vendas.infra.integration.dto.CEP;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CEPControllerImp implements CEPController {
    @Autowired
    private CEPService cepService;

    @Override
    public CEP consultar(@PathVariable String cep) {
        return this.cepService.buscar(cep);
    }

}
