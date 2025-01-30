package com.github.sfidencio.singleton;

public class Application {

        public static void main(String[] args) {
            Singleton singleton = Singleton.getInstance();
            System.out.println(singleton);

            System.out.println("***********************************");

            Singleton singleton2 = Singleton.getInstance();
            System.out.println(singleton2);

            System.out.println("***********************************");

            Singleton singleton3 = Singleton.getInstance();
            System.out.println(singleton3);
        }
}
