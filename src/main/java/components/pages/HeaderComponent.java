package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String categoryXPath = ".//div[contains(@class,'navbar-collapse')]//div[text()='%s']";
    private final String itemOfCategoryXpath = ".//ancestor::div[2]/following-sibling::div//div[text()='%s']";

    @FindBy(xpath = "//*[@class = 'container']")
    private WebElement baseElement;

    public void selectCategoryOfHeader(String category) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(baseElement.findElement(By.xpath(String.format(categoryXPath, category)))));
        el.click();
    }

    public void hoverAndClick(String navbarCategory, String itemOfCategory) {
        Actions actions = new Actions(driver);
        WebElement navbarItem = wait.until(ExpectedConditions.elementToBeClickable(baseElement.findElement(By.xpath(String.format(categoryXPath, navbarCategory)))));
        actions.moveToElement(navbarItem).perform();
        actions.click(wait.until(ExpectedConditions.visibilityOf(navbarItem.findElement(By.xpath(String.format(itemOfCategoryXpath,itemOfCategory)))))).perform();
    }

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
}
