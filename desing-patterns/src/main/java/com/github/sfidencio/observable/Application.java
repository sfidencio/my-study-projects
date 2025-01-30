package com.github.sfidencio.observable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        var product = new Product(1, LocalDateTime.now(), "Produto 1", new BigDecimal("22.40"));
        product.subscribeEvent(new TotalAmountExceeded());
        product.subscribeEvent(new ProductQuantityLessThanFive());
        product.calculateTotal(new BigDecimal("10"));
        System.out.println(product);
    }

}

class Product {
    private List<ProductObserver> observers = new ArrayList<>();
    private int code;
    private LocalDateTime date;
    private String description;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal total;

    private static final BigDecimal LIMIT_TOTAL = BigDecimal.TEN;

    public Product(int code, LocalDateTime date, String description, BigDecimal price) {
        this.code = code;
        this.date = date;
        this.description = description;
        this.price = price;
        this.quantity = BigDecimal.ZERO;
    }

    public void calculateTotal(BigDecimal quantity) {
        this.quantity = quantity;
        this.checkTotal();
    }

    private void checkTotal() {
        this.total = this.price.multiply(this.quantity);
        if (total.doubleValue() > LIMIT_TOTAL.doubleValue())
            this.notifyObservers();

    }

    private void notifyObservers() {
        observers.forEach(item -> item.execute(this));
        if (observers.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum observador registrado no momento!");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "observers=" + observers +
                ", code=" + code +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public void subscribeEvent(ProductObserver productObserver) {
        this.observers.add(productObserver);
    }

    public void unSubscribeEvent(ProductObserver productObserver) {
        this.observers.remove(productObserver);
    }

    public int getCode() {
        return code;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }
}

class TotalAmountExceeded implements ProductObserver {
    @Override
    public void execute(Product p) {
        System.out.println();
        System.out.println("Total excedido - code-> " + p.getCode() + " - Total-> " + p.getTotal());
        System.out.println();
    }
}

class ProductQuantityLessThanFive implements ProductObserver {
    @Override
    public void execute(Product p) {
        System.out.println();
        System.out.println("Quantidade excedeu - code-> " + p.getCode() + " - Quantidade-> " + p.getQuantity());
        System.out.println();
    }
}

interface ProductObserver {
    void execute(Product p);
}
