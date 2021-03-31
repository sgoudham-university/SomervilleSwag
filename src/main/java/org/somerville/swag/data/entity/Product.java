package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * A product contains all details about an item within the SomervilleSwag store.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && stockLevel == product.stockLevel && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(imagePath, product.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, price, stockLevel, imagePath);
    }

    @Override
    public String toString() {
        return name;
    }
}
