package homework_29_03_25_Pages;

import org.openqa.selenium.By;

public class CompanyPage extends BasePage {
    private final By detailsLoc = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]");
    private final By namesLoc = By.xpath("//h1[@role='heading']");
    private final By industryLoc = By.xpath("//div[text() ='Industry']/following-sibling::div");

    public String getCompanyDetails() {
        String name = driver.findElement(namesLoc).getText();
        String details = driver.findElement(detailsLoc).getText()
                .replace("(", "").replace(")", "");
        return name + "\n" + details;
    }

    public String getIndustryDetail() {

        return driver.findElement(industryLoc).getText();
    }

}

