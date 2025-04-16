package homework.staff.tests;

import homework.staff.pages.DriverGenerator;
import homework.staff.pages.JobsPage;
import homework.staff.pages.SearchResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class TestFiltersValidation extends filterTest {
    @ParameterizedTest
    @CsvSource({
            "Job category",
            "Job special tag",
            "Specialist level",
            "Job salary",
            "Job types",
            "Job terms",
            "By cities"
    })
    public void filtersValidation(String filterType) {
        DriverGenerator.getDriver().get("https://staff.am/jobs/");
        SearchResultPage jobsPage = new SearchResultPage();
        List<String> filters = jobsPage.getTwoRandomFilters(filterType);
        String filterName1 = filters.get(0);
        String filterName2 = filters.get(1);

        jobsPage.clickFilter(filterType, filterName1);
        int exceptedCountAfterFirstFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName1);
        int actualCountAfterFirstFilter = jobsPage.getAllCount();
        System.out.println("Expected count all jobs -> " + exceptedCountAfterFirstFilter);
        System.out.println("Actual count all jobs after first filtering -> " + actualCountAfterFirstFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterFirstFilter, actualCountAfterFirstFilter
                , String.format("Jobs count is not correct when selecting  '%s' from '%s' filter", filterName1, filterType));


        jobsPage.clickFilter(filterType, filterName2);
        int exceptedCountAfterSecondFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName1)
                + jobsPage.getCountOfSelectedFilter(filterType, filterName2);
        int actualCountAfterSecondFilter = jobsPage.getAllCount();
        System.out.println("Expected count all jobs -> " + exceptedCountAfterSecondFilter);
        System.out.println("Actual count all jobs after second filtering -> " + actualCountAfterSecondFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterSecondFilter, actualCountAfterSecondFilter
                , String.format("Jobs count is not correct when selecting  '%s' & '%s' from '%s' filter", filterName1, filterName2, filterType));


        jobsPage.clickFilter(filterType, filterName1);
        int exceptedCountAfterRemoveFirstFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName2);
        int actualCountAfterRemoveFirstFilter = jobsPage.getAllCount();
        System.out.println("Expected count all jobs  -> " + exceptedCountAfterRemoveFirstFilter);
        System.out.println("Actual count all jobs after first filter removing -> " + actualCountAfterRemoveFirstFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterRemoveFirstFilter, actualCountAfterRemoveFirstFilter
                , String.format("Jobs count is not correct when selecting  '%s' from '%s' filter", filterName2, filterType));

    }
}
