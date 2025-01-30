package com.gihub.sfidencio.mybusiness.domain.model;

//sugestion clean code

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal stockUpdated;

    public Product(Long id, String name, BigDecimal price, BigDecimal stockUpdated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockUpdated = stockUpdated;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getStockUpdated() {
        return stockUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockUpdated=" + stockUpdated +
                '}';
    }
}