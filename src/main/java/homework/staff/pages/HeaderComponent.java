package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String categoryXPath = "//div[contains(@class,'navbar-collapse')]//div[text()='%s']";
    private final By headerRegisterLoc = By.xpath("//a[@href='/register']/div");

    public void selectCategoryOfHeader(String category) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(categoryXPath, category))));
        el.click();
    }

    public void hoverAndClick(String navbarCategory) {
        Actions actions = new Actions(driver);
        WebElement navbarItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[text()='%s']", navbarCategory))));
        actions.moveToElement(navbarItem).perform();
        actions.click(wait.until(ExpectedConditions.elementToBeClickable(headerRegisterLoc))).perform();
    }

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
