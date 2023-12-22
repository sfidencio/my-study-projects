package com.github.sfidencio.exemplo1.openclosed;

import com.github.sfidencio.exemplo1.openclosed.Calculadora;

public class CalculadoraService {



    /**
     * @param calculadora
     * @param num1
     * @param num2
     * @return double
     * <p>
     *  Método para calcular operações matemáticas
     *  Imutável, nao ha necessidade de alterar esse metodo a principio, caso surja uma nova operação
     *  Mais coeso (1 linha de codigo apenas)
     *  Mais legível
     *  Mais fácil de manter
     *  Principio de responsabilidade única(Single Responsibility Principle)
     *  Principio de aberto/fechado(Open/Closed Principle)
     *  Esse metodo não tem responsabilidade de saber como calcular, apenas delega para a classe CalculadoraStrategy
     *  A estrategia de calculo esta encapsulada na classe CalculadoraStrategy
     *  Poderiamos abstrair mais ainda, criando uma interface comum e implementando as operações em classes separadas, ou seja, processo descomponentizacao
     *  Estamos aplicando o polimorfismo, pois o metodo calcular e abstrato e cada enum implementa o metodo calcular de forma diferente
     * </p>
     */
    public double calcular(Calculadora calculadora, double num1, double num2) {
        return calculadora.calcular(num1, num2);
    }
}


