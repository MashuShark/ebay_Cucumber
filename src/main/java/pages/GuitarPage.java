package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuitarPage extends BasePage{

    @FindBy(xpath = "//a[text()='Electric Guitars']")
    private WebElement electricGuitarButton;

    public GuitarPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnElectricGuitarButton(){
        electricGuitarButton.click();
    }
}
