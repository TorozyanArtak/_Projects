package homework_29_03_25_Tests;


import homework_29_03_25_Pages.Company;
import homework_29_03_25_Pages.IndustriesResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestListValidation extends TestClass {

    @Test
    public void testHomework() throws InterruptedException {
        IndustriesResultPage resultPage = new IndustriesResultPage();
        //Step 1-2
        resultPage.header.selectCategoryOfHeader("Companies")
                .openViewMoreSection()
                .selectFilterIndustry("Sport");
        Thread.sleep(5000);

        //Step3
        List<Company> companiesListAfterHeaderFiltering = resultPage.getCompaniesList();

        //Step4
        resultPage.enterHiring();
        Thread.sleep(3000);
        List<Company> companiesListAfterHiringFilter = resultPage.getCompaniesList();

        //Step5-6 ->2-4
        resultPage.footer.selectCategoryOfFooter()
                .selectFilterIndustry("Sport");
        List<Company> companiesListAfterFooterFiltering = resultPage.getCompaniesList();
        resultPage.enterHiring();
        Thread.sleep(3000);
        List<Company> companiesListAfterHiringFilter_2 = resultPage.getCompaniesList();

        Assertions.assertEquals(companiesListAfterHeaderFiltering, companiesListAfterFooterFiltering,
                "All objects of first list should be equals of second list");

        Assertions.assertEquals(companiesListAfterHiringFilter, companiesListAfterHiringFilter_2,
                "All objects of first list should be equals of second list");

    }
}
