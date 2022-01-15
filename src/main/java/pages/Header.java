package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(xpath = "//a[@id='gh-la']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[@aria-label='Your shopping cart']")
    private WebElement cartButton;

    @FindBy(xpath ="//button[@id='gh-shop-a']")
    private WebElement categoryButton;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@class='gh-tb ui-autocomplete-input']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@id='gh-top']//span[@id='gh-ug-flex']/a")
    private WebElement registrationButton;

    @FindBy(xpath = "//div[@role='navigation']//a[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(@href,'Guitar')]")
    private WebElement guitarCategory;

    @FindBy(xpath = "//button[@class='gh-control ghn-b gh-eb-li-a']") // search!!
    private WebElement notificationsButton;

    @FindBy(xpath = "//a[@class='gh-eb-li-a gh-rvi-menu']")
    private WebElement myEbayButton;

    @FindBy(xpath = "//a[@class='gh-eb-li-a gh-rvi-menu watchlist-menu']") // search!!
    private WebElement watchlistButton;

    @FindBy(xpath = "//button[@aria-controls='gh-shipto-click-o']") // search!!
    private WebElement changeDeliveryLocationButton;

    @FindBy(xpath = "//div[@aria-label='Account']//a[contains(text(),'Help')]") // search!!
    private WebElement helpContactButton;

    public Header(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeButtonVisible() {
        return homeButton.isDisplayed();
    }

    public boolean isCartButtonVisible() {
        return cartButton.isDisplayed();
    }

    public boolean isCategoryButtonVisible() {
        return categoryButton.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        return searchButton.isDisplayed();
    }

    public boolean isSearchFieldVisible() {
        return searchField.isDisplayed();
    }

    public boolean isRegistrationButtonVisible() {
        return registrationButton.isDisplayed();
    }

    public boolean isSignInButtonVisible() {
        return signInButton.isDisplayed();
    }

    public boolean isNotificationsButtonVisible() {
        return notificationsButton.isDisplayed();
    }

    public boolean isMyEbayButtonVisible() {
        return myEbayButton.isDisplayed();
    }

    public boolean isWatchlistButtonVisible() {
        return watchlistButton.isDisplayed();
    }

    public boolean isChangeDeliveryLocationButtonVisible() {
        return changeDeliveryLocationButton.isDisplayed();
    }

    public boolean isHelpContactButtonVisible() {
        return helpContactButton.isDisplayed();
    }


    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickOnCategoryButton() {
        categoryButton.click();
    }

    public void clickOnRegistrationButton(){
        registrationButton.click();
    }

    public  void clickOnHomeButton(){
        homeButton.click();
    }

    public void clickOnCartButton(){
        cartButton.click();
    }

    public void clickOnGuitarCategory(){
        guitarCategory.click();
    }
}
