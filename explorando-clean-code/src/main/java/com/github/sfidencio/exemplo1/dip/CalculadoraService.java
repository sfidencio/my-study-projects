package com.github.sfidencio.exemplo1.dip;

import com.github.sfidencio.exemplo1.dip.db.DatabaseFake;

public class CalculadoraService {

    private DatabaseFake databaseFake;

    public CalculadoraService(DatabaseFake databaseFake) {
        this.databaseFake = databaseFake;
    }

    public double calcular(Calculadora calculadora, double num1, double num2) {
        return this.aplicarIndice(calculadora.calcular(num1, num2));
    }

    private double aplicarIndice(double resultado) {
          return resultado > 100 ? resultado + Double.parseDouble(this.databaseFake.getValor("a").toString()) : resultado + Double.parseDouble(this.databaseFake.getValor("b").toString());
    }
}


