package waits;

import browsers.Browser;
import configuration.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Waits {

    private static final int TIMEOUT = Integer.parseInt(Configuration.getData("timeout"));
    private static final Logger logger = LogManager.getLogger(Waits.class);

    public Waits() {
    }


    public boolean fileIsDownloaded(String name) {
        File file = new File(name);
        logger.info("Waiting for downloading of the file");
        FluentWait wait = new FluentWait(Browser.getDriver()).withTimeout(Duration.ofSeconds(25))
                .pollingEvery(Duration.ofMillis(1000));
        wait.until(x -> file.exists());
        return file.exists();
    }

    public boolean elementIsPresent(WebElement element) {
        return new WebDriverWait(Browser.getDriver(), TIMEOUT).
                until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

}
