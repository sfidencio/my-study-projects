package com.github.sfidencio.lambdas;

public class L2 {

    public static void main(String[] args) {
        //Usando uma interface funcional
        CalculoSoma calculoSoma = () -> System.out.println(2 + 2);
        calculoSoma.execute();

        //Usando uma classe anônima
        CalculoSoma calculoSoma1 = new CalculoSoma() {
            @Override
            public void execute() {
                System.out.println(2 + 2);
            }
        };

        //Quando precisamos executar mais de um comando no corpo da expressão lambda
        //Utilizamos chaves
        CalculoSoma calculoSoma2 = () -> {
            System.out.println(2 + 2);
            System.out.println(3 + 3);
        };

        calculoSoma2.execute();
    }


    @FunctionalInterface
    public interface CalculoSoma {
        void execute();
    }
}
