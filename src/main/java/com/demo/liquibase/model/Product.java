package com.demo.liquibase.model;

public class Product {

    private int sku;
    private String title;
    private String description;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int sku, String title, String description, double price, int quantity) {
        this.sku = sku;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getSKU() {
        return sku;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "SKU=" + sku +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
