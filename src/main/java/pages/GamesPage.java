package pages;

import browsers.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webelements.Button;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.log4j.LogManager.getLogger;

public class GamesPage extends BaseForm {
    public GamesPage() {
    }

    private static final Logger logger = getLogger(GamesPage.class);

    private By topSellers = By.xpath(".//div[contains(@id, 'tab_select_TopSellers')]");
    private By gamePageHeader = By.xpath(".//h2[@class='pageheader']");
    private By discountOfGame = By.xpath(".//div[@class='discount_pct']");
    private By discount = By.xpath(".//div[@class='discount_pct']");
    private By oldPrice = By.xpath(".//div[@class='discount_original_price']");
    private By newPrice = By.xpath(".//div[@class='discount_final_price']");
    private By gamesWithDiscount = By.xpath("//div[@id='TopSellersRows']//div[@class='discount_original_price']" +
            "//ancestor::div[@class='discount_block tab_item_discount']");

    public void clickOnTopSellers() {
        Button topSellersButton = new Button(Browser.getDriver().findElement(topSellers));
        topSellersButton.click();
    }

    public List<WebElement> getDiscountList() {
        return Browser.getDriver().findElements(gamesWithDiscount);
    }

    private Map<WebElement, Double> getDiscounts(List<WebElement> games) {
        Map<WebElement, Double> discountsOfGames = new HashMap<>();
        for (WebElement game : games) {
            Double discountValueInPercentage = Double.parseDouble(game.findElement(discountOfGame).getText()
                    .replace("%", "")
                    .replace("-", ""));
            discountsOfGames.put(game, discountValueInPercentage);
        }
        return discountsOfGames;
    }

    public WebElement getMaxDiscountGame(List<WebElement> games) {
        Map<WebElement, Double> discountsOfGames = getDiscounts(games);
        Map.Entry<WebElement, Double> maxDiscountInGame = null;
        for (Map.Entry<WebElement, Double> entry : discountsOfGames.entrySet()) {
            if (maxDiscountInGame == null || entry.getValue().compareTo(maxDiscountInGame.getValue()) >= 0) {
                maxDiscountInGame = entry;
            }
        }
        return maxDiscountInGame.getKey();
    }

    public WebElement getMinDiscountGame(List<WebElement> games) {
        logger.info("Making a list of a games with discount");
        Map<WebElement, Double> discountsOfGames = getDiscounts(games);
        Map.Entry<WebElement, Double> minDiscountInGame = null;
        for (Map.Entry<WebElement, Double> entry : discountsOfGames.entrySet()) {
            if (minDiscountInGame == null || entry.getValue().compareTo(minDiscountInGame.getValue()) < 100) {
                minDiscountInGame = entry;
            }
        }
        return minDiscountInGame.getKey();
    }

    public void chooseGame(WebElement game) {
        game.click();
    }

    public Double getGameInitialPrice(WebElement game) {
        logger.info("Getting initial price of a game");
        return Double.parseDouble(game.findElement(oldPrice).getText().replace("$", ""));
    }

    public Double getGameDiscountValue(WebElement game) {
        logger.info("Getting discount value of a game");
        return Double.parseDouble(game.findElement(discount).getText().replace("%", "").replace("-", ""));
    }

    public Double getGameNewPrice(WebElement game) {
        logger.info("Getting new price of a game");
        return Double.parseDouble(game.findElement(newPrice).getText().replace("$", ""));
    }

    public boolean genrePageIsLoaded(String toSearch) {
        return chosenPageIsLoaded(gamePageHeader, toSearch);
    }
}
