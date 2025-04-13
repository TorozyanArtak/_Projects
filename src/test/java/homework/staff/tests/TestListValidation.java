package homework.staff.tests;


import homework.staff.pages.DriverGenerator;
import homework.staff.pages.IndustriesResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestListValidation extends TestClass {


    @Test
    public void testHomework() {
        DriverGenerator.getDriver().get("https://staff.am/");
        IndustriesResultPage resultPage = new IndustriesResultPage();
        //Step 1-2
        resultPage.header.selectCategoryOfHeader("Companies");
        resultPage.openViewMoreSection()
                .selectFilterIndustry("Sport");

        //Step3
        List<String> companiesListAfterHeaderFiltering = resultPage.getCompaniesList();
        System.out.println(companiesListAfterHeaderFiltering);

        //Step4
        resultPage.enterHiring();
        List<String> companiesListAfterHiringFilter = resultPage.getCompaniesList();
        System.out.println(companiesListAfterHiringFilter);

        //Step5-6 ->2-4
        resultPage.footer.selectCategoryOfFooter();

        resultPage.selectFilterIndustry("Sport");
        List<String> companiesListAfterFooterFiltering = resultPage.getCompaniesList();
        System.out.println(companiesListAfterFooterFiltering);
        resultPage.enterHiring();
        List<String> companiesListAfterHiringFilter_2 = resultPage.getNamesOfCompany();
        System.out.println(companiesListAfterHiringFilter_2);
        Assertions.assertEquals(companiesListAfterHeaderFiltering, companiesListAfterFooterFiltering,
                "All objects of first list should be equals of second list");

        Assertions.assertEquals(companiesListAfterHiringFilter, companiesListAfterHiringFilter_2,
                "All objects of first list should be equals of second list");

    }
}
