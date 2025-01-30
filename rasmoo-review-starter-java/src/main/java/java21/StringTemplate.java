package java21;


public class StringTemplate {
    public static void main(String[] args) {
        var price = 1.20;
        var qty = 10;
        var name = "Feijao";
        //1º forma
        System.out.println("Preco: " + price + " Qtde: " + qty + ". Preco Final: " + price * qty);

        //2ºforma
        System.out.printf(String.format("Preco: %.2f e Qtde: %d para produto: %s", price, qty, name));

        //3º forma - nova
        //System.out.println(STR."O preco final do \{name} com valor final \{price*qty}");
        //STR."teste".
    }
}
