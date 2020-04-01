package browsers;

import configuration.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

class BrowserFactory {

    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);

    private WebDriver chromeSettings() {
        logger.info("Chrome browser is setting");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        chromePrefs.put("safebrowsing.enabled", "true");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(cap);
    }

    private WebDriver firefoxSettings() {
        logger.info("Firefox browser is setting");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", 8080);
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.merge(options);
        return new FirefoxDriver();
    }

    static WebDriver choseBrowser() {

        if ("chrome".equals(Configuration.getData("browser"))) {
            logger.info("Chrome browser is selected");
            return new BrowserFactory().chromeSettings();

        } else if ("firefox".equals(Configuration.getData("browser"))) {
            logger.info("Firefox browser is selected");
            return new BrowserFactory().firefoxSettings();

        } else {
            logger.info("Chrome browser is opening as default");
            return new BrowserFactory().chromeSettings();

        }
    }
}
