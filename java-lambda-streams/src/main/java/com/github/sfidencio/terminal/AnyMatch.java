package com.github.sfidencio.terminal;

import java.util.List;
import java.util.UUID;

public class AnyMatch {
    public static void main(String[] args) {

        var precoProcurado = true;

        List<Product> products1 = List.of(
                new Product("Product 1", 10.0, true),
                new Product("Product 2", 20.0, true),
                new Product("Product 3", 30.0, true),
                new Product("Product 4", 40.0, true),
                new Product("Product 5", 50.0, true)
        );

        List<Product> products2 = List.of(
                new Product("Product 6", 60.0, false),
                new Product("Product 7", 70.0, false),
                new Product("Product 8", 80.0, false),
                new Product("Product 9", 90.0, false),
                new Product("Product 10", 100.0, false)
        );

        List<Product> products3 = List.of(
                new Product("Product 11", 110.0, false),
                new Product("Product 12", 120.0, false),
                new Product("Product 13", 130.0, true),
                new Product("Product 14", 140.0, false),
                new Product("Product 15", 150.0, false)
        );


        var anyMatch = products1
                .stream()
                .anyMatch(Product::isActive);

        System.out.println("Any match: " + anyMatch);

        System.out.println("******************************************************");

        var anyMatch2 = products2
                .stream()
                .anyMatch(Product::isActive);
        System.out.println("Any match: " + anyMatch2);

        System.out.println("******************************************************");

        var anyMatch3 = products3
                .stream()
                .anyMatch(Product::isActive);
        System.out.println("Any match: " + anyMatch3);
    }

    public static class Product{
        private String id;
        private String name;
        private double price;
        private boolean active;

        public Product(String name, double price, boolean active){
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.price = price;
            this.active = active;
        }

        public String getName(){
            return name;
        }

        public double getPrice(){
            return price;
        }

        public boolean isActive(){
            return active;
        }


        @Override
        public String toString(){
            return "Product{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", active=" + active +
                    '}';
        }
    }
}
