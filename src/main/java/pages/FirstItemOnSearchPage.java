package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstItemOnSearchPage extends BasePage {

    @FindBy(xpath = "//span/a[@id='isCartBtn_btn']")
    private WebElement addToCartButton;

    public FirstItemOnSearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }
}
