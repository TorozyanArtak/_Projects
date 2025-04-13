package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IndustriesResultPage extends BasePage {

    private final String companyDetails = "//div[contains(text(),'%s')]/ancestor-or-self::div[4]";
    private final String industryNameXpath = "//span[text()='%s']";
    private final By companiesResultLoc = By.xpath("//div[img[@alt='company-logo']]/div/div/div/div[string-length(text()) > 0]");

    @FindBy(xpath = "//input[@placeholder='Enter keywords...']")
    private WebElement searchInput;
    @FindBy(xpath = "//div[text()='Search']")
    private WebElement enterSearchButton;
    @FindBy(xpath = "//div//img[@alt='search-not-found']/following-sibling::div")
    private WebElement noFoundMessage;
    @FindBy(xpath = "//div[text()='Filter By Industry']//following-sibling::div[6]")
    private WebElement viewMore;
    @FindBy(xpath = "//div[text()='Hiring']")
    private WebElement hiring;
    @FindBy(xpath = "//div[text()='Clear filters']")
    private WebElement clearFilter;
    public static int randomCompany;
    public static List<WebElement> companies;

    public IndustriesResultPage enterKeywordInSearchField(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchInput))
                .sendKeys(keyword);
        return this;
    }

    public IndustriesResultPage clearSearchField() {
        wait.until(ExpectedConditions.visibilityOf(searchInput))
                .clear();
        return this;
    }

    public void submitSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterSearchButton)).click();
    }


    public String getRandomCompanyDetails() {
        companies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(companiesResultLoc));
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
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated((By.xpath(String.format(companyDetails, nameCompany)))))
                .getText();
    }

    public IndustriesResultPage openViewMoreSection() {
        wait.until(ExpectedConditions.elementToBeClickable(viewMore)).click();

        return this;
    }

    public void selectFilterIndustry(String industryName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.invisibilityOf(clearFilter));
        WebElement el = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(industryNameXpath, industryName))));
        js.executeScript("arguments[0].scrollIntoView(true);", el);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        js.executeScript("arguments[0].click();", el);

    }

    public List<String> getNamesOfCompany() {
        List<WebElement> visibleCompanies = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(companiesResultLoc)
        );
        return visibleCompanies.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isNoCompanyFoundMessageVisible() {
        WebElement noResults = wait.until(ExpectedConditions.visibilityOf(noFoundMessage));
        return noResults.isDisplayed();
    }

    public List<String> getCompaniesList() {
        WebElement filterCount = driver.findElement(By.xpath("//span[text()='Sport']/span"));
        WebElement resultCount = driver.findElement(By.xpath("//img[@alt='building']//following-sibling::div"));
        wait.until(_ ->
                Integer.parseInt(filterCount.getText().replaceAll("\\D", ""))
                        == Integer.parseInt(resultCount.getText().trim()));
        List<WebElement> visibleCompanies = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(companiesResultLoc)
        );
        return visibleCompanies.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void enterHiring() {

        wait.until(ExpectedConditions.elementToBeClickable(hiring)).click();
        wait.until(ExpectedConditions.urlContains("job"));

    }

}
