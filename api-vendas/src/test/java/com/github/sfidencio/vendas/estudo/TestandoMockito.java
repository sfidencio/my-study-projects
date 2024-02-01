package com.github.sfidencio.vendas.estudo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestandoMockito {


    // Aqui você pode escrever seus testes
    // utilizando o Mockito
    @Test
    @DisplayName("Deve chamar o método size")
    public void test_deve_chamar_metodo_size_pelo_menos_duas_vezes() {
        List<Long> listaNumerosMockados = Mockito.mock(List.class);
        Mockito.when(listaNumerosMockados.get(0)).thenReturn(10L);
        Mockito.when(listaNumerosMockados.get(1)).thenReturn(20L);
        Mockito.when(listaNumerosMockados.size()).thenReturn(200);
        Assertions.assertThat(listaNumerosMockados.get(0)).isEqualTo(10L);
        Assertions.assertThat(listaNumerosMockados.get(1)).isEqualTo(20L);
        Assertions.assertThat(listaNumerosMockados.size()).isEqualTo(200);
        Assertions.assertThat(listaNumerosMockados.size()).isEqualTo(200);

        Mockito.verify(listaNumerosMockados, Mockito.times(2)).size();
    }

    @Test
    @DisplayName("Nunca deveria chamar o método size")
    public void test_nunca_deveria_chamar_metodo_size() {
        List<Long> listaNumerosMockados = Mockito.mock(List.class);
        Mockito.when(listaNumerosMockados.get(0)).thenReturn(10L);
        Mockito.when(listaNumerosMockados.get(1)).thenReturn(20L);
        //Mockito.when(listaNumerosMockados.size()).thenReturn(200);
        Assertions.assertThat(listaNumerosMockados.get(0)).isEqualTo(10L);
        Assertions.assertThat(listaNumerosMockados.get(1)).isEqualTo(20L);


        //Assertions.assertThat(listaNumerosMockados.size()).isEqualTo(200);
        //Assertions.assertThat(listaNumerosMockados.size()).isEqualTo(200);

        Mockito.verify(listaNumerosMockados, Mockito.never()).size();
    }

    @Test
    @DisplayName("Deve chamar os métodos na ordem correta")
    public void test_explorando_ordem_metodo_verify() {
        List<Long> listaNumerosMockados = Mockito.mock(List.class);
        Mockito.when(listaNumerosMockados.get(0)).thenReturn(10L);
        Mockito.when(listaNumerosMockados.get(1)).thenReturn(20L);
        //Mockito.when(listaNumerosMockados.size()).thenReturn(200);
        Assertions.assertThat(listaNumerosMockados.get(0)).isEqualTo(10L);
        Assertions.assertThat(listaNumerosMockados.get(1)).isEqualTo(20L);


        //Se mudarmos a ordem das assertivas, acima, o teste ja falha abaixo
        InOrder inOrder = Mockito.inOrder(listaNumerosMockados);
        inOrder.verify(listaNumerosMockados).get(0);
        inOrder.verify(listaNumerosMockados).get(1);
        inOrder.verify(listaNumerosMockados, Mockito.never()).size();
        Mockito.verify(listaNumerosMockados, Mockito.never()).size();
    }


    //Teste de exceção no junit 5
    @Test
    @DisplayName("Deve lançar exceção")
    public void test_deve_lancar_excecao() {
        Assertions.assertThatThrownBy(() -> metodoQueLancaExcecao())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Exceção lançada");
    }

    private void metodoQueLancaExcecao() {
        throw new RuntimeException("Exceção lançada");
    }

    @Test
    @DisplayName("Testando metodo que adiciona um item na lista")
    public void test_deve_adicionar_item_na_lista() {
        List<Long> listaNumerosMockados = new ArrayList<>(0);
        listaNumerosMockados.add(10L);
        listaNumerosMockados.add(20L);
        listaNumerosMockados.add(30L);
        Assertions.assertThat(listaNumerosMockados).hasSize(3);
    }
}
