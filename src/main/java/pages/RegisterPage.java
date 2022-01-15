package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='Email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//div[@class='email-comp autocompleteCntr']//span[@id='Email_err']")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[@class='signInLink']/a[@class='action-link']")
    private WebElement singInButton;

    @FindBy(xpath = "//span[@id='password_err']")
    private WebElement errorMessagePassword;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstNameText){
        firstName.clear();
        firstName.sendKeys(firstNameText);
    }

    public void enterLastName(String lastNameText){
        lastName.clear();
        lastName.sendKeys(lastNameText);
    }

    public void enterEmail(String emailText){
        email.clear();
        email.sendKeys(emailText);
    }

    public void enterPassword (String passwordText){
        password.clear();
        password.sendKeys(passwordText);
    }

    public WebElement getErrorMessage(){
        return errorMessage;
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }

    public boolean isSingInButtonVisible() {
        return singInButton.isDisplayed();
    }

    public WebElement getErrorMessagePassword(){
        return  errorMessagePassword;
    }

    public String getErrorMessagePasswordText(){
        return  errorMessagePassword.getText();
    }


}
