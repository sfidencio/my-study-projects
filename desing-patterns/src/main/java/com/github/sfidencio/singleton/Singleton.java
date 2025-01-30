package com.github.sfidencio.singleton;

public class Singleton {

        private static Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
                System.out.println("Instância criada");
            }

            System.out.println("Retornando instância ja criada");
            return instance;
        }
}
