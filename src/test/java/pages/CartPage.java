package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {
    WebDriver driver;

    public String validFirstName = "John";
    public String validLastName = "Doe";
    public String validPostalCode = "20004";

    public String firstNameIsRequiredError = "Error: First Name is required";
    public String lastNameIsRequiredError = "Error: Last Name is required";
    public String postalCodeIsRequiredError = "Error: Postal Code is required";

    public WebElement cartItem(Integer itemNumber){
        return driver.findElement(By.xpath("//*[@class='cart_item'][" + itemNumber + "]"));
    }
    public WebElement cartRemoveFromCartBtn(Integer itemNumber){
        return driver.findElement(By.xpath("//*[@class='cart_item'][" + itemNumber + "]//*[contains(@data-test, 'remove')]"));
    }
    @FindBy(id = "checkout") public WebElement checkoutBtn;
    @FindBy(id = "first-name") public WebElement checkoutFirstNameField;
    @FindBy(id = "last-name") public WebElement checkoutLastNameField;
    @FindBy(id = "postal-code") public WebElement checkoutPostalCodeField;
    @FindBy(css = "[data-test='error']") public WebElement checkoutErrorMessage;
    @FindBy(id = "continue") public WebElement checkoutContinueBtn;
    @FindBy(id = "finish") public WebElement checkoutFinishBtn;
    @FindBy(className = "complete-text") public WebElement checkoutCompleteMessage;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCheckoutFirstName(String firstName){
        checkoutFirstNameField.clear();
        checkoutFirstNameField.sendKeys(firstName);
    }
    public void enterCheckoutLastName(String lastName){
        checkoutLastNameField.clear();
        checkoutLastNameField.sendKeys(lastName);
    }
    public void enterCheckoutPostalCode(String postalCode){
        checkoutPostalCodeField.clear();
        checkoutPostalCodeField.sendKeys(postalCode);
    }
    public void fillCheckoutForm(String firstName, String lastName, String postalCode){
        enterCheckoutFirstName(firstName);
        enterCheckoutLastName(lastName);
        enterCheckoutPostalCode(postalCode);
        checkoutContinueBtn.click();
    }
    public void checkCheckoutErrorMessage(String expectedErrorMessage){
        Assert.assertTrue(checkoutErrorMessage.isDisplayed());
        Assert.assertEquals(checkoutErrorMessage.getText(), expectedErrorMessage);
    }
    public void checkCheckoutCompleteMessage(){
        Assert.assertTrue(checkoutCompleteMessage.isDisplayed());
    }
}
