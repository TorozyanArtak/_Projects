package homework.staff.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    protected WebDriver driver = DriverGenerator.getDriver();
    protected WebDriverWait wait;
    protected JavascriptExecutor js = (JavascriptExecutor) driver;

    public HeaderComponent header = new HeaderComponent(driver);
    public FooterComponent footer = new FooterComponent(driver);

    public BasePage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement webElement) {
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        js.executeScript("arguments[0].click();", webElement);
    }
}