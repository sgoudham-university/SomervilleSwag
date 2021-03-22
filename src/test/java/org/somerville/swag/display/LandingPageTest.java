package org.somerville.swag.display;

import org.junit.jupiter.api.Test;
import org.somerville.swag.data.entity.Order;
import org.somerville.swag.data.entity.OrderLine;
import org.somerville.swag.data.entity.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LandingPageTest {

    @Test
    public void orderLinesCanBeAddedToOrder() {
        Product actualProduct = new Product(0, "Test Product", "TestDescription", BigDecimal.valueOf(10.99), 5, "TestPath");
        Product expectedProduct = new Product(0, "Test Product", "TestDescription", BigDecimal.valueOf(10.99), 5, "TestPath");
        OrderLine actualOrderLine = new OrderLine(actualProduct, 1);
        Order expectedOrder = new Order();
        expectedOrder.add(actualOrderLine);

        assertEquals(actualOrderLine, expectedOrder.getSingleOrderLine(0));
    }
}