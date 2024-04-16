package dto;

public class Product {
    private String gtin;
    private String name;
    private double price;

    public Product(String gtin, String name, double price) {
        this.gtin = gtin;
        this.name = name;
        this.price = price;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
