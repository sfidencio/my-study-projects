select
    id_usuario_out AS id,
    nome_usuario_out AS nome,
    id_permissao_out AS id_permissao,
    nome_permissao_out AS nome_permissao,
    status_out AS status
    from sp_seleciona_usuarios('Pedro');