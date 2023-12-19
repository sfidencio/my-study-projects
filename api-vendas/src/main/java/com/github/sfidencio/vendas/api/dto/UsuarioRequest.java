package com.github.sfidencio.vendas.api.dto;

import lombok.With;
import lombok.experimental.Accessors;

@With
@Accessors(fluent = true, chain = true)
public record UsuarioRequest(String nome, String email) {

    public static void main(String[] args) {
        var usuarioRequest = new UsuarioRequest("Fidencio", "fulano@gmail.com");
        usuarioRequest.withEmail("teste@gmail.com");
        usuarioRequest.withNome("Fidencio Silva");

    }

}
