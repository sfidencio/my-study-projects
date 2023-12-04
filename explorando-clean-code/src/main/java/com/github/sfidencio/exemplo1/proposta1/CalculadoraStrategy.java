package com.github.sfidencio.exemplo1.proposta1;

public enum CalculadoraStrategy {
    SOMA {
        @Override
        public double calcular(double num1, double num2) {
            return num1 + num2;
        }
    },
    MULTIPLICACAO {
        @Override
        public double calcular(double num1, double num2) {
            return num1 * num2;
        }
    },
    SUBTRACAO {
        @Override
        public double calcular(double num1, double num2) {
            return num1 - num2;
        }
    },
    DIVISAO {
        @Override
        public double calcular(double num1, double num2) {
            return num1 / num2;
        }
    };

    public abstract double calcular(double num1, double num2);
}
