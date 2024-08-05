--obter usuario e suas permissoes
select u.id,
       u.nome,
       p.nome as permissao,
       p.id   as permissao_id,
       p.usuario_id
from usuarios u
         inner join permissoes p on u.id = p.usuario_id;