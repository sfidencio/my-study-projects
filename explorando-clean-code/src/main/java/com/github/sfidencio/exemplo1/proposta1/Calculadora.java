package com.github.sfidencio.exemplo1.proposta1;

public class Calculadora {
    /**
     * @param estrategia
     * @param num1
     * @param num2
     * @return double
     * <p>
     *  Método para calcular operações matemáticas
     *  Imutável, nao ha necessidade de alterar esse metodo a principio, caso surja uma nova operação
     *  Mais coeso (1 linha de codigo apenas)
     *  Mais legível
     *  Mais fácil de manter
     *
     *  Estamos aplicando os principios
     *      responsabilidade unica(Single Responsibility Principle)
     *      aberto para extensão e fechado para modificação(Open Closed Principle)
     *
     *  Esse metodo não tem responsabilidade de saber como calcular, apenas delega para a classe CalculadoraStrategy
     *  A estrategia de calculo esta encapsulada na classe CalculadoraStrategy
     *  Poderiamos abstrair mais ainda, criando uma interface comum e implementando as operações em classes separadas
     *  Ou seja, processo descomponentizacao
     *
     * </p>
     */
    public double calcular(CalculadoraStrategy estrategia, double num1, double num2) {
        return estrategia.calcular(num1, num2);
    }
}


