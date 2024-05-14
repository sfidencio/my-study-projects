package teste01;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        List<MyObject> list =new ArrayList<>();
        list.add(new MyObject(10));
        list.add(new MyObject(11));
        list.add(new MyObject(12));
        list.add(new MyObject(9));
        list.add(new MyObject(100));
        list.add(new MyObject(-1));

        Map<String,Integer> salas = new HashMap<>();
        salas.put("sala1", 1);
        salas.put("sala2", 2);
        salas.put("sala3", 3);




        var maxValue = Collections.max(list).value;
        var minValue = Collections.min(list).value;
        System.out.println(maxValue);
        System.out.println(minValue);

        Comparator<MyObject> comparator = Comparator.comparing(MyObject::getValue);
        var maxValue2 = list.stream().max(comparator).get().value;
    }

}
class MyObject implements Comparable<MyObject> {
    int value;
    Map<String,Integer> mapa = new HashMap<>();

    public MyObject(int value, Map<String, Integer> mapa) {
        this.value = value;
        this.mapa = mapa;
    }
    public MyObject(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(MyObject o) {
        return Integer.compare(this.value, o.value);
    }

    public int getValue() {
        return value;
    }
}


