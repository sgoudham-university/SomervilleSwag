package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockLevel;
    private String imagepath;

    public Product(int productId, String name, String description, BigDecimal price,
                   int stockLevel, String imagepath) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
        this.imagepath = imagepath;
    }

    public String getFormattedPrice(){
        return "£" + this.price.setScale(2, RoundingMode.HALF_UP);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
