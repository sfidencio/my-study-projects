package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class TabelaFaixa2 implements TabelaImpostoRendaHandler {

    private TabelaImpostoRendaHandler proximo;

    public TabelaFaixa2(TabelaImpostoRendaHandler proximo) {
        this.proximo = proximo;
    }

    @Override
    public double encontrarAliquota(double rendaTributavel) {
        return rendaTributavel <= 3418.59 ? 0.11 : proximo.encontrarAliquota(rendaTributavel);
    }
}