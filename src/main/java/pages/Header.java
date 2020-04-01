package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import webelements.Button;

public class Header extends BaseForm {

    Header() {
    }

    private By header = By.id("global_action_menu");
    private By installSteamButtonXpath = By.xpath("//a[contains(@class, 'header_installsteam_btn_content')]");

    public boolean mainPageIsLoaded() {
        return isDisplayed(Browser.getDriver().findElement(header));
    }

    public void clickInstallSteamButton() {
        Button steamButton = new Button(Browser.getDriver().findElement(installSteamButtonXpath));
        steamButton.click();
    }

}