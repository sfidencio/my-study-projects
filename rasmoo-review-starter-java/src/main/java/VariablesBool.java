public class VariablesBool {
    public static void main(String[] args) {
        boolean isOk = true;
        int i = 0;
        while (isOk) {
            i++;
            System.out.println(i);
            if (i >= 1000)
                isOk = false;
        }
    }
}
