package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class IndustriesResultPage {
    private final WebDriver driver;
    private final By searchInput = By.xpath("//input[@placeholder='Enter keywords...']");
    private final By enterSearchButton = By.xpath("//div[text()='Search']");
    private final By companiesResult = By.xpath("//div[img[@alt='company-logo']]/div/div/div/div[string-length(text()) > 0]");

    public IndustriesResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterKeywordInSearchField(String keyword) throws InterruptedException {
        driver.findElement(searchInput).sendKeys(keyword);
        Thread.sleep(3000);
    }

    public void clearSearchField() throws InterruptedException {
        driver.findElement(searchInput).sendKeys(Keys.CONTROL + "a");
        driver.findElement(searchInput).sendKeys(Keys.SPACE);
        Thread.sleep(3000);
    }

    public void submitSearchButton() throws InterruptedException {
        driver.findElement(enterSearchButton).click();
        Thread.sleep(3000);
    }

    public List<String> getNamesOfCompany() {
        List<String> names;
        names = driver.findElements(companiesResult)
                .stream()
                .map(el -> el.getText().toLowerCase())
                .collect(Collectors.toList());
        return names;

    }

    public void selectRandomCompany(String nameCompany) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//div[contains(text(),'" + nameCompany + "')]"))).perform();
        Thread.sleep(3000);
    }


    public String getExpectedCompanyDetails(String nameCompany) {
        return driver.findElement(By.xpath("//div[contains(text(),'" + nameCompany + "')]/ancestor-or-self::div[4]"))
                .getText().toLowerCase();
    }


}
