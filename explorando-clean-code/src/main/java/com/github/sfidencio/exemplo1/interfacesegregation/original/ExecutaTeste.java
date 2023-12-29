package com.github.sfidencio.exemplo1.interfacesegregation.original;

public class ExecutaTeste {
    public static void main(String[] args) {

        Tributo icms = new ICMS();
        System.out.println("ICMS: " + icms.calcular(1000, 0.17));

        Tributo impostoRenda = new ImpostoRenda();
        System.out.println("Imposto de Renda: " + impostoRenda.calcular(1000, 200, 100));

    }
}
