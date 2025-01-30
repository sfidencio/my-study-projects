public class Exemplo01 {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread em execução!");
    }
}
