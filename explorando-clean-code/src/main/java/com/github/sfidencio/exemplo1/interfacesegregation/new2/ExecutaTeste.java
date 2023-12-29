package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class ExecutaTeste {
    public static void main(String[] args) {


        TributoICMS icms = new ICMS();
        System.out.println("ICMS: " + icms.calcular(1000, 0.17));

        TributoImpostoRenda impostoRenda = new ImpostoRenda(new TabelaFaixa1(new TabelaFaixa2(new TabelaFaixa3(new TabelaFaixaInsento(null)))));
        System.out.println("Imposto de Renda: " + impostoRenda.calcular(3000, 200, 100));


    }
}
