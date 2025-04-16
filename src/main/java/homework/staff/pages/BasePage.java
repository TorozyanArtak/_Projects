package homework.staff.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public HeaderComponent header;
    public FooterComponent footer;

    public BasePage() {
        this.driver = DriverGenerator.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;

        this.header = new HeaderComponent(driver);
        this.footer = new FooterComponent(driver);

        PageFactory.initElements(driver, this);
    }
}