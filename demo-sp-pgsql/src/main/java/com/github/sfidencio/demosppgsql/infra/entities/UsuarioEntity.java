package com.github.sfidencio.demosppgsql.infra.entities;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioEntity(
        Integer id,
        String nome,
        Boolean status,
        List<PermissoesEntity> permissoes) {
}
