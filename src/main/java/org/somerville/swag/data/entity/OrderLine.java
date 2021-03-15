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

}
