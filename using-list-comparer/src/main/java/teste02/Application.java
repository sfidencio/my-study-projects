package teste02;

public class Application {
    public static void main(String[] args) {
        int numero = 10;
        String[] operadores = {"MENOR", "MAIOR", "IGUAL"};
        int[] numerosA = {21, 20, 40};
        int[] numerosB = {89, 2, 40};
        for (int i = 0; i < operadores.length ; i++)
            System.out.println(operadores[i] + "-" + ComparadorStrategy.valueOf(operadores[i]).comparar(numerosA[i], numerosB[i]));
    }
}
