package pages;

import browsers.Browser;
import org.openqa.selenium.By;

public class GameWithDiscountPage extends BaseForm {
    public GameWithDiscountPage() {
    }

    private By nameOfAGame = By.xpath("//div[@class='apphub_AppName']");
    private String gameArea = "//div[@class='game_area_purchase_game_wrapper']";
    private By discount = By.xpath(gameArea + "//div[@class='discount_pct']");
    private By oldPrice = By.xpath(gameArea + "//div[@class='discount_original_price']");
    private By newPrice = By.xpath(gameArea + "//div[@class='discount_final_price']");

    public boolean gamePageIsLoaded() {
        return isDisplayed(Browser.getDriver().findElement(nameOfAGame));
    }

    public Double getOldPrice() {
        return Double.parseDouble(Browser.getDriver().findElement(oldPrice).getText().replace("$", ""));
    }

    public Double getNewPrice() {
        return Double.parseDouble(Browser.getDriver().findElement(newPrice).getText().replace("$", "").
                replace(" USD", ""));
    }

    public Double getDiscountValue() {
        return Double.parseDouble(Browser.getDriver().findElement(discount).getText().replace("%", "").replace("-", ""));
    }
}
