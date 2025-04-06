package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import java.util.stream.Collectors;

public class IndustriesResultPage extends BasePage {
    private final By searchInput = By.xpath("//input[@placeholder='Enter keywords...']");
    private final By enterSearchButton = By.xpath("//div[text()='Search']");
    private final By companiesResult = By.xpath("//div[img[@alt='company-logo']]/div/div/div/div[string-length(text()) > 0]");
    private final By viewMoreLoc = By.xpath("//div[text()='Filter By Industry']//following-sibling::div[6]");
    private final By hiringLoc = By.xpath("//div[text()='Hiring']");
    public static int randomCompany;
    public static List<WebElement> companies;


    public IndustriesResultPage enterKeywordInSearchField(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput))
                .sendKeys(keyword);
        return this;
    }

    public IndustriesResultPage clearSearchField() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput))
                .clear();
        return this;
    }

    public void submitSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterSearchButton)).click();
    }


    public String getRandomCompanyDetails() {
        companies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(companiesResult));
        Random rand = new Random();
        randomCompany = rand.nextInt(companies.size());
        return getCompanyDetails(companies.get(randomCompany).getText());
    }


    public CompanyPage clickRandomPage() {
        Actions actions = new Actions(driver);
        actions.click(companies.get(randomCompany)).perform();
        return new CompanyPage();
    }

    public String getCompanyDetails(String nameCompany) {
        return driver.findElement(By.xpath(String.format("//div[contains(text(),'%s')]/ancestor-or-self::div[4]", nameCompany)))
                .getText();
    }

    public IndustriesResultPage openViewMoreSection() {
        wait.until(ExpectedConditions.elementToBeClickable(viewMoreLoc));
        driver.findElement(viewMoreLoc).click();
        return this;
    }

    public void selectFilterIndustry(String industryName) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Clear filters']")));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//span[text()='%s']", industryName))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    public List<String> getNamesOfCompany() {
        List<WebElement> visibleCompanies = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(companiesResult)
        );
        return visibleCompanies.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isNoCompanyFoundMessageVisible() {
        WebElement noResults = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div//img[@alt='search-not-found']/following-sibling::div")));
        return noResults.isDisplayed();
    }

    public List<Company> getCompaniesList() {

        WebElement filterCount = driver.findElement(By.xpath("//span[text()='Sport']/span"));
        WebElement resultCount = driver.findElement(By.xpath("//img[@alt='building']//following-sibling::div"));
        wait.until(_ ->
                Integer.parseInt(filterCount.getText().replaceAll("\\D", ""))
                        == Integer.parseInt(resultCount.getText().trim()));
        List<String> companiesNames = getNamesOfCompany();
        return companiesNames.stream()
                .map(name -> new Company(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void enterHiring() {

        WebElement hiring = wait.until(ExpectedConditions.elementToBeClickable(hiringLoc));
        hiring.click();
    }

}
