package homework.staff.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import steps.FiltersTestSteps;

import java.util.List;

public class AllureFiltersValidationTest extends FiltersTest {
    private final FiltersTestSteps filtersSteps = new FiltersTestSteps();

    @ParameterizedTest
    @CsvSource({
            "Job special tag",
            "Job category",
//            "Specialist level",
//            "Job salary",
//            "Job types",
//            "Job terms",
//            "By cities"
    })
    public void filtersValidation(String filterType) {

        filtersSteps.openJobsPage();
        List<String> filters = filtersSteps.selectTwoRandomFilters(filterType);
        String filterName1 = filters.get(0);
        String filterName2 = filters.get(1);
        filtersSteps.selectFilter(filterType, filterName1);
        int expectedCountAfterFirstFilter = filtersSteps.getExpectedCountAfterFilter(filterType, filterName1);
        int actualCountAfterFirstFilter = filtersSteps.getActualJobCount();
        Assertions.assertEquals(expectedCountAfterFirstFilter, actualCountAfterFirstFilter,
               String.format("Jobs count is not correct when selecting '%s' from '%s' filter", filterName1, filterType));

        filtersSteps.selectFilter(filterType, filterName2);
        int expectedCountAfterSecondFilter = filtersSteps.getExpectedCountAfterFilter(filterType, filterName1)
                + filtersSteps.getExpectedCountAfterFilter(filterType, filterName2);
        int actualCountAfterSecondFilter = filtersSteps.getActualJobCount();
        Assertions.assertEquals(expectedCountAfterSecondFilter, actualCountAfterSecondFilter,
                String.format("Jobs count is not correct when selecting '%s' & '%s' from '%s' filter", filterName1, filterName2, filterType));

        filtersSteps.selectFilter(filterType, filterName1);

        int expectedCountAfterRemoveFirstFilter = filtersSteps.getExpectedCountAfterFilter(filterType, filterName2);
        int actualCountAfterRemoveFirstFilter = filtersSteps.getActualJobCount();
        Assertions.assertEquals(expectedCountAfterRemoveFirstFilter, actualCountAfterRemoveFirstFilter,
                String.format("Jobs count is not correct after removing '%s' from '%s' filter", filterName1, filterType));

    }
}
