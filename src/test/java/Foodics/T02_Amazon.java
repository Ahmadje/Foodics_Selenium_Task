package Foodics;

import Pages.P01_HomePage;
import org.testng.annotations.Test;

public class T02_Amazon extends T01_Hooks {

    @Test
    public void testAmazon() throws InterruptedException {
        new P01_HomePage(driver).navigateToLoginPage().loginSteps()
                .navigateToVideoGamesPage()
                    .applyFilter()
                        .applySortBy("Price: High to Low")
                                .addProductsToCart(15000)
                                        .proceedToCheckout()
                                            .verifyItemsInCart();
    }
}
