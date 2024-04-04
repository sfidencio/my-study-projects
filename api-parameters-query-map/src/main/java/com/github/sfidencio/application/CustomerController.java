package com.github.sfidencio.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final DatabaseFake databaseFake;

    public CustomerController(DatabaseFake databaseFake) {
        this.databaseFake = databaseFake;
    }

    //http://localhost:8080/customers/name/:name/processParams?&email=zanin@gmail.com,zanin@outlook.com&creditLimit=200.000&
    @GetMapping("/name/{name}/processParams")
    public Object getCustomer1(@PathVariable("name") String name,
                               @RequestParam(required = false) Map<String, String> params) {
        Map<String, List<Object>> map = new HashMap<>();

        params.forEach((k, v) -> {
            if (v.contains(",")) {
                String[] values = v.split(",");
                map.put(k, Arrays.asList(values));
            } else {
                map.put(k, List.of(v));
            }
        });

        var existsName = this.databaseFake.existsByName(name);

        /*if(!map.isEmpty()) {
           this.databaseFake.existsByName()
        }*/


        return map;
    }

    @GetMapping("/name/{name}")
    public Object getCustomer2(@PathVariable("name") String name) {
        if (this.databaseFake.existsByName(name))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
