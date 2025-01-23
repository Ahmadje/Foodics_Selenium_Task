package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P03_AllVideoGamesPage {
    WebDriver driver;

    public P03_AllVideoGamesPage(WebDriver driver) {
        this.driver = driver;
    }

    By freeShippingFilterBtn = By.xpath("(//i[@class='a-icon a-icon-checkbox'])[1]");
    By newFilterBtn = By.xpath("(//ul[@aria-labelledby='p_n_condition-type-title']//span[contains(.,'New')]//parent::a)[1]");
    By sortByDropdown = By.xpath("//span[@id='a-autoid-0']");
//    By sortByHighestPrice = By.xpath("//a[@id='s-result-sort-select_2]'");

    public P03_AllVideoGamesPage applyFilter() {
        driver.findElement(freeShippingFilterBtn).click();
        driver.findElement(newFilterBtn).click();
        return this;
    }

    public P03_AllVideoGamesPage applySortBy(String sortOption) {
        driver.findElement(sortByDropdown).click();
        WebElement option = driver.findElement(By.xpath("//a[text()='" + sortOption + "']"));
        option.click();
        return this;
    }

    public P04_CartPage addProductsToCart(int maxPrice) throws InterruptedException {
        boolean productFound = false;

        while (!productFound) {
            // Find all products on the current page
            Thread.sleep(2000);
            List<WebElement> products = driver.findElements(By.className("a-price-whole"));

            for (WebElement product : products) {
                try {
                    int price = Integer.parseInt(product.getText().replaceAll("[^0-9]", ""));
                    // If product price is less than maxPrice
                    if (price < maxPrice) {
                        // Click on the product
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
                        product.click();
                        new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
                        // Click the "Add to Cart" button
                        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
                        addToCartButton.click();
                        Thread.sleep(2000);
                        handlePopup();
                        // Wait for the confirmation that the item has been added to the cart
                        new WebDriverWait(driver, Duration.ofSeconds(5))
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Added to cart')]")));

                        // Go back to the search results
                        driver.navigate().back();
                        driver.navigate().back();

                        // Wait for the search results to reload
                        new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(ExpectedConditions.visibilityOfElementLocated(By.className("a-price-whole")));

                        productFound = true;  // Exit the loop after adding the product to cart
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse price: " + product.getText());
                }
            }

            // If no product under maxPrice was found, move to the next page
            if (!productFound) {
                try {
                    WebElement nextPageButton = driver.findElement(By.cssSelector("[aria-label^=\"Go to next page\"]"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPageButton);
                    nextPageButton.click();

                } catch (NoSuchElementException e) {
                    System.out.println(productFound);
                }
            }
        }
        return new P04_CartPage(driver);
    }

    // Function to handle popups
    private void handlePopup() {
        try {
            // Wait for popup to appear, with a timeout
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-labelledby=\"attachSiNoCoverage-announce\"]")));

            // Close the popup
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-labelledby=\"attachSiNoCoverage-announce\"]")));
            closeButton.click();

            System.out.println("Popup closed.");

            // wait until the popup disappears
            wait.until(ExpectedConditions.invisibilityOf(popup));
        } catch (TimeoutException e) {
            // Popup did not appear, continue the process
            System.out.println("No popup appeared.");
        }
    }
}

