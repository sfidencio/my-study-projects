package com.github.sfidencio.lambdas;

import java.util.List;

public class L1 {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello, World! ");
        r.run();

        //Anonymous inner class, this code
        //is equivalent to the lambda expression above
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World! ");
            }
        };
    }
}
