package browsers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Browser {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Browser.class);

    public Browser() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.info("Opening browser");
            driver = BrowserFactory.choseBrowser();
        }
        return driver;
    }

    public void getMainUrl(String url) {
        logger.info("Opening " + url);
        getDriver().get(url);
    }

    public void fullScreenMode() {
        logger.info("Making fullscreen Window");
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        logger.info("Closing browser");
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}