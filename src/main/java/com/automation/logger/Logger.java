package com.automation.logger;

import org.slf4j.LoggerFactory;

public final class Logger {

    public static org.slf4j.Logger out = LoggerFactory.getLogger(Logger.class.getName());

    private Logger() {
    }
}