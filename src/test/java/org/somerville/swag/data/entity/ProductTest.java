package org.somerville.swag.data.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getFormattedPriceReturnsCorrectFormat() {
        BigDecimal bd = BigDecimal.valueOf(19.99);
        Product p = new Product(1,"Name","Desc", bd,10,"Path");
        assertEquals("£19.99", p.getFormattedPrice());
    }
}