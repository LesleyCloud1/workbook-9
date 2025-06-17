package com.pluralsight.NorthwindTradersSpringBoot.models;

public class Product {
    private int productId;
    private String name;
    private int categoryId;
    private double price;

    // Constructor
    public Product(int productId, String name, int categoryId, double price) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getPrice() {
        return price;
    }

    // (Optional) Setters if you need to allow changes
}
