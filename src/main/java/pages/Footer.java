package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends BasePage{

    @FindBy(xpath = "//footer//a[text()='Buy']")
    private WebElement buyButton;

    @FindBy(xpath = "//footer//a[text()='Sell']")
    private WebElement sellButton;

    @FindBy(xpath = "//h3[text()='Stay connected']")
    private WebElement socialNetworks;

    @FindBy(xpath = "//a[text()='About eBay']")
    private WebElement aboutEbayButton;

    @FindBy(xpath = "//a[contains(text(),'Help')]")
    private WebElement helpContactButton;

    @FindBy(xpath = "//a[text()='Community']")
    private WebElement communityButton;

    @FindBy(xpath = "//a[@aria-label='eBay sites,United States']")
    private WebElement dropboxSiteVariation;

    @FindBy(xpath = "//a[@title=\"Verify site's SSL certificate\"]")
    private WebElement verifySSLButton;

    public Footer(WebDriver driver) {
        super(driver);
    }

    public boolean isBuyButtonVisible() {
        return buyButton.isDisplayed();
    }

    public boolean isSellButtonVisible() {
        return sellButton.isDisplayed();
    }

    public boolean isSocialNetworksVisible() {
        return socialNetworks.isDisplayed();
    }

    public boolean isAboutEbayButtonVisible() {
        return aboutEbayButton.isDisplayed();
    }

    public boolean isHelpContactButtonVisible() {
        return helpContactButton.isDisplayed();
    }

    public boolean isCommunityButtonVisible() {
        return communityButton.isDisplayed();
    }

    public boolean isDropboxSiteVariationVisible() {
        return dropboxSiteVariation.isDisplayed();
    }

    public boolean isVerifySSLButtonVisible() {
        return verifySSLButton.isDisplayed();
    }
}
