public class Exemplo02 {
    public static void main(String[] args) {
        MyThread2 t = new MyThread2();
        t.run();
        //ou entao
        System.out.println( );
        Thread t1 = new Thread(new MyThread2());
        t1.start(); //start() invoca o metodo run()
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread(Runnable) em execução!");
    }
}
