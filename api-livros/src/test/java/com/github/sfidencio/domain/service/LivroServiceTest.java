package com.github.sfidencio.domain.service;

import com.github.sfidencio.domain.model.Livro;
import com.github.sfidencio.infrastructure.exceptions.BusinessException;
import com.github.sfidencio.infrastructure.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LivroServiceTest {

    @MockBean
    private LivroRepository repository;
    private LivroService service;

    @BeforeEach
    void setUp() {
        this.service = new LivroServiceImp(this.repository);
    }

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    void deveCriarLivroComSucesso() {
        //Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();


        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.save(Mockito.any(Livro.class))).thenReturn(livro);
        //BDDMockito.given(this.repository.save(Mockito.any(Livro.class))).willReturn(livro);
        // -> mais utilizado para o test de controller

        //Execucao
        var livroSalvo = this.service.salvar(livro);

        //Verificacao/Teste
        assertThat(livroSalvo.getId()).isNotNull();
        assertThat(livroSalvo.getTitulo()).isEqualTo("Meu Livro");
    }

    @Test
    @DisplayName("Deve lançar erro de negocio quando ISBN duplicado")
    void deveLancarErroNegocioQuandoISBNDuplicado() {
        // Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.TRUE);
        // BDDMockito.given(this.repository.save(Mockito.any(Livro.class))).willReturn(livro);
        // -> mais utilizado para o test de controller

        // Execucao
        // var livroSalvo = this.service.salvar(livro);
        assertThatThrownBy(() -> this.service.salvar(livro)).isInstanceOf(BusinessException.class)
                .hasMessage("ISBN já cadastrado.");


        //Outra forma de fazer a verificacao
        //Throwable catchThrowable2 = catchThrowable(() -> this.service.salvar(livro));
        //assertThat(catchThrowable2).isInstanceOf(BusinessException.class).hasMessage("ISBN já cadastrado.");


        //Verificando se o metodo foi chamado (Save) - nesse cenario nao dever ser chamado
        Mockito.verify(this.repository, Mockito.never()).save(livro);


        // Verificacao/Teste
        // assertThat(livroSalvo.getId()).isNotNull();
        // assertThat(livroSalvo.getTitulo()).isEqualTo("Meu Livro");
    }

    @Test
    @DisplayName("Deve atualizar um livro com sucesso")
    void deveAtualizarLivroComSucesso() {
        //Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.save(Mockito.any(Livro.class))).thenReturn(livro);
        Mockito.when(this.repository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(livro));

        //Execucao
        var livroSalvo = this.service.atualizar(livro);

        //Verificacao/Teste
        assertThat(livroSalvo.getId()).isNotNull();
        assertThat(livroSalvo.getTitulo()).isEqualTo("Meu Livro");
    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar atualizar um livro inexistente")
    void deveLancarErroNegocioAoTentarAtualizarLivroInexistente() {
        // Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.empty());

        // Execucao
        // var livroSalvo = this.service.salvar(livro);
        assertThatThrownBy(() -> this.service.atualizar(livro)).isInstanceOf(BusinessException.class)
                .hasMessage("Livro não encontrado.");

    }

    @Test
    @DisplayName("Deve deletar um livro com sucesso")
    void deveDeletarLivroComSucesso() {
        //Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.findById(Mockito.any
                (Long.class))).thenReturn(java.util.Optional.of(livro));

        //Execucao
        this.service.excluir(livro.getId());

        //Verificacao/Teste
        Mockito.verify(this.repository, Mockito.times(1)).delete(livro);
    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar deletar um livro inexistente")
    void deveLancarErroNegocioAoTentarDeletarLivroInexistente() {
        // Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123").build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.empty());

        // Execucao
        // var livroSalvo = this.service.salvar(livro);
        assertThatThrownBy(() -> this.service.excluir(livro.getId())).isInstanceOf(BusinessException.class)
                .hasMessage("Livro não encontrado.");
    }

    @Test
    @DisplayName("Deve buscar um livro por id com sucesso")
    void deveBuscarLivroPorIdComSucesso() {
        //Cenario
        var livro = Livro.builder()
                .id(1L)
                .titulo("Meu Livro")
                .autor("Autor")
                .isbn("123")
                .build();

        Mockito.when(this.repository.existsByIsbn(Mockito.any(String.class)))
                .thenReturn(Boolean.FALSE);
        Mockito.when(this.repository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(livro));

        //Execucao
        var livroEncontrado = this.service.buscarPorId(livro.getId());

        //Verificacao/Teste
        assertThat(livroEncontrado.getId()).isNotNull();
        assertThat(livroEncontrado.getTitulo()).isEqualTo("Meu Livro");
    }
}
