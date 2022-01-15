package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    Header header;
    Footer footer;

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
        header = pageFactoryManager.getHeader();
    }

    @And("User checks search field visibility")
    public void checkSearchVisibility() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        header.isSearchFieldVisible();
    }

    @And("User makes search by keyword {string}")
    public void enterKeywordToSearchField(final String keyword) {
        header.enterTextToSearchField(keyword);
    }

    @And("User clicks on search button")
    public void clickSearchButton(){
        header.clickSearchButton();
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
        header.isCategoryButtonVisible();
    }

    @And("User clicks on category button")
    public void userClicksOnCategoryButton() {
        header.clickOnCategoryButton();
    }

    @And("User opens guitar category")
    public void openGuitarCategory() {
        header.clickOnGuitarCategory();
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
        header.isRegistrationButtonVisible();
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
        header.clickOnRegistrationButton();
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
        header.clickOnHomeButton();
    }

    @And("User opens cart")
    public void openCart() {
        header.clickOnCartButton();
    }

    @Then("User checks that cart is empty")
    public void userChecksThatCartIsEmpty() {
        cartPage.isCartIsEmptyMessageVisible();
    }

    @Then("User checks that total sum is correctly calculated")
    public void checkThatTotalSumIsCorrectlyCalculated() {
        cartPage = pageFactoryManager.getCartPage();
        assertEquals(cartPage.getTotalPriceCalculated(), cartPage.getSubtotalPrice(), 0);
    }

    @And("User checks that home button is visible")
    public void checkThatHomeButtonIsVisible() {
        assertTrue(header.isHomeButtonVisible());
    }

    @And("User checks that category button is visible")
    public void checkThatCategoryButtonIsVisible() {
        assertTrue(header.isCategoryButtonVisible());
    }

    @And("User checks that Sing in button is visible")
    public void checkThatSignInButtonIsVisible() {
        assertTrue(header.isSignInButtonVisible());
    }

    @And("User checks that register button is visible")
    public void checkThatRegisterButtonIsVisible() {
        assertTrue(header.isRegistrationButtonVisible());
    }


    @And("User checks that Search box is visible")
    public void checkThatSearchBoxIsVisible() {
        assertTrue(header.isSearchFieldVisible());
    }

    @And("User checks that Search button is visible")
    public void checkThatSearchButtonIsVisible() {
        assertTrue(header.isSearchButtonVisible());
    }

    @And("User checks that cart button is visible")
    public void checkThatCartButtonIsVisible() {
        assertTrue(header.isCartButtonVisible());
    }

    @And("User checks that notifications button is visible")
    public void checkThatNotificationsButtonIsVisible() {
        assertTrue(header.isNotificationsButtonVisible());
    }

    @And("User checks that My ebay button is visible")
    public void checkThatMyEbayButtonIsVisible() {
        assertTrue(header.isMyEbayButtonVisible());
    }

    @And("User checks that watchlist button is visible")
    public void checkThatWatchlistButtonIsVisible() {
        assertTrue(header.isWatchlistButtonVisible());
    }

    @And("User checks that change delivery location button is visible")
    public void checkThatChangeDeliveryLocationButtonIsVisible() {
        assertTrue(header.isChangeDeliveryLocationButtonVisible());
    }

    @And("User checks that Help & Contact button is visible")
    public void checkThatHelpContactButtonIsVisible() {
        assertTrue(header.isHelpContactButtonVisible());
    }




    @After
    public void tearDown(){
        driver.close();
    }


    @When("User move to footer")
    public void moveToFooter() {
        homePage.moveToFooter();
        footer = pageFactoryManager.getFooter();
    }


    @And("User checks that button with functionality responsible for buy is visible")
    public void checkThatButtonWithFunctionalityResponsibleForBuyIsVisible() {
        assertTrue(footer.isBuyButtonVisible());
    }


    @And("User checks that button with functionality responsible for sell is visible")
    public void checkThatButtonWithFunctionalityResponsibleForSellIsVisible() {
        assertTrue(footer.isSellButtonVisible());
    }

    @And("User checks that links to social networks is visible")
    public void checkThatLinksToSocialNetworksIsVisible() {
        assertTrue(footer.isSocialNetworksVisible());
    }

    @And("User checks that About eBay button is visible")
    public void checkThatAboutEbayButtonIsVisible() {
        assertTrue(footer.isAboutEbayButtonVisible());
    }

    @And("User checks that Help & Contact is visible")
    public void checkThatHelpContactIsVisible() {
        assertTrue(footer.isHelpContactButtonVisible());
    }

    @And("User checks that Community button is visible")
    public void checkThatCommunityButtonIsVisible() {
        assertTrue(footer.isCommunityButtonVisible());
    }

    @And("User checks that dropbox site variation is visible")
    public void checkThatDropboxSiteVariationIsVisible() {
        assertTrue(footer.isDropboxSiteVariationVisible());
    }

    @And("User checks that Verify site's SSL certificate button is visible")
    public void checkThatVerifySiteSSSLCertificateButtonIsVisible() {
        assertTrue(footer.isVerifySSLButtonVisible());
    }
}
