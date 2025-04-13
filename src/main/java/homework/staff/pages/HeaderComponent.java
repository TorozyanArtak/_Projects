package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent {
    private final WebDriver driver;
    private final String categoryXPath = "//div[contains(@class,'navbar-collapse')]//div[text()='%s']";

    public void selectCategoryOfHeader(String category) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(categoryXPath, category))));
        el.click();
    }

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }
}
