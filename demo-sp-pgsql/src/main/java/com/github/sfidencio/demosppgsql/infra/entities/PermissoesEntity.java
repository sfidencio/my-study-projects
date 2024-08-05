package com.github.sfidencio.demosppgsql.infra.entities;


import lombok.Builder;

@Builder
public record PermissoesEntity(Integer id,
                               String descricao) {
}
