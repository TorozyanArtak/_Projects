package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent {
    private WebDriver driver;

    public IndustriesResultPage selectCategoryOfHeader(String category) {
        driver.findElement(By.xpath(String.format("//div[@class='justify-content-end false  " +
                        "web-dropdown light navbar-collapse collapse']//div[text()='%s']", category)))
                .click();
        return new IndustriesResultPage();
    }

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }
}
