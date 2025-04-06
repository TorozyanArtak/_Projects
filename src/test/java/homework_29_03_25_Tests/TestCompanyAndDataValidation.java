package homework_29_03_25_Tests;

import homework_29_03_25_Pages.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCompanyAndDataValidation extends TestClass{
    @Test
    public void testCompanyAndDataValidation()  {
        //Steps 1-8
        HomePage homePage = new HomePage();
        IndustriesResultPage resultPage = homePage.selectRadioButtonOnTab("Companies")
                .selectIndustryCategory("Information technologies")
                .enterSearchButton();

        resultPage.enterKeywordInSearchField(RandomStringUtils.randomAlphabetic(8))
                .submitSearchButton();
        boolean noResults = resultPage.isNoCompanyFoundMessageVisible();
        Assertions.assertTrue(noResults, "Expected no companies to be found!");
        resultPage.clearSearchField()
                .enterKeywordInSearchField("ser")
                .submitSearchButton();

        Assertions.assertTrue(resultPage
                        .getNamesOfCompany()
                        .stream().allMatch(name -> name.toLowerCase().contains(("sEr".toLowerCase()))),
                "All company names should contain search word");
        //Step 9
        String expectedDetails = resultPage.getRandomCompanyDetails().toLowerCase();
        CompanyPage companyPage = resultPage.clickRandomPage();
        String actualDetails = companyPage.getCompanyDetails().toLowerCase();
        Assertions.assertEquals(expectedDetails, actualDetails,
        "Company details in IndustriesResultPage should be equals company details in CompanyPage");
        //Step 10
        String expectedIndustry = homePage.getIndustryDetail().toLowerCase();
        String actualIndustry = companyPage.getIndustryDetail().toLowerCase();
        Assertions.assertEquals(expectedIndustry, actualIndustry,
                "Industry name of company should be the same as selected industries category");
    }
}
