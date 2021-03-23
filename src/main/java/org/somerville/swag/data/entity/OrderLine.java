package org.somerville.swag.data.entity;

public class OrderLine {

    private Product productId;
    private int quantity;

    public OrderLine(){

    }

    public OrderLine(Product productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
