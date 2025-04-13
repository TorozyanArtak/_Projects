package homework.staff.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyPage extends BasePage {

    @FindBy(xpath = "//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]")
    private WebElement detailsEl;
    @FindBy(xpath = "//h1[@role='heading']")
    private WebElement nameEl;
    @FindBy(xpath = "//div[text() ='Industry']/following-sibling::div")
    private WebElement industry;

    public String getCompanyDetails() {
        String name = nameEl.getText();
        String details = detailsEl.getText()
                .replace("(", "").replace(")", "");
        return name + "\n" + details;
    }

    public String getIndustryDetail() {

        return industry.getText();
    }

}
