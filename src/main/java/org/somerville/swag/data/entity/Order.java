package org.somerville.swag.data.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private OrderLine orderLine;
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    public Order() { }

    public Order(int orderId, int customerId){
        setOrderId(orderId);
        setCustomerId(customerId);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }


    //change this shit to make use of class instances
    public void add(OrderLine orderLine){
        orderLines.add(orderLine);
    }

    public OrderLine getSingleOrderLine(int index){
        return orderLines.get(index);
    }
}
