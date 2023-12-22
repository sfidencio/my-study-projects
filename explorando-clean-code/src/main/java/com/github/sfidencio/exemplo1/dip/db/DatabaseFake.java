package com.github.sfidencio.exemplo1.dip.db;

import java.util.HashMap;
import java.util.Map;

public class DatabaseFake {

    private Map<String, String> indicesCalculo = new HashMap<>();

    public DatabaseFake() {
        this.build();
    }

    private void build() {
        if (this.indicesCalculo.isEmpty()) {
            this.indicesCalculo.put("a", "0.412d");
            this.indicesCalculo.put("b", "0.748d");
            this.indicesCalculo.put("c", "0.986d");
            this.indicesCalculo.put("d", "0.384d");
            this.indicesCalculo.put("e", "0.147d");
        }
    }

    public Object getValor(String chave) {
        return this.indicesCalculo.get(chave);
    }

}
