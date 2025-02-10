package nl.han.jeremydalm.datasource.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private final Properties properties;

    public DatabaseProperties() {
        // Set the logger & properties.
        Logger logger = Logger.getLogger(getClass().getName());
        properties = new Properties();

        try {
            // Attempt to load the database.properties resource sa a stream.
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            // Attempt to load the driver.
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String getConnectionString() {
        return properties.getProperty("connectionString");
    }
}
