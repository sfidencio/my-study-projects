package com.github.sfidencio.teste.hbase.api.controller;

import com.github.sfidencio.teste.hbase.api.controller.dto.OfertaHbaseRequest;
import com.github.sfidencio.teste.hbase.api.controller.dto.OfertaHbaseResponse;
import com.github.sfidencio.teste.hbase.infra.db.OfertaHbaseFake;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hbase/consulta/oferta")
@Log4j2
public class OfertaHbaseController {

    @Autowired
    private OfertaHbaseFake fake;

    @PostMapping
    public ResponseEntity<OfertaHbaseResponse> getOferta(@RequestBody OfertaHbaseRequest request) {
        log.info("Consultando oferta por cpf: {}", request.cpf());
        var response = this.fake.getOferta(request);
        if (response == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(response);
    }
}
