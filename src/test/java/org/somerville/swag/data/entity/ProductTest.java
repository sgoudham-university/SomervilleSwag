package org.somerville.swag.data.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getFormattedPriceIsFormattedCorrectly() {
        String expectedFormattedPrice = "£19.99";
        BigDecimal actualPrice = BigDecimal.valueOf(19.99);
        Product actualProduct = new Product(1,"Name","Desc", actualPrice,10,"Path");

        String actualFormattedPrice = actualProduct.getFormattedPrice();

        assertEquals(expectedFormattedPrice, actualFormattedPrice);
    }
}