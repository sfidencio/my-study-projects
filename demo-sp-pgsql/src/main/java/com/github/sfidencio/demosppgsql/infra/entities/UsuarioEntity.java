package com.github.sfidencio.demosppgsql.infra.entities;

import lombok.Builder;

@Builder
public record UsuarioEntity(
    Integer id,
    String nome,
    Boolean status)
{}
