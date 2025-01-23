package Foodics;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.DriverManager;
import utils.JSONUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static utils.ScreenshotUtils.takeScreenShot;

public class T01_Hooks {
    public static WebDriver driver = null;
    public static JSONUtils testData;


    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.eg/?language=en_AE");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public static void takeScreenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenShot(result);
            Allure.attachment(result.getName() + ".png", Files.newInputStream(Paths.get("./screenshots/" + result.getName() + ".png")));
        }
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
    }

    public static String getTestData(String jsonPath) throws FileNotFoundException {
        return testData.getTestData(jsonPath);
    }
}
