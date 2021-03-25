package org.somerville.swag.data.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private int customerId;
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() { }

    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
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
        for (OrderLine order : orderLines) {
            total = total.add(order.getProduct().getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        return total;
    }

    public String getFormattedTotal(){return "£" + getTotal().setScale(2, RoundingMode.HALF_UP);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && customerId == order.customerId && Objects.equals(orderLines, order.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderLines);
    }
}
