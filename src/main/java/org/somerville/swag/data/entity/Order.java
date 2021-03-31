package org.somerville.swag.data.entity;

import org.somerville.swag.data.exception.FileWriterException;
import org.somerville.swag.data.source.MyFileWriter;
import org.somerville.swag.data.source.MyTextFileWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This represents an Order that contains many OrderLines.
 * A customer can only have one order at a time.
 */
public class Order {
    private int orderId;
    private int customerId;
    private List<OrderLine> orderLines = new ArrayList<>();

    private MyFileWriter myFileWriter = new MyTextFileWriter();

    public Order() {
        orderId++;
    }

    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public void add(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void refresh() {
        orderLines.clear();
        orderId++;
    }

    public String getReceipt(Customer customer) {
        StringBuilder receiptBuilder = new StringBuilder("Thanks For Ordering " + customer.getForename() + "! Your Swag Will Be With You Soon :D\n\nPurchased Swag:");
        for (OrderLine orderLine : orderLines) {
            Product product = orderLine.getProduct();
            receiptBuilder.append("\n")
                    .append(orderLine.getQuantity())
                    .append(" x ")
                    .append(product.getName())
                    .append(" [")
                    .append(orderLine.getTotalRowPrice())
                    .append("]");
        }
        receiptBuilder.append("\n\nGrand Total: ").append(getFormattedTotal());
        String receipt = receiptBuilder.toString();

        exportReceipt(customer.getCustomerId(), customer.getCurrentOrder().getOrderId(), receipt);

        return receipt;
    }

    private void exportReceipt(int customerId, int orderId, String receipt) {
        myFileWriter.setFileToWrite("src/main/resources/customer.orders/Customer" + customerId + "-Order-" + orderId + ".txt");
        try {
            myFileWriter.writeToFile(receipt, false);
        } catch (FileWriterException fwe) {
            fwe.printStackTrace();
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderLine order : orderLines) {
            total = total.add(order.getProduct().getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        return total;
    }

    public String getFormattedTotal() {
        return "£" + getTotal().setScale(2, RoundingMode.HALF_UP);
    }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }
    public void setMyFileWriter(MyFileWriter myFileWriter) { this.myFileWriter = myFileWriter; }

    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public List<OrderLine> getOrderLines() { return orderLines; }
    public MyFileWriter getMyFileWriter() { return myFileWriter; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customerId == order.customerId && Objects.equals(orderLines, order.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, orderLines);
    }
}
