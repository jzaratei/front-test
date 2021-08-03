package common.Managers;

import common.exceptions.ExceptionController;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;
import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance;
    private final Properties properties;

    public static org.apache.logging.log4j.Logger Logger = LogManager.getLogger(PropertiesManager.class.getName());

    private PropertiesManager() {
        properties = new Properties();
        loadLocalProperties();
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private void loadLocalProperties() {
        try {
            Logger.debug("Loading properties...");
            properties.load(Objects.requireNonNull(PropertiesManager.class.getClassLoader()
                    .getResourceAsStream("project_config.properties")));
        } catch (Exception exception) {
            ExceptionController.hookFail("Properties cannot be load! " + exception);
        }
    }
}