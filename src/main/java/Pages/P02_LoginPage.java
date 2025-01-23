package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class P02_LoginPage {
    WebDriver driver;

    public P02_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By mobileInput = By.xpath("//input[@id='ap_email_login' or @name='email']");
    By passwordInput = By.cssSelector("#ap_password");

    public P01_HomePage loginSteps() {
        driver.findElement(mobileInput).sendKeys("01111362956" + Keys.ENTER);
        driver.findElement(passwordInput).sendKeys("P@ssw0rd" + Keys.ENTER);
        return new P01_HomePage(driver);
    }
}
