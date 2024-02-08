package br.com.sfidencio.clientes.config;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BigDecimalConverter {
    public BigDecimal converter(String num) {
        return new BigDecimal(num.replace(".", "").replace(",", "."));
    }
}
