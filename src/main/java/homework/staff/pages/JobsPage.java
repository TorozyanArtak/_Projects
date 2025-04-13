package homework.staff.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;


public class JobsPage extends BasePage {

    private final String filterNameXpath = "//following-sibling::div//span[text()=\"%s\"]";
    private final String filterTypeXpath = "//div[text()='%s']";
    private final String viewMoreXpath = " //div[text()='%s']//following-sibling::div[6]";
    private final By jobsLoc = By.xpath("//div[h1]/following-sibling::div//img[@alt='left-icon']");

    @FindBy(xpath = "//div[text()='Clear filters']")
    private WebElement clearFilter;
    @FindBy(xpath = "//li[@class='next']/preceding-sibling::li[1]/a")
    private WebElement lastPage;

    public void selectFilter(String filterType, String filterName) {
        String nameXpath = String.format(filterTypeXpath, filterType) + "//following-sibling::div" + String.format(filterNameXpath, filterName);
        String previousUrl = driver.getCurrentUrl();
        try {
            click(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(viewMoreXpath, filterType)))));
            click(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nameXpath))));
        } catch (TimeoutException e) {
            click(wait.until(ExpectedConditions.elementToBeClickable
                    (By.xpath(nameXpath))));
        }
        wait.until(_ -> !Objects.equals(driver.getCurrentUrl(), previousUrl));
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
            click(lastPage);
            wait.until(ExpectedConditions.invisibilityOf(jobsOfFirstPage));
            System.out.println("before wait");
            System.out.println("after wait");
            int jobsCountOFLastPage = getCountOnePage();
            return (lastPageNumber - 1) * 50 + jobsCountOFLastPage;
        } catch (TimeoutException e) {
            System.out.println("catch");
            return getCountOnePage();
        }
    }

    public void removeFilter(String filterType, String filterName) {
        String nameXpath = String.format(filterTypeXpath, filterType) + "//following-sibling::div" + String.format(filterNameXpath, filterName);
        click(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nameXpath))));
    }

    public int getCountOfSelectedFilter(String filterType, String filterName) {
        return Integer
                .parseInt(wait.until(ExpectedConditions
                                .visibilityOfElementLocated(By.xpath(String.format(filterTypeXpath, filterType)
                                        + String.format(filterNameXpath + "/span", filterName))))
                        .getText().replaceAll("\\D", ""));
    }

    public void setClearFilter() {
        click(wait.until(ExpectedConditions.elementToBeClickable(clearFilter)));
    }

}

