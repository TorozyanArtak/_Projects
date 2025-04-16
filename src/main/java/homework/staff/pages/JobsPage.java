package homework.staff.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;


public class JobsPage extends BasePage {

    private final String filterNameXpath = "//following-sibling::div//span[text()=\"%s\"]";
    private final String filterTypeXpath = "//div[text()='%s']";
    private final String viewMoreXpath = " //div[text()='%s']//following-sibling::div//div[text()='View more']";
    private final By jobsLoc = By.xpath("//div[h1]/following-sibling::div//img[@alt='left-icon']");

    @FindBy(xpath = "//div[text()='Clear filters']")
    private WebElement clearFilters;
    @FindBy(xpath = "//li[@class='next']/preceding-sibling::li[1]/a")
    private WebElement lastPage;
    Actions actions = new Actions(driver);

    public void clickViewMore(String filterType) {
        List<WebElement> viewMores = driver.findElements(By.xpath(String.format(viewMoreXpath, filterType)));
        System.out.println(viewMores.size());
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
            System.out.println("catch");
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