import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;

public class InventoryPageTest {
    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://www.saucedemo.com");
        loginPage.loginWithValidUsernameAndValidPassword();
    }

    @Test
    void openingItemDetailsPage(){
        inventoryPage.inventoryItemTitle(1).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"));
    }

    @Test
    void movingItemToShoppingCart(){
        inventoryPage.clickInventoryItemAddToCartBtn(1);
        inventoryPage.openShoppingCartPage();
        Assert.assertTrue(cartPage.cartItem(1).isDisplayed());
    }

    @Test
    void removingItemFromShoppingCart(){
        inventoryPage.clickInventoryItemAddToCartBtn(1);
        inventoryPage.openShoppingCartPage();
        Assert.assertTrue(cartPage.cartItem(1).isDisplayed());
        cartPage.cartRemoveFromCartBtn(1).click();
    }

    @Test
    void checkoutWithEmptyFields(){
        inventoryPage.openShoppingCartPage();
        cartPage.checkoutBtn.click();
        cartPage.checkoutContinueBtn.click();
        cartPage.checkCheckoutErrorMessage(cartPage.firstNameIsRequiredError);
    }

    @Test
    void checkoutWithFirstName(){
        inventoryPage.openShoppingCartPage();
        cartPage.checkoutBtn.click();
        cartPage.checkoutContinueBtn.click();
        cartPage.fillCheckoutForm(cartPage.validFirstName, "", "");
        cartPage.checkCheckoutErrorMessage(cartPage.lastNameIsRequiredError);
    }

    @Test
    void checkoutWithFirstNameAndLastName(){
        inventoryPage.openShoppingCartPage();
        cartPage.checkoutBtn.click();
        cartPage.fillCheckoutForm(cartPage.validFirstName, cartPage.validLastName, "");
        cartPage.checkCheckoutErrorMessage(cartPage.postalCodeIsRequiredError);
    }

    @Test
    void checkoutWithFilledFields(){
        inventoryPage.openShoppingCartPage();
        cartPage.checkoutBtn.click();
        cartPage.fillCheckoutForm(cartPage.validFirstName, cartPage.validLastName, cartPage.validPostalCode);
        cartPage.checkoutFinishBtn.click();
        cartPage.checkCheckoutCompleteMessage();
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
