package managar;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(driver);
    }

    public GuitarPage getGuitarPage(){
        return new GuitarPage(driver);
    }

    public ElectricGuitarPage getElectricGuitarPage(){
        return new ElectricGuitarPage(driver);
    }

    public RegisterPage getRegisterPage(){
        return new RegisterPage(driver);
    }

    public FirstItemOnSearchPage getFirstItemOnSearchPage(){
        return new FirstItemOnSearchPage(driver);
    }

    public CartPage getCartPage(){
        return new CartPage(driver);
    }
}
