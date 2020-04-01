package pages;

import browsers.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import waits.Waits;

import static org.apache.log4j.LogManager.getLogger;

class BaseForm {

    private static final Logger logger = getLogger(BaseForm.class);

    BaseForm() {
    }

    boolean isDisplayed(WebElement element) {
        logger.info("Element is displayed");
        return new Waits().elementIsPresent(element);
    }

    boolean chosenPageIsLoaded(By element, String name) {
        String nameOfThePage = Browser.getDriver().findElement(element).getText();
        if (nameOfThePage.contains(name)) {
            logger.info("Page " + name + " is loaded");
            return true;
        }
        logger.info("Page " + name + " didn't load");
        return false;
    }
}