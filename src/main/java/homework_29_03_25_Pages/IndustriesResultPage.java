package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IndustriesResultPage extends BasePage {
    private final By searchInput = By.xpath("//input[@placeholder='Enter keywords...']");
    private final By enterSearchButton = By.xpath("//div[text()='Search']");
    private final By companiesResult = By.xpath("//div[img[@alt='company-logo']]/div/div/div/div[string-length(text()) > 0]");
    public static int randomCompany;
    public static List<WebElement> companies;

    public IndustriesResultPage enterKeywordInSearchField(String keyword) throws InterruptedException {
        driver.findElement(searchInput).sendKeys(keyword);
        Thread.sleep(3000);
        return this;
    }

    public IndustriesResultPage clearSearchField() throws InterruptedException {
        driver.findElement(searchInput).clear();
        Thread.sleep(3000);
        return this;
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

    public String getRandomCompanyDetails() throws InterruptedException {

        companies = driver.findElements(By.xpath("//div[img[@alt='company-logo']]/div/div/div/div[string-length(text()) > 0]"));
        Thread.sleep(5000);
        Random rand = new Random();
        randomCompany = rand.nextInt(companies.size());
        return getCompanyDetails(companies.get(randomCompany).getText());
    }


    public CompanyPage clickRandomPage() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.click(companies.get(randomCompany)).perform();
        Thread.sleep(3000);
        return new CompanyPage();
    }


    public String getCompanyDetails(String nameCompany) {
        return driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]/ancestor-or-self::div[4]", nameCompany)))
                .getText().toLowerCase();

    }


}
