package com.github.sfidencio.exemplo1.interfacesegregation.original;

public interface TabelaImpostoRenda {
    /**
     * Esse codigo pode ser melhorado, pois a tabela de imposto de renda
     * possui uma aliquota que pode ser obtida a partir da base de calculo
     * ou renda tributavel. Logo um pattern strategy seria mais adequado, afim de
     * alcancarmos o pricipio responsabilidade unica, bem como o aberto/fechado.
     * Creio que notara varios ifs e elses aqui.
     * Essa tabela muda todo ano, e a aliquota pode ser obtida a partir da base de calculo
     * Logo, temos apenas exemplos ficticios aqui.
     */
    default double getAliquota(double rendaTributavel) {
        double aliquota = 0.0d;
        if (rendaTributavel <= 1710.78) {
            aliquota = 0.00;
        } else if (rendaTributavel <= 2563.91) {
            aliquota = 0.09;
        } else if (rendaTributavel <= 3418.59) {
            aliquota = 0.11;
        } else {
            aliquota = 0.27;
        }
        return aliquota;
    }
}