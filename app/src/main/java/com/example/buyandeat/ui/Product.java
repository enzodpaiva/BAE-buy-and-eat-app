package com.example.buyandeat.ui;

public class Product {

    private String id;
    private String name;
    private String description;
    private double price;
    private int imageResource;
    private int quantity;

    public Product(String id, String name, String description, double price, int imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
        this.quantity = 1;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
