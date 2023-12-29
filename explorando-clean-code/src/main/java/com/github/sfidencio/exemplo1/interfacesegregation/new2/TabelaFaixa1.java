package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class TabelaFaixa1 implements TabelaImpostoRendaHandler {

    private TabelaImpostoRendaHandler proximo;

    public TabelaFaixa1(TabelaImpostoRendaHandler proximo) {
        this.proximo = proximo;
    }

    @Override
    public double encontrarAliquota(double rendaTributavel) {
        return rendaTributavel <= 2563.91 ? 0.09 : proximo.encontrarAliquota(rendaTributavel);
    }
}