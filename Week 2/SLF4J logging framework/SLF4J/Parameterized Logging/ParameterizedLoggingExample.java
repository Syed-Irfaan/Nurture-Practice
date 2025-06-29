package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "John";
        int userId = 101;

        logger.info("User {} has logged in with ID {}", username, userId);

        int items = 5;
        double price = 49.99;

        logger.debug("User {} added {} items to cart, total price: ₹{}", username, items, price);

        boolean success = true;
        logger.warn("Transaction for user {} completed: {}", username, success);
    }
}
