package com.github.sfidencio.application;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseFake {

    private final Map<String, List<Object>> db;


    public DatabaseFake() {
        this.db = new HashMap<>();
        this.db.put("email", List.of("fulano@gmail.com","beltrano@gmail.com","ciclano@outlook.com"));
        this.db.put("creditLimit", List.of(1000.00, 2000.00, 3000.00));
        this.db.put("name", List.of("FULANO", "BELTRANO", "CICLANO"));

    }


    public boolean existsByName(String name) {
       List<Object> values = this.db.get("name");
       return values.contains(name.toUpperCase());
    }
}
