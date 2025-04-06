package homework_29_03_25_Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterComponent {
    private final WebDriver driver;

    public void selectCategoryOfFooter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a//div[text()='View all companies'])[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    public FooterComponent(WebDriver driver) {
            this.driver = driver;
        }
}