package org.somerville.swag.data.entity;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private int customerId;
    private OrderLine orderLine;
    private ArrayList<OrderLine> orderLinesList= new ArrayList<OrderLine>();

    public void Order(){

    }

    public void Order(int orderId, int customerId){
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

    public ArrayList<OrderLine> getOrderLinesList() {
        return orderLinesList;
    }

    public void setOrderLinesList(ArrayList<OrderLine> orderLinesList) {
        this.orderLinesList = orderLinesList;
    }


    //change this shit to make use of class instances
    public ArrayList<OrderLine> addOrderLineToOrder(ArrayList<OrderLine> existingOrderLines, OrderLine orderLine){
        this.orderLinesList = existingOrderLines;
        existingOrderLines.add(orderLine);
        return orderLinesList;
    }

    public OrderLine getSingleOrderLine(ArrayList<OrderLine> orderLines, int index){
        return orderLines.get(index);
    }
}
