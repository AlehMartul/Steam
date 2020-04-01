package pages;

import browsers.Browser;
import org.openqa.selenium.By;
import waits.Waits;
import webelements.Button;

public class InstallToSteamPage extends BaseForm {

    public InstallToSteamPage() {
    }

    private By installSteamNow = By.xpath("//a[contains(@class, 'about_install_steam_link')]");

    public boolean welcomeToSteamPageIsLoaded() {
        return isDisplayed(Browser.getDriver().findElement(installSteamNow));
    }

    public void clickInstallSteamNowButton() {
        Button installSteamNowButton = new Button(Browser.getDriver().findElement(installSteamNow));
        installSteamNowButton.click();
    }

    public boolean setupFileIsDownloaded(String name) {
        return new Waits().fileIsDownloaded(name);
    }
}

