package steps;

import homework.staff.pages.DriverGenerator;
import homework.staff.pages.SearchResultPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.loggers.Log;
import utils.screenshot.AllureAttachments;

import java.util.List;

public class FiltersTestSteps {
    private final SearchResultPage jobsPage = new SearchResultPage();
    private final WebDriver driver = DriverGenerator.getDriver();

    @Step("Open Jobs Page")
    public void openJobsPage() {
        driver.get("https://staff.am/jobs/");
        Log.info("Jobs Page opened");
    }

    @Step("Select two random filters from '{filterType}'")
    public List<String> selectTwoRandomFilters(String filterType) {
        return jobsPage.getTwoRandomFilters(filterType);
    }

    @Step("Select filter '{filterName}' from '{filterType}'")
    public void selectFilter(String filterType, String filterName) {
        jobsPage.clickFilter(filterType, filterName);
        Log.debug(filterName + " selected from " + filterType);
    }

    @Step("Get expected job count after selecting '{filterName}' from '{filterType}'")
    public int getExpectedCountAfterFilter(String filterType, String filterName) {
        return jobsPage.getCountOfSelectedFilter(filterType, filterName);
    }

    @Step("Get actual job count displayed on page")
    public int getActualJobCount() {
        AllureAttachments.attachScreenshot(driver, "Counting the number of actual jobs on the displayed page");
        return jobsPage.getAllCount();
    }
}
