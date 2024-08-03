DROP FUNCTION IF EXISTS sp_seleciona_usuarios(varchar);

CREATE
OR REPLACE FUNCTION sp_seleciona_usuarios(nome_in varchar)
         RETURNS TABLE (id_out integer, nome_out varchar, status_out boolean)
AS $$

BEGIN

--retorna os registros da tabela usuarios
RETURN QUERY
SELECT u.id,
       u.nome,
       u.status
FROM usuarios u
WHERE u.nome ILIKE '%' || nome_in || '%';
--commit
END;
$$ LANGUAGE plpgsql;

