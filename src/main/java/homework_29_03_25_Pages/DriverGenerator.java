package homework_29_03_25_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverGenerator {
    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get("https://staff.am/");
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }
}
