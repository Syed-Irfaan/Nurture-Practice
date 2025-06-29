package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debugging application startup.");
        logger.info("User has accessed the application.");
        logger.warn("This is a warning about memory usage.");
        logger.error("This is an error message for testing.");
    }
}
