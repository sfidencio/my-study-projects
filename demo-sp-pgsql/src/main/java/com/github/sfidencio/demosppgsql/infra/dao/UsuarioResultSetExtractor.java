package com.github.sfidencio.demosppgsql.infra.dao;

import com.github.sfidencio.demosppgsql.infra.entities.UsuarioEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioResultSetExtractor implements ResultSetExtractor<List<UsuarioEntity>> {
    @Override
    public List<UsuarioEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        while (rs.next()) {
            usuarios.add(new UsuarioEntity(
                    rs.getInt("id_out"),
                    rs.getString("nome_out"),
                    rs.getBoolean("status_out")));
        }
        return usuarios;
    }
}
