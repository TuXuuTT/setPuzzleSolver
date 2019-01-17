package com.automation.environment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfigurator {

    protected static final Logger Logger.out = LogManager.getLogger(EnvironmentConfigurator.class);
    private static volatile EnvironmentConfigurator environmentConfigurator;
    private static Properties properties = new Properties();

    private EnvironmentConfigurator() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream i18nStream = loader.getResourceAsStream("env.properties");
        try {
            properties.load(i18nStream);
            i18nStream.close();
        } catch (IOException e) {
            Logger.out.error("", e);
        }
    }

    public static EnvironmentConfigurator getInstance() {
        EnvironmentConfigurator sysProps = environmentConfigurator;
        if (sysProps == null) {
            synchronized (EnvironmentConfigurator.class) {
                sysProps = environmentConfigurator;
                if (sysProps == null) {
                    try {
                        environmentConfigurator = sysProps = new EnvironmentConfigurator();
                    } catch (IOException e) {
                        Logger.out.error("", e);
                    }
                }
            }
        }
        return sysProps;
    }

    //Get value of System variable (which we are providing from pom.xml or command line).
    //If not specified - then get default value from properties file

    public String getAppUrl() {
        return System.getProperty("app.url", properties.get("app.url").toString());
    }

    public String getBrowserClient() {
        return System.getProperty(properties.get("browserClient").toString(), "gc");
    }
}
