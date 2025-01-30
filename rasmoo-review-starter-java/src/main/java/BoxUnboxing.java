public class BoxUnboxing {
    public static void main(String[] args) {
        float value = 10.2f;
        System.out.println("Primitive type: " + value);
        Float valueWrapper = value;
        System.out.println("Wrapper type: (boxing) " + valueWrapper);
        float valueReturn = value;
        System.out.println("Unboxing type: " + valueReturn);
    }
}
