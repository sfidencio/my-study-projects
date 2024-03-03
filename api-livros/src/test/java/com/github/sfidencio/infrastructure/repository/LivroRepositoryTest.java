package com.github.sfidencio.infrastructure.repository;

import com.github.sfidencio.domain.model.Livro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Teste de integração com banco de dados
 */

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class LivroRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    LivroRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o ISBN informado")
    public void deveRetornarVerdadeiroQuandoExistirLivroComISBN() {
        //Cenario
        var isbn = "123";
        var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn(isbn).build();
        this.entityManager.persist(livro);
        //Execucao
        var exists = this.repository.existsByIsbn(isbn);
        //Verificacao
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando nao existir um livro na base com o ISBN informado")
    public void deveRetornarFalsoQuandoExistirLivroComISBN() {
        //Cenario
        var isbn = "123";
        //var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn(isbn).build();
        //this.entityManager.persist(livro);
        //Execucao
        var exists = this.repository.existsByIsbn(isbn);
        //Verificacao
        assertThat(exists).isFalse();
    }


}