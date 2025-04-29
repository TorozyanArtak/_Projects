package components.pages;

import homework.staff.pages.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterComponent {
    WebDriverWait wait;
    private final WebDriver driver;
    @FindBy(xpath = "(//a//div[text()='View all companies'])[2]")
    private WebElement viewAll;

    public FooterComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public SearchResultPage selectCategoryOfFooter() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(viewAll));
        actions.scrollToElement(viewAll)
                .moveToElement(viewAll)
                .click(viewAll)
                .build()
                .perform();
        return new SearchResultPage();
    }
}