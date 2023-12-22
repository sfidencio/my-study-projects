package com.github.sfidencio.vendas.infra.repository.relational;

import com.github.sfidencio.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    //@Query("select p from Produto p where p.descricao like %?1%")
    //List<Produto> findByDescricaoLike(String descricao);
    @Query("select p from Produto p where p.descricao like %:desc%")
    List<Produto> findByDescricaoLike(@Param("desc") String descricao);



    //Produto findOneByDescricao(String descricao);


    //Paginando produto
    //Page<Produto> findByDescricaoContaining(String descricao, Pageable pageable);


    //Procura produtos com preco maior ou igual ao informado.
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#query-methods.query-creation
    //https://springjavatutorial.medium.com/spring-boot-pagination-and-sorting-with-rest-api-20e5707c4c54
    //Page<Produto> findByPrecoGreaterThan(BigDecimal preco, Pageable pageable);
}
