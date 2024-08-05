package com.github.sfidencio.demosppgsql.infra.dao;

import com.github.sfidencio.demosppgsql.infra.entities.PermissoesEntity;
import com.github.sfidencio.demosppgsql.infra.entities.UsuarioEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioPermissaoResultSetExtractor implements ResultSetExtractor<UsuarioEntity> {
    @Override
    public UsuarioEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
        Optional<UsuarioEntity> usuario = Optional.empty();
        Optional<PermissoesEntity> permissao;
        List<PermissoesEntity> permissoes = new ArrayList<>();
        while (rs.next()) {
            permissao = Optional.of(PermissoesEntity.builder()
                    .id(rs.getInt("id_permissao"))
                    .descricao(rs.getString("nome_permissao"))
                    .build());
            permissoes.add(permissao.get());
            if (rs.isLast()) {
                usuario = Optional.of(this.getUsuarioEntity(rs, permissoes));
            }
        }
        return usuario.orElse(null);
    }

    private UsuarioEntity getUsuarioEntity(ResultSet rs, List<PermissoesEntity> permissoes) throws SQLException {
        var usuario = UsuarioEntity.builder()
                .id(rs.getInt("id"))
                .nome(rs.getString("nome"))
                .status(rs.getBoolean("status"))
                .permissoes(permissoes)
                .build();
        return usuario;
    }
}


