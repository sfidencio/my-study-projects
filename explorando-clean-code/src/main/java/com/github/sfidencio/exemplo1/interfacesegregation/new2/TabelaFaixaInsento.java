package com.github.sfidencio.exemplo1.interfacesegregation.new2;

public class TabelaFaixaInsento implements TabelaImpostoRendaHandler {

    private TabelaImpostoRendaHandler proximo;

    public TabelaFaixaInsento(TabelaImpostoRendaHandler proximo) {
        this.proximo = proximo;
    }

    @Override
    public double encontrarAliquota(double rendaTributavel) {
        return rendaTributavel <= 1710.78 ? 0.00 : proximo.encontrarAliquota(rendaTributavel);
    }
}