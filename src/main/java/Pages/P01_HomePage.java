package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By loginBtn = By.cssSelector("#nav-signin-tooltip");
    By allHamburgerMenu = By.cssSelector("#nav-hamburger-menu");
    By seeMoreBtn = By.xpath("//a[@class='hmenu-item hmenu-compressed-btn']");
    By videoGamesBtn = By.xpath("//a[@class='hmenu-item' and @data-menu-id='16']");
    By allVideoGamesBtn = By.xpath("(//a[@class='hmenu-item'] [contains(.,'All Video Games')])[2]");

    public P02_LoginPage navigateToLoginPage() {
        driver.findElement(loginBtn).click();
        return new P02_LoginPage(driver);
    }

    public P03_AllVideoGamesPage navigateToVideoGamesPage() {
        driver.findElement(allHamburgerMenu).click();
        driver.findElement(seeMoreBtn).click();
        driver.findElement(videoGamesBtn).click();
        driver.findElement(allVideoGamesBtn).click();
        return new P03_AllVideoGamesPage(driver);
    }
}
