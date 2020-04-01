package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;
import webelements.Label;

public class MainPage extends BaseForm {

    private By dropdown = By.xpath("//span//a[contains(@href, 'games')]");
    public Header header;

    public MainPage() {
        this.header = new Header();
    }

    private void clickOnGameStyle(By element) {
        Button gameGenre = new Button(Browser.getDriver().findElement(element));
        gameGenre.click();
    }

    private By setGameGenreLocator(String gameGenre) {
        return By.xpath(String.format
                ("//div[contains(@class,'popup_block_new')]//a[contains(text(),'%s')]", gameGenre));
    }

    public void menuGoTo(String gameGenre) {
        Label label = new Label(Browser.getDriver().findElement(dropdown));
        label.select(dropdown);
        clickOnGameStyle(setGameGenreLocator(gameGenre));
    }
}
