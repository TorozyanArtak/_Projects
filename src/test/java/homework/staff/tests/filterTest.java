package homework.staff.tests;

import homework.staff.pages.JobsPage;
import homework.staff.pages.SearchResultPage;
import org.junit.jupiter.api.AfterEach;

public class filterTest extends BaseTest{
    SearchResultPage jobsPage = new SearchResultPage();
    @AfterEach
    public void clearFiltersAfterTest() {
        jobsPage.setClearFilter();
    }
}
