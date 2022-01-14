package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import managar.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 60;
    private static final String ERROR_MESSAGE_INVALID_EMAIL = "Email address is invalid. Please enter a valid email address.";
    private static final String ERROR_MESSAGE_PASSWORD_CONTAIN_EMAIL = "Please enter a password that's less like your email address.";

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    GuitarPage guitarPage;
    ElectricGuitarPage electricGuitarPage;
    RegisterPage registerPage;
    PageFactoryManager pageFactoryManager;
    FirstItemOnSearchPage firstItemOnSearchPage;
    CartPage cartPage;

    @Before
    public void testSetUp(){
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url){
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User checks search field visibility")
    public void checkSearchVisibility() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.isSearchFieldVisible();
    }

    @And("User makes search by keyword {string}")
    public void enterKeywordToSearchField(final String keyword) {
        homePage.enterTextToSearchField(keyword);
    }

    @And("User clicks on search button")
    public void clickSearchButton(){
        homePage.clickSearchButton();
    }

    @And("User checks that Search Result Contains {string}")
    public void checkThatSearchResultContainsKeyword(final String keyword) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        for (WebElement element: searchResultsPage.getSearchResult()) {
            assertTrue(element.getText().toLowerCase().contains(keyword));
        }
    }

    @And("User checks that {int} products are displayed")
    public void checkAmountOfProductsAreDisplayed(final int amount ) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        assertEquals(searchResultsPage.getAmountOfItemsInSearchResult(), amount);
    }

    @And("User checks category button visibility")
    public void checkCategoryButtonVisibility() {
        homePage.isCategoryButtonVisible();
    }

    @And("User clicks on category button")
    public void userClicksOnCategoryButton() {
        homePage.clickOnCategoryButton();
    }

    @And("User opens guitar category")
    public void openGuitarCategory() {
        homePage.clickOnGuitarCategory();
    }

    @And("User clicks on electric guitar button")
    public void clickOnElectricGuitarButton() {
        guitarPage = pageFactoryManager.getGuitarPage();
        guitarPage.clickOnElectricGuitarButton();
    }

    @And("User selects sort by Price + Shipping: lowest first")
    public void userSelectsSortByPriceShippingLowestFirst() {
        electricGuitarPage = pageFactoryManager.getElectricGuitarPage();
        electricGuitarPage.clickOnSortButton();
        electricGuitarPage.moveAndClickOnSortByPriceShippingLowestFirst();
    }

    @Then("User checks the correctness of the sorting result")
    public void checkTheCorrectnessOfTheSortingResult() {
        Assert.assertTrue(electricGuitarPage.checkThatListIsSorted(electricGuitarPage.getPrice()));
    }

    @And("User checks registration button visibility")
    public void checkRegistrationButtonVisibility() {
        homePage.isRegistrationButtonVisible();
    }

    @And("User enters first name {string}")
    public void userEntersFirstName(String firstName) {
        registerPage = pageFactoryManager.getRegisterPage();
        registerPage.enterFirstName(firstName);
    }

    @And("User enters last name {string}")
    public void userEntersLastName(String lastName) {
        registerPage.enterLastName(lastName);
    }

    @And("User enters email {string}")
    public void userEntersEmail(String email) {
        registerPage.enterEmail(email);
    }

    @And("User enters password {string}")
    public void userPassword(String password) {
        registerPage.enterPassword(password);
    }

    @Then("User sees an error message")
    public void checkErrorMessage() {
        registerPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, registerPage.getErrorMessage());
        assertEquals(ERROR_MESSAGE_INVALID_EMAIL, registerPage.getErrorMessageText());
    }

    @And("User opens registration page")
    public void userOpensRegistrationPage() {
        homePage.clickOnRegistrationButton();
    }

    @Then("User checks that on register page present SingInButton")
    public void isSingInButtonVisible() {
        registerPage = pageFactoryManager.getRegisterPage();
        assertTrue(registerPage.isSingInButtonVisible());
    }

    @Then("User sees an error message about password that contains email")
    public void checkErrorMessageAboutPasswordThatContainsEmail() {
        registerPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, registerPage.getErrorMessagePassword());
        assertEquals(ERROR_MESSAGE_PASSWORD_CONTAIN_EMAIL, registerPage.getErrorMessagePasswordText());
    }

    @And("User adds first item to cart")
    public void addFirstItemToCart() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.clickOnFirstItem();

        searchResultsPage.switchBetweenWindows();
//        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs2.get(0));
//        driver.close();
//        driver.switchTo().window(tabs2.get(1));

        firstItemOnSearchPage = pageFactoryManager.getFirstItemOnSearchPage();
        firstItemOnSearchPage.clickOnAddToCartButton();
    }

    @And("User removes item from cart")
    public void removeItemFromCart() {
        cartPage = pageFactoryManager.getCartPage();
        cartPage.clickOnRemoveButton();
    }

    @And("User goes to Home page")
    public void userGoesToHomePage() {
        homePage.clickOnHomeButton();
    }

    @And("User opens cart")
    public void openCart() {
        homePage.clickOnCartButton();
    }

    @Then("User checks that cart is empty")
    public void userChecksThatCartIsEmpty() {
        cartPage.isCartIsEmptyMessageVisible();
    }



    @After
    public void tearDown(){
        driver.close();
    }


    @Then("User checks that total sum is correctly calculated")
    public void checkThatTotalSumIsCorrectlyCalculated() {
        cartPage = pageFactoryManager.getCartPage();
        assertEquals(cartPage.getTotalPriceCalculated(), cartPage.getSubtotalPrice(), 0);
    }
}
