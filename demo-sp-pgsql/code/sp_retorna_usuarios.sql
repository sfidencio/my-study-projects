DROP FUNCTION IF EXISTS sp_seleciona_usuarios(varchar);

CREATE
    OR REPLACE FUNCTION sp_seleciona_usuarios(nome_in varchar)
    RETURNS TABLE
            (
                id_usuario_out     integer,
                nome_usuario_out   varchar,
                nome_permissao_out varchar,
                id_permissao_out   integer,
                usuario_id_out     integer,
                status_out         boolean
            )
AS
$$

BEGIN
    RETURN QUERY
        SELECT u.id         AS id_usuario_out,
               u.nome       AS nome_usuario_out,
               p.nome       AS nome_permissao_out,
               p.id         as id_permissao_out,
               p.usuario_id AS usuario_id_out,
               u.status     AS status_out
        FROM usuarios u
                 INNER JOIN permissoes p ON u.id = p.usuario_id
        WHERE u.nome = nome_in;
END;
$$ LANGUAGE plpgsql;

