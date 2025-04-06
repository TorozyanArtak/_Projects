package homework_29_03_25_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    protected WebDriver driver = DriverGenerator.getDriver();
    protected WebDriverWait wait;

    public HeaderComponent header = new HeaderComponent(driver);
    public FooterComponent footer = new FooterComponent(driver);

    public BasePage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

}
