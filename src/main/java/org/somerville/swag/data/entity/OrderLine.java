package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderLine {

    private Product product;
    private int quantity;

    public OrderLine() { }

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalRowPrice() {
        BigDecimal productPrice = new BigDecimal(String.valueOf(product.getPrice()));
        BigDecimal productQuantity = new BigDecimal(quantity);
        return "£" + productPrice.multiply(productQuantity).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(product, orderLine.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
