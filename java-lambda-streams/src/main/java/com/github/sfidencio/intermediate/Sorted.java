package com.github.sfidencio.intermediate;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Sorted {
    public static void main(String[] args) {
        List<Product> numbersOrder  = List.of(
                new Product("Product 1", 10.0),
                new Product("Product 2", 20.0),
                new Product("Product 3", 30.0),
                new Product("Product 4", 40.0),
                new Product("Product 5", 50.0),
                new Product("Product 6", 60.0),
                new Product("Product 7", 70.0),
                new Product("Product 8", 80.0),
                new Product("Product 9", 90.0),
                new Product("Product 10", 100.0)
        );

        numbersOrder.stream().sorted(
                //(p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice())
                Comparator.comparing(Product::getPrice)
        ).forEach(System.out::println);

        System.out.println("******************************************************");

        //descending order - lambda
        //numbersOrder.stream().sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())).forEach(System.out::println);
        //descending order - method reference
        numbersOrder.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .forEach(System.out::println);

    }

    public static class Product implements Comparable<Product> {
        private String id;
        private String name;
        private double price;

        public Product(String name, double price) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }

        @Override
        public int compareTo(Product o) {
            return Double.compare(this.price, o.price);
        }
    }
}
