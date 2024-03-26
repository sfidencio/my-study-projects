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

    @Test
    @DisplayName("Deve obter um livro por id")
    public void deveObterLivroPorId() {
        //Cenario
        var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn("123").build();
        this.entityManager.persist(livro);
        //Execucao
        var livroEncontrado = this.repository.findById(livro.getId());
        //Verificacao
        assertThat(livroEncontrado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void deveSalvarLivro() {
        //Cenario
        var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn("123").build();
        //Execucao
        var livroSalvo = this.repository.save(livro);
        //Verificacao
        assertThat(livroSalvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve deletar um livro")
    public void deveDeletarLivro() {
        //Cenario
        var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn("123").build();
        this.entityManager.persist(livro);
        var livroEncontrado = this.entityManager.find(Livro.class, livro.getId());
        //Execucao
        this.repository.delete(livroEncontrado);
        var livroDeletado = this.entityManager.find(Livro.class, livro.getId());
        //Verificacao
        assertThat(livroDeletado).isNull();
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    public void deveAtualizarLivro() {
        //Cenario
        var livro = Livro.builder().titulo("Meu Livro").autor("Fulano").isbn("123").build();
        this.entityManager.persist(livro);
        var livroEncontrado = this.entityManager.find(Livro.class, livro.getId());
        livroEncontrado.setTitulo("Meu Livro Atualizado");
        //Execucao
        this.repository.save(livroEncontrado);
        var livroAtualizado = this.entityManager.find(Livro.class, livro.getId());
        //Verificacao
        assertThat(livroAtualizado.getTitulo()).isEqualTo("Meu Livro Atualizado");
    }
}