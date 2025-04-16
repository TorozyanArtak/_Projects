package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final String radioButtonXpath = "(//div//div[contains(text() , '%s')])[2]";
    private final String industryCategoryXpath = "//div[text()='%s']";
    @FindBy(xpath = "//input[@class='ant-select-selection-search-input']")
    WebElement allIndustriesDropDown;
    @FindBy(xpath = "//img[@alt='search-icon']")
    WebElement searchButton;
    @FindBy(xpath = "//div[text()='Information technologies']")
    WebElement informationTechnologiesOption;

    public HomePage selectRadioButtonOnTab(String radioButton) {
        wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath(String.format(radioButtonXpath, radioButton))))
                .click();
        return this;
    }

    public HomePage selectIndustryCategory(String industry) {
        wait.until(ExpectedConditions.visibilityOf(allIndustriesDropDown)).sendKeys(industry);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(industryCategoryXpath, industry))))
                .click();
        return this;
    }

    public SearchResultPage enterSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
        return new SearchResultPage();
    }

    public String getIndustryDetail() {
        return
                wait.until(ExpectedConditions.elementToBeClickable(informationTechnologiesOption)).getText();
    }

}

