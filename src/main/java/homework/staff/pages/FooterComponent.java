package homework.staff.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterComponent {
    private final WebDriver driver;
    @FindBy(xpath = "(//a//div[text()='View all companies'])[2]")
    private WebElement viewAllCompanies;

    public void selectCategoryOfFooter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(viewAllCompanies));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", el);
        }
    }

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}