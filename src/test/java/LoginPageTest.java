import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://www.saucedemo.com");
    }

    @Test
    void loginWithEmptyFields(){
        loginPage.loginBtn.click();
        loginPage.checkErrorMessage(loginPage.usernameIsRequiredError);
    }

    @Test
    void loginWithEmptyPasswordField(){
        loginPage.enterUsernameField(loginPage.validUsername);
        loginPage.loginBtn.click();
        loginPage.checkErrorMessage(loginPage.passwordIsRequiredError);
    }

    @Test
    void loginWithNonExistingUsername(){
        loginPage.enterUsernameField(loginPage.nonExistingUsername);
        loginPage.enterPasswordField(loginPage.validPassword);
        loginPage.loginBtn.click();
        loginPage.checkErrorMessage(loginPage.usernameAndPasswordNotMatchError);
    }

    @Test
    void loginWithValidCredentials(){
        loginPage.enterUsernameField(loginPage.validUsername);
        loginPage.enterPasswordField(loginPage.validPassword);
        loginPage.loginBtn.click();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("inventory.html"));
    }

    @Test
    void loginWithLockedOutUsername(){
        loginPage.enterUsernameField(loginPage.lockedOutUsername);
        loginPage.enterPasswordField(loginPage.validPassword);
        loginPage.loginBtn.click();
        loginPage.checkErrorMessage(loginPage.lockedOutUserError);
    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }
}
