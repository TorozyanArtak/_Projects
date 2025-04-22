package homework.staff.tests;

import homework.staff.pages.SearchResultPage;
import org.junit.jupiter.api.AfterEach;

public class FiltersTest extends BaseTest{
    SearchResultPage jobsPage = new SearchResultPage();
    @AfterEach
    public void clearFiltersAfterTest() {
        jobsPage.setClearFilter();
    }
}
