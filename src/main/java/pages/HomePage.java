package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//input[@class='gh-tb ui-autocomplete-input']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath ="//button[@id='gh-shop-a']")
    private WebElement categoryButton;

    @FindBy(xpath = "//a[contains(@href,'Guitar')]")
    private WebElement guitarCategory;

    @FindBy(xpath = "//div[@id='gh-top']//span[@id='gh-ug-flex']/a")
    private WebElement registrationButton;

    @FindBy(xpath = "//a[@id='gh-la']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[@aria-label='Your shopping cart']")
    private WebElement cartButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void isSearchFieldVisible() {
        searchField.isDisplayed();
    }

    public void isCategoryButtonVisible() {
        categoryButton.isDisplayed();
    }

    public void isRegistrationButtonVisible() {
        registrationButton.isDisplayed();
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

    public void clickOnGuitarCategory(){
        guitarCategory.click();
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

}
