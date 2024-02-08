package br.com.sfidencio.clientes.model.repository;

import br.com.sfidencio.clientes.model.entity.PrestacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrestacaoServicoRepository extends JpaRepository<PrestacaoServico, UUID> {
    @Query("select s from PrestacaoServico s join s.cliente c where upper(c.nome) like upper(:nome) and MONTH(s.dataServico)=:mes ")
    List<PrestacaoServico> obterPrestacaoServicosPorClienteEMes(@Param("nome") String nome,
                                                                @Param("mes") Integer mes);


    //@Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%',:username,'%')")
    //List<String> findUsersWithPartOfName(@Param("username") String username);
}
