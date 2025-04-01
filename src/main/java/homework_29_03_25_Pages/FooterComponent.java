package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FooterComponent {
    private WebDriver driver;

    public IndustriesResultPage selectCategoryOfFooter() {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("(//div[text()='View all companies'])[2]"))).perform();

        return new IndustriesResultPage();
    }

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
    }
}
