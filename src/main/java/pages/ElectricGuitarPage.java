package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;


public class ElectricGuitarPage extends BasePage {

    @FindBy(xpath = "//div[@class='srp-sort']//button[@class='fake-menu-button__button expand-btn expand-btn--small']")
    private WebElement sortButton;

    @FindBy(xpath = "//a[contains(@href,'sop=15')]")
    private WebElement sortByPriceShippingLowestFirst;

    @FindBy(xpath = "//div[@class='s-item__detail s-item__detail--primary']/span[@class='s-item__price']")
    private List<WebElement> price;

    @FindBy(xpath = "//div[@class='s-item__detail s-item__detail--primary']//span[contains(text(),'shipping')]")
    private List<WebElement> shipping;

    public ElectricGuitarPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSortButton() {
        moveToElement(sortButton);
        sortButton.click();
    }

    private void moveToElement(WebElement element) {
        Actions moveTo = new Actions(driver);
        moveTo.moveToElement(element);
    }

    public void moveAndClickOnSortByPriceShippingLowestFirst() {
        sortByPriceShippingLowestFirst.click();
    }

    public List<Double> getPrice() {
       List<Double> priceList = new ArrayList<Double>();
       for (int i = 0; i < price.size() - 1; ++i) {
           priceList.add(i, Double.valueOf(price.get(i).getText().split(" to ")[0].replaceAll("[$]", "")));
       }
       return priceList;
    }

    public List<Double> getShipping(){
        List<Double> shippingList = new ArrayList<Double>();
        for (int i = 0; i < shipping.size() - 1; ++i){
            if (shipping.get(i).getText().split(" ")[0].startsWith("$")){
                shippingList.add(i, Double.valueOf(shipping.get(i).getText().split(" ")[0].replaceAll("[$]", "")));
            }
            else shippingList.add(i, Double.valueOf(0));
        }
        return shippingList;
    }

    public List<Double> getPriceAndShippingSum(){
        List<Double> priceAndShippingList = new ArrayList<Double>();
        List<Double> price = getPrice();
        List<Double> shipping = getShipping();
        for(int i = 0; i < getPrice().size(); ++i){
            priceAndShippingList.add(i, Math.round((price.get(i) + shipping.get(i)) * 100.0) / 100.0);
        }
        return priceAndShippingList;
    }

    public  boolean checkThatListIsSorted(List<Double> list) {
        for (int i = 0; i > getPriceAndShippingSum().size() - 1; ++i) {
            double currentSum = getPriceAndShippingSum().get(i);
            double nextSum = getPriceAndShippingSum().get(i+1);
            if (currentSum <= nextSum)
                return true;
            else return false;
        } return true;
    }

}