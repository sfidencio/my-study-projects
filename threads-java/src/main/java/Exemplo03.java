public class Exemplo03 {
    public static void main(String[] args) throws InterruptedException {
        //Sincronizacao - problemas de concorrencia, como resolver?
        Contador c1 = new Contador();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c1.increment();
                System.out.println("t1 -> " + "'" + c1.getCount() + "'");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c1.increment();
                System.out.println("t2 -> " + "'" + c1.getCount() + "'");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finalizado!");

    }
}

class Contador {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
