package com.github.sfidencio.exemplo1.proposta2;

import com.github.sfidencio.exemplo1.proposta2.openclosed.Divisao;
import com.github.sfidencio.exemplo1.proposta2.openclosed.Multiplicacao;
import com.github.sfidencio.exemplo1.proposta2.openclosed.Soma;
import com.github.sfidencio.exemplo1.proposta2.openclosed.Subtracao;

public class CalculadoraTeste {
    public static void main(String[] args) {
        CalculadoraService calculadora = new CalculadoraService();
        imprimirResultado(calculadora.calcular(new Divisao(), 9, 8));
        imprimirResultado(calculadora.calcular(new Soma(), 9, 8));
        imprimirResultado(calculadora.calcular(new Subtracao(), 9, 8));
        imprimirResultado(calculadora.calcular(new Multiplicacao(), 9, 8));
    }

    static void imprimirResultado(double resultado) {
        System.out.println(resultado);
    }
}
