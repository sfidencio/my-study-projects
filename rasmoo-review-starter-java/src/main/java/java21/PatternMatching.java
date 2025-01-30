package java21;

import java.math.BigDecimal;
import java.util.List;

/*
 * Pattern Matching usando InstanceOf e Switch Case
 * */
public class PatternMatching {
    public static void main(String[] args) {
        printValue(1.2);
        printValue(Boolean.TRUE);
        printValue("Hello World");
        printValue(List.of(1, 2, 3, 4, 5, 6));
        printValue(new BigDecimal("878787878787878787878"));
        System.out.println();
        printValue2(List.of(true,false));
        printValue2(1.2);


        System.out.println();
        System.out.println("Calcule shape!");

        System.out.println(calculeArea(new Circle(5.0)));
        System.out.println(calculeArea(new Square(1.2)));
        System.out.println(calculeArea(new Rectangle(1.2, 1.3)));
    }

    public static void printValue(Object value) {
        var message = switch (value) {
            case Long l -> String.format("Long: %d", l);
            case Boolean b -> ((Boolean) value).toString();
            case Double d -> String.format("Double: %f", d);
            case String s -> String.format("String: %s", s);
            case List<?> f -> String.format("List: %s", f);
            default -> "Unknown type";
        };
        System.out.println(message);
    }

    public static double calculeArea(Shape shape) {
        return switch (shape) {
            case Square s -> Math.pow(s.side(), 2);
            case Circle c -> Math.PI * Math.pow(c.radius(), 2);
            case Triangle t -> (t.base() * t.height());
            //case seja quadrado?
            case Rectangle r when r.height() == r.width() -> {
                System.out.println("Rectangle is Square ");
                yield Math.pow(r.height(), 2);
            }
            case Rectangle r -> r.width() * r.height();
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };

    }

    public static void printValue2(Object value) {
        if (value instanceof List<?>)
            System.out.println(value);
        if (value instanceof Double)
            System.out.println(value);
    }
}

