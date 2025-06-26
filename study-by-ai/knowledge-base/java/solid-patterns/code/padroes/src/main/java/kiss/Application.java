package kiss;

public class Application {
    public static void main(String[] args) {
        String text = "Hello, World!";
        System.out.println(reverseOne(text));
        System.out.println("---------------------");
        System.out.println(reverseTwo(text));
        System.out.println("---------------------");
        System.out.println(reverseThree(text));
    }

    public static String reverseOne(String input) {
        char[] chars = input.toCharArray();
        StringBuilder reversed = new StringBuilder();

        for (int i = chars.length-1; i >= 0; i--)
            reversed.append(chars[i]);
        return reversed.toString();
    }

    public static String reverseTwo(String input) {
        StringBuilder reversed = new StringBuilder(input);
        return reversed.reverse().toString();
    }

    public static String reverseThree(String input) {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static String reverseFour(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
}


