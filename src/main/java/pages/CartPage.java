package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

    @FindBy(xpath = "//button[@data-test-id='cart-remove-item']")
    private WebElement removeButton;

    @FindBy(xpath = "//div[@class='empty-cart']")
    private WebElement cartIsEmptyMessage;

    @FindBy(xpath = "//div[@data-test-id='ITEM_TOTAL']//span[contains(text(),'US')]")
    private WebElement itemsPrice;

    @FindBy(xpath = "//div[@data-test-id='SHIPPING']//span[contains(text(),'US')]")
    private WebElement shippingPrice;

    @FindBy(xpath = "//div[@data-test-id='SUBTOTAL']//span[contains(text(),'US')]")
    private WebElement subtotalPrice;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }

    public void isCartIsEmptyMessageVisible() {
        cartIsEmptyMessage.isDisplayed();
    }

    public double getShippingPrice(){
        return Double.parseDouble(itemsPrice.getText().split(" ")[1].replaceAll("[$,]", ""));
    }

    public double getItemsPrice(){
        return Double.parseDouble(shippingPrice.getText().split(" ")[1].replaceAll("[$,]", ""));
    }

    public double getSubtotalPrice(){
        return Double.parseDouble(subtotalPrice.getText().split(" ")[1].replaceAll("[$,]", ""));
    }

    public double getTotalPriceCalculated(){
        return getItemsPrice() + getShippingPrice();
    }
}
