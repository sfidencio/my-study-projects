package com.github.sfidencio.exemplo1.responsability;

public class Calculadora {
    /**
     * @param operacao
     * @param num1
     * @param num2
     * @return double
     *<p>
     *       Método para calcular operações matemáticas
     *       Cheio de Responsabilidades
     *       Não é coeso
     *       Não é legível
     *       Unico metodo que detem detalhes de implementação com cenarios distintos
     *       Implica o uso de diversos if/else
     *       Se surgir uma nova operação, teremos que alterar esse metodo
     *</p>
     */
    public double calcular(String operacao, double num1, double num2) {
        if (operacao.equals("+")) {
            return num1 + num2;
        } else if (operacao.equals("-")) {
            return num1 - num2;
        } else if (operacao.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}


