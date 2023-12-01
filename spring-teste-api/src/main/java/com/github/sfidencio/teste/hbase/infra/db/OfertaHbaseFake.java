package com.github.sfidencio.teste.hbase.infra.db;

import com.github.sfidencio.teste.hbase.api.controller.dto.OfertaHbaseRequest;
import com.github.sfidencio.teste.hbase.api.controller.dto.OfertaHbaseResponse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OfertaHbaseFake {

    private final Map<String, Map<String, String>> ofertasMap = new HashMap<>();

    public OfertaHbaseFake() {
        this.buildOfertas();
    }

    public OfertaHbaseResponse getOferta(OfertaHbaseRequest ofertaHbaseRequest) {
        var resultado = this.ofertasMap.get(ofertaHbaseRequest.cpf() + ":" + ofertaHbaseRequest.parceria() + ":" + ofertaHbaseRequest.pontoVenda() + ":" + ofertaHbaseRequest.tipoPontoVenda());
        if (resultado == null)
            return null;
        return new OfertaHbaseResponse(resultado.get("cpf"),
                resultado.get("dataAnalise"),
                resultado.get("dataAnaliseOnline"),
                resultado.get("limiteCredito"),
                resultado.get("status"),
                resultado.get("contratado"),
                resultado.get("resultadoAnalise"),
                resultado.get("tipoCliente"));
    }


    private void buildOfertas() {
        if (this.ofertasMap.isEmpty()) {
            //Massa1
            this.ofertasMap.put("12345678901:911:382:1", Map.of("cpf", "12345678901",
                    "dataAnalise", "2020-01-01",
                    "dataAnaliseOnline", "2020-01-01 10:00:00",
                    "limiteCredito", "1000.00",
                    "status", "ELEGIVEL",
                    "contratado", "NAO",
                    "resultadoAnalise", "APROVADO",
                    "tipoCliente", "PADRAO"));

            //Massa2
            this.ofertasMap.put("12345678902:910:381:2", Map.of("cpf", "12345678902",
                    "dataAnalise", "2021-01-01",
                    "dataAnaliseOnline", "2021-01-01 10:00:00",
                    "limiteCredito", "1200.00",
                    "status", "INELIGIVEL",
                    "contratado", "NAO",
                    "resultadoAnalise", "APROVADO",
                    "tipoCliente", "PADRAO"));


        }
    }
}