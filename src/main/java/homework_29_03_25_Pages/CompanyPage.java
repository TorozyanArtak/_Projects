package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompanyPage {
    private final WebDriver driver;
    private final By detailsLoc = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]");
    private final By namesLoc = By.xpath("//h1[@role='heading']");
    private final By industryLoc = By.xpath("//div[text() ='Industry']/following-sibling::div");

    public CompanyPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getActualResultsCompanyDetails() {
        String name = driver.findElement(namesLoc).getText().toLowerCase();
        String details = driver.findElement(detailsLoc).getText()
                .replace("(", "").replace(")", "");
        return name + "\n" + details;
    }

    public String getActualResultOfIndustry() {
        return driver.findElement(industryLoc).getText().toLowerCase();
    }


}

