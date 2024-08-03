package com.github.sfidencio.demosppgsql.infra.dao;

import com.github.sfidencio.demosppgsql.infra.entities.UsuarioEntity;

import java.util.List;

public interface IUsuarioDAO {
    List<UsuarioEntity> selecionarUsuarios(String nome);
}
