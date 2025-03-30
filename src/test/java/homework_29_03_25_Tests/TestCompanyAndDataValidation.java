package homework_29_03_25_Tests;

import homework_29_03_25_Pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCompanyAndDataValidation {
    private WebDriver driver ;

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://staff.am/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    @AfterEach
    public void quit() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void testCompanyAndDataValidation() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        IndustriesResultPage industriesResultPage = new IndustriesResultPage(driver);
        CompanyPage companyPage = new CompanyPage(driver);
        homePage.selectRadioButtonOnTab();
        homePage.selectIndustryCategory("Information technologies");
        homePage.clickSearchResult();
        homePage.enterSearchButton();

        industriesResultPage.enterKeywordInSearchField(Helper.generateRandomText(5));
        industriesResultPage.submitSearchButton();
        Assertions.assertTrue(industriesResultPage.getNamesOfCompany().isEmpty(), "Result should be empty");
        industriesResultPage.clearSearchField();
        industriesResultPage.enterKeywordInSearchField("ser");
        industriesResultPage.submitSearchButton();
        boolean isContainKeyword = Helper.areAllNamesContainingSearch(industriesResultPage.getNamesOfCompany(), "ser");
        Assertions.assertTrue(isContainKeyword, "All company names should contain keyword");
        String expectedDetailsOfCompany = industriesResultPage.getExpectedCompanyDetails("gHost Services LLC");
        industriesResultPage.selectRandomCompany("gHost Services LLC");

        String actualDetailsOfCompany = companyPage.getActualResultsCompanyDetails();
        Assertions.assertEquals(expectedDetailsOfCompany,actualDetailsOfCompany);

        String expectedIndustry = homePage.getExpectedResultOfIndustry();
        String actualIndustry = companyPage.getActualResultOfIndustry();
        Assertions.assertEquals(expectedIndustry,actualIndustry);
    }
}
