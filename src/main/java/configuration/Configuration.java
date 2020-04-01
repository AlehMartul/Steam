package configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(Configuration.class);

    private static void readProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(RESOURCES_PATH, "steam.properties")));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static String getData(String data) {
        logger.info("Getting " + data + " from properties");
        initializeProperties();
        return properties.getProperty(data);
    }

    private static void initializeProperties() {
        if (properties == null) {
            readProperties();
        }
    }

    public String getLocalization() {
        String localization = System.getenv("localization");

        if (localization != null) {
            if (localization.equals("ru")) {
                logger.info("Localization is ru");
                return "testDataRu.xml";
            } else if (localization.equals("com")) {
                logger.info("Localization is not ru");
                return "testDataCom.xml";
            } else {
                logger.info("Localization is not allowed: " + localization);
            }
        } else {
            System.out.println("Localization is not specified");
            logger.info("Localization is not specified");
        }
        return "testDataRu.xml";
    }

}