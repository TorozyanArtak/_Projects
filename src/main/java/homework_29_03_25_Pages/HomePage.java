package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final By allIndustriesDropDown = By.xpath("//input[@class='ant-select-selection-search-input']");
    private final By informationTechnologiesOption = By.xpath("//div[text()='Information technologies']");
    private final By searchButton = By.xpath("//img[@alt='search-icon']");


    public HomePage selectRadioButtonOnTab(String radioButton) {
        wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath(String.format("(//div//div[contains(text() , '%s')])[2]", radioButton))))
                .click();
        return this;
    }

    public HomePage selectIndustryCategory(String industry) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(allIndustriesDropDown)).sendKeys(industry);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//div[text()='%s']", industry))))
                .click();
        return this;
    }

    public IndustriesResultPage enterSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
        return new IndustriesResultPage();
    }

    public String getIndustryDetail() {
        return driver.findElement(informationTechnologiesOption).getText();
    }

}
