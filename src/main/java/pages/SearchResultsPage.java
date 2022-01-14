package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[@class='s-item__info clearfix']//h3[@class='s-item__title']")
    private List<WebElement> searchResult;

    @FindBy(xpath = "//ul[@class='srp-results srp-list clearfix']//a[@class='s-item__link'][1]")
    private WebElement firstItem;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSearchResult(){
        return searchResult;
    }

    public int getAmountOfItemsInSearchResult(){
        return searchResult.size();
    }

    public void clickOnFirstItem(){
        firstItem.click();
    }

}
