package com.github.sfidencio.terminal;

import java.util.List;
import java.util.stream.Collectors;

public class PartitioningBy {
    public static void main(String[] args) {
        List<String> productVariants = List.of("Apple", "Google", "Microsoft", "Oracle", "IBM", "Alphabet", "Facebook", "Twitter", "Amazon", "Netflix");

        productVariants.stream().collect(
                Collectors.partitioningBy(
                        product -> product.startsWith("A")
                )
        ).forEach((productVariantsPrice, product) -> {
            System.out.printf("Partitioning by: %s\n", productVariantsPrice);
            System.out.printf("Product: %s\n", product);
        });

        System.out.println("--------------------------------------------------");
        System.out.printf("Partitioning by: %s\n", productVariants);
    }
}
