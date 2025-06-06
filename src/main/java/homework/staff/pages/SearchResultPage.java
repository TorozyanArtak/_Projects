package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {

    private final String companyDetails = "//div[contains(text(),'%s')]/ancestor-or-self::div[4]";
    private final String industryNameXpath = "//span[text()='%s']";
    private final String filterNameXpath = "//following-sibling::div//span[text()=\"%s\"]";
    private final String filterTypeXpath = "//div[text()='%s']";
    private final String viewMoreXpath = " //div[text()='%s']//following-sibling::div//div[text()='View more']";
    private final By jobsLoc = By.xpath("//div[h1]/following-sibling::div//img[@alt='left-icon']");
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
    @FindBy(xpath = "//div[text()='Clear filters']")
    private WebElement clearFilters;
    @FindBy(xpath = "//li[@class='next']/preceding-sibling::li[1]/a")
    private WebElement lastPage;;
    public static int randomCompany;
    public static List<WebElement> companies;

    public SearchResultPage enterKeywordInSearchField(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchInput))
                .sendKeys(keyword);
        return this;
    }

    public SearchResultPage clearSearchField() {
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
        actions.click(companies.get(randomCompany)).perform();
        return new CompanyPage();
    }

    public String getCompanyDetails(String nameCompany) {
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated((By.xpath(String.format(companyDetails, nameCompany)))))
                .getText();
    }

    public SearchResultPage openViewMoreSection() {
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
        wait.until(a_ ->
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

    public void clickViewMore(String filterType) {
        List<WebElement> viewMores = driver.findElements(By.xpath(String.format(viewMoreXpath, filterType)));
        if (!viewMores.isEmpty()) {
            WebElement viewMore = viewMores.getFirst();
            actions.moveToElement(viewMore).perform();
            actions.click(viewMore).perform();
        }
    }

    public void clickFilter(String filterType, String filterName) {
        String previousUrl = driver.getCurrentUrl();
        clickViewMore(filterType);
        String nameXpath = String.format(filterTypeXpath, filterType)
                + "//following-sibling::div"
                + String.format(filterNameXpath, filterName);
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nameXpath)));
        actions.moveToElement(filterElement).perform();
        actions.click(filterElement).perform();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));
    }

    public int getCountOnePage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(jobsLoc)).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public int getAllCount() {
        try {
            int lastPageNumber = Integer.parseInt(wait.until(ExpectedConditions.elementToBeClickable(lastPage)).getText());
            WebElement jobsOfFirstPage = driver.findElement(jobsLoc);
            actions.moveToElement(lastPage).perform();
            actions.click(lastPage).perform();
            wait.until(ExpectedConditions.invisibilityOf(jobsOfFirstPage));
            int jobsCountOFLastPage = getCountOnePage();
            return (lastPageNumber - 1) * 50 + jobsCountOFLastPage;
        } catch (TimeoutException e) {
            return getCountOnePage();
        }
    }

    public int getCountOfSelectedFilter(String filterType, String filterName) {
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(String.format(filterTypeXpath, filterType)
                        + String.format(filterNameXpath
                        + "/span", filterName)))).getText().replaceAll("\\D", ""));
    }

    public void setClearFilter() {
        actions.moveToElement(clearFilters).perform();
        actions.click(clearFilters).perform();
    }

    public List<String> getTwoRandomFilters(String filterType) {
        clickViewMore(filterType);
        String jobsXpath = "(//div[text()='%s']//following-sibling::div//div/span)";
        List<WebElement> jobs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath(String.format(jobsXpath, filterType))));
        if (jobs.size() < 2) {
            throw new IllegalStateException("Not enough filters to select in " + filterType);
        }
        Random rand = new Random();
        int randomNumber1 = rand.nextInt(jobs.size());
        int randomNumber2 = rand.nextInt(jobs.size());
        while (randomNumber2 == randomNumber1) {
            randomNumber2 = rand.nextInt(jobs.size());
        }
        WebElement filter1 = jobs.get(randomNumber1);
        WebElement filter2 = jobs.get(randomNumber2);
        return List.of(
                filter1.getText().split(" \\(")[0].trim(),
                filter2.getText().split(" \\(")[0].trim()
        );
    }


}
