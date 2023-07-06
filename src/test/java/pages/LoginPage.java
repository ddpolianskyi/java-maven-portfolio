package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;

    public String validUsername = "standard_user";
    public String validPassword = "secret_sauce";
    public String nonExistingUsername = "non_existing_user";
    public String lockedOutUsername = "locked_out_user";

    public String usernameIsRequiredError = "Epic sadface: Username is required";
    public String passwordIsRequiredError = "Epic sadface: Password is required";
    public String usernameAndPasswordNotMatchError = "Epic sadface: Username and password do not match any user in this service";
    public String lockedOutUserError = "Epic sadface: Sorry, this user has been locked out.";

    @FindBy(id = "user-name") public WebElement usernameField;
    @FindBy(id = "password") public WebElement passwordField;
    @FindBy(id = "login-button") public WebElement loginBtn;
    @FindBy(css = "[data-test='error']") public WebElement errorMessage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginWithValidUsernameAndValidPassword(){
        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(validPassword);
        loginBtn.click();
    }
    public void enterUsernameField(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void checkErrorMessage(String expectedErrorMessage){
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }
}
