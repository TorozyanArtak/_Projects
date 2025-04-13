package homework.staff.tests;

import homework.staff.pages.DriverGenerator;
import homework.staff.pages.JobsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestFiltersValidation extends TestClass {
    @ParameterizedTest
    @CsvSource({
            "Job category, Legal, Human Resources",
            "Job special tag, Bachelor's degree, Doctoral degree",
            "Specialist level, Junior, Mid level",
            "Job salary, Not Mentioned, Mentioned",
            "Job types, Fixed term contract, Full time",
            "Job terms, Freelance, Permanent ",
            "By cities, Goris, Yerevan"
    })
    public void filtersValidation( String filterType,String filterName1, String filterName2)  {
        DriverGenerator.getDriver().get("https://staff.am/jobs/");
        JobsPage jobsPage = new JobsPage();

        jobsPage.selectFilter(filterType, filterName1);
        int exceptedCountAfterFirstFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName1);
        int actualCountAfterFirstFilter = jobsPage.getAllCount();
        System.out.println("All jobs expected count -> " + exceptedCountAfterFirstFilter);
        System.out.println("All jobs count after first filtering -> " + actualCountAfterFirstFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterFirstFilter, actualCountAfterFirstFilter
                , String.format("Jobs count is not correct when selecting  '%s' from '%s' filter",filterName1,filterType));


        jobsPage.selectFilter(filterType, filterName2);
        int exceptedCountAfterSecondFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName1)
                + jobsPage.getCountOfSelectedFilter(filterType, filterName2);
        int actualCountAfterSecondFilter = jobsPage.getAllCount();
        System.out.println("All jobs expected count -> " + exceptedCountAfterSecondFilter);
        System.out.println("All jobs count after second filtering -> " + actualCountAfterSecondFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterSecondFilter, actualCountAfterSecondFilter
                , String.format("Jobs count is not correct when selecting  '%s' & '%s' from '%s' filter",filterName1,filterName2,filterType));


        jobsPage.removeFilter(filterType, filterName1);
        int exceptedCountAfterRemoveFirstFilter = jobsPage.getCountOfSelectedFilter(filterType, filterName2);
        int actualCountAfterRemoveFirstFilter = jobsPage.getAllCount();
        System.out.println("All jobs expected count -> " + exceptedCountAfterRemoveFirstFilter);
        System.out.println("All jobs count after first filter removing -> " + actualCountAfterRemoveFirstFilter);
        System.out.println("_________________________________________________________");
        Assertions.assertEquals(exceptedCountAfterRemoveFirstFilter, actualCountAfterRemoveFirstFilter
                , String.format("Jobs count is not correct when selecting  '%s' from '%s' filter",filterName2,filterType));

        jobsPage.setClearFilter();
        System.out.println("All jobs count after removing filters -> " + jobsPage.getAllCount());
        System.out.println("_________________________________________________________");
    }
}
