package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class InventoryPage {
    WebDriver driver;

    @FindBy(className = "shopping_cart_link") public WebElement shoppingCartBtn;
    public WebElement inventoryItemTitle(Integer itemNumber){
        return driver.findElement(By.xpath("//*[@class='inventory_item'][" + itemNumber + "]//*[@class='inventory_item_name']"));
    }
    public WebElement inventoryItemAddToCartBtn(Integer itemNumber){
        return driver.findElement(By.xpath("//*[@class='inventory_item'][" + itemNumber + "]//*[contains(@data-test, 'add-to-cart')]"));
    }
    public WebElement inventoryItemRemoveFromCartBtn(Integer itemNumber){
        return driver.findElement(By.xpath("//*[@class='inventory_item'][" + itemNumber + "]//*[contains(@data-test, 'remove')]"));
    }

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickInventoryItemAddToCartBtn(Integer itemNumber){
        inventoryItemAddToCartBtn(itemNumber).click();
        Assert.assertTrue(inventoryItemRemoveFromCartBtn(itemNumber).isDisplayed());
    }
    public void openShoppingCartPage(){
        shoppingCartBtn.click();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("cart.html"));
    }
}
