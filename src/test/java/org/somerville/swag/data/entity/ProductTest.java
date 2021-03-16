package org.somerville.swag.data.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getFormattedPriceReturnsCorrectFormat() {
        BigDecimal actualBigDecimal = BigDecimal.valueOf(19.99);
        Product expectedProduct = new Product(1,"Name","Desc", actualBigDecimal,10,"Path");
        assertEquals("£19.99", expectedProduct.getFormattedPrice());
    }
}