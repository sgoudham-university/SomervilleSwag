package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private OrderLine orderLine;
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() { }

    public Order(int orderId, int customerId) {
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

    public void add(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public OrderLine getSingleOrderLine(int index) {
        return orderLines.get(index);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(OrderLine order : orderLines){
            total = total.add(order.getProduct().getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        return  total;
    }

    public String getFormattedTotal(){return "£" + getTotal().setScale(2, RoundingMode.HALF_UP);}
}
