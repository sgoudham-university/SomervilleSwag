package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockLevel;
    private String imagePath;

    public Product() { }

    public Product(int productId, String name, String description, BigDecimal price, int stockLevel, String imagePath) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
        this.imagePath = imagePath;
    }

    public String getFormattedPrice(){
        return "£" + this.price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setProductId(int productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getStockLevel() { return stockLevel; }
    public String getImagePath() { return imagePath; }
}
