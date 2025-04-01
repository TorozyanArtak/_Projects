package homework_29_03_25_Pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By allIndustriesDropDown = By.xpath("//input[@class='ant-select-selection-search-input']");
    private final By informationTechnologiesOption = By.xpath("//div[text()='Information technologies']");
    private final By searchButton = By.xpath("//img[@alt='search-icon']");

    public HomePage selectRadioButtonOnTab(String radioButton) throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath(String.format("(//div//div[contains(text() , '%s')])[2]", radioButton))).click();
        Thread.sleep(5000);
        return this;
    }

    public HomePage selectIndustryCategory(String industry) throws InterruptedException {
        driver.findElement(allIndustriesDropDown).sendKeys(industry);
        Thread.sleep(5000);
        driver.findElement(By.xpath(String.format("//div[text()='%s']", industry))).click();
        return this;
    }

    public IndustriesResultPage enterSearchButton() throws InterruptedException {
        driver.findElement(searchButton).click();
        Thread.sleep(5000);
        return new IndustriesResultPage();
    }

    public String getIndustryDetail() {
        return driver.findElement(informationTechnologiesOption).getText().toLowerCase();
    }

}
