package com.github.sfidencio.intermediate;

import java.util.List;
import java.util.UUID;

public class Map {
    public static void main(String[] args) {
        List<String> names = List.of("Alcides", "Alvaro", "Gersom", "Joao", "Jose", "Maria", "Pedro", "Ricardo", "Roberto", "Sergio");
        var nameWithRandomSingle =
                names.stream()
                        /*.map(
                                name -> new Customer(name)
                        );*/
        .map(Customer::new)
                .filter(
                        customer -> customer.getName().contains("oa")
                ).findFirst();


        System.out.printf("Names with random: %s\n", nameWithRandomSingle);

        System.out.println("******************************************************");


        var namesWithRandomList =
                names.stream()
                        .map(
                                name -> new Customer(name)
                        )
                        .filter(
                                customer -> customer.getName().contains("ar")
                        ).toList();


        System.out.printf("Names with random: %s\n", namesWithRandomList);



    }

    public static class Customer {
        private String id;
        private String name;

        public Customer(String name) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
