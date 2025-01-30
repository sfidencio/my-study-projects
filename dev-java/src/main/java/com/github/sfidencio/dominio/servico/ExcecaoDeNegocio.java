package com.github.sfidencio.dominio.servico;

public class ExcecaoDeNegocio extends Throwable {
    public ExcecaoDeNegocio(String message) {
        super(message);
    }
}
