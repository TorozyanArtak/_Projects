package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By companiesRadioButton = By.xpath("(//div//div[contains(text() , 'Companies')])[2]");
    private final By allIndustriesDropDown = By.xpath("//input[@class='ant-select-selection-search-input']");
    private final By informationTechnologiesOption = By.xpath("//div[text()='Information technologies']");
    private final By searchButton = By.xpath("//img[@alt='search-icon']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRadioButtonOnTab() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(companiesRadioButton).click();
        Thread.sleep(5000);
    }

    public void selectIndustryCategory(String industry) throws InterruptedException {
        driver.findElement(allIndustriesDropDown).sendKeys(industry);
        Thread.sleep(5000);
    }

    public void clickSearchResult() throws InterruptedException {
        driver.findElement(informationTechnologiesOption).click();
        Thread.sleep(5000);
    }

    public void enterSearchButton() throws InterruptedException {
        driver.findElement(searchButton).click();
        Thread.sleep(5000);
    }

    public String getExpectedResultOfIndustry(){
        return driver.findElement(informationTechnologiesOption).getText().toLowerCase();
    }

}
