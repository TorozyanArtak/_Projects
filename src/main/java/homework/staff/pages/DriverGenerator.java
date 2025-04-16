package homework.staff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverGenerator {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverGenerator() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());

        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");

        WebDriver driver =  switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver();
        };
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
