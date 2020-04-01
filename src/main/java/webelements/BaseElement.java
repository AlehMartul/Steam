package webelements;

import browsers.Browser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.AssertJUnit.assertNotNull;

public class BaseElement {
    private WebElement webElement;
    private static final Logger logger = LogManager.getLogger(BaseElement.class);

    public BaseElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public BaseElement() {
    }

    public void click() {
        assertNotNull(webElement);
        logger.info("Clicking on button with text '" + getText() + "'");
        webElement.click();
    }

    public void select(By webElement) {
        logger.info("Moving mouse on element '" + getText() + "'");
        Actions action = new Actions(Browser.getDriver());
        action.moveToElement(Browser.getDriver().findElement(webElement));
        action.perform();
    }

    private String getText() {
        return webElement.getText();
    }
}