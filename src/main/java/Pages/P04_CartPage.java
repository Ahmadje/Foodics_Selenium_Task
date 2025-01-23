package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



import java.time.Duration;

public class P04_CartPage {
    WebDriver driver;

    public P04_CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public P04_CartPage proceedToCheckout() {
        WebElement checkoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("nav-cart")));
        checkoutButton.click();
        return this;
    }

    // Method to verify that the cart contains the expected items
    public void verifyItemsInCart() {
        WebElement cartTotal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));
        Assert.assertTrue(cartTotal.isDisplayed(), "Cart total is not displayed.");
        Assert.assertTrue(Integer.parseInt(cartTotal.getText()) > 0, "Cart total is not greater than 0.");
    }

}
