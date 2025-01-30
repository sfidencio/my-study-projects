package com.github.sfidencio.adapter.in;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {

    private final List<Customer> databaseFake = new ArrayList<>();


    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok(this.databaseFake);
    }


    @PostConstruct
    public void init() {
        this.databaseFake.add(Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .email("customer1@gmail.com").build());
        this.databaseFake.add(Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .email("customer2@gmail.com")
                .build());
    }

    @Data
    @Builder
    public static class Customer {
        private UUID id;
        private String name;
        private String email;
    }
}
