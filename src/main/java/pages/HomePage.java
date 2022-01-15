package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage{

    @FindBy(xpath = "//footer[@id='glbfooter']")
    private WebElement footer;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    private void moveToElement(WebElement element){
        Actions moveTo = new Actions(driver);
        moveTo.moveToElement(element);
        moveTo.perform();
    }

    public void moveToFooter(){
        moveToElement(footer);
    }

}
