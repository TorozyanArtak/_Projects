import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://6pm.com");

        driver.manage().window().maximize();
        Thread.sleep(1000);
        hoverAndClick(driver, "BAGS","Luggage");
        Thread.sleep(3000);
        List<WebElement> luggage = driver.findElements(By.xpath("//article/a[contains(@href , 'luggage')]"));

        for (WebElement el : luggage) {
            Thread.sleep(5000);
            System.out.println(el.getText());
        }

        if (!luggage.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(luggage.size());
            WebElement randomTab = luggage.get(randomIndex);
            randomTab.click();
        } else {
            System.out.println("No luggage found!");
        }
        Thread.sleep(7000);
        WebElement addToShoppingBag = driver.findElement(By.xpath("//button[@id='add-to-cart-button']"));
        addToShoppingBag.click();

        Thread.sleep(5000);
        WebElement removeElement = driver.findElement(By.xpath("//button[@aria-label = 'Remove Item']"));
        removeElement.click();

        Thread.sleep(5000);
        WebElement signInButton = driver.findElement(By.xpath("//a[contains(text(), 'Sign In')]"));
        String url = signInButton.getAttribute("href");
        System.out.println(url);

        driver.quit();
    }

    public static void hoverAndClick(WebDriver driver, String tabName, String clickItem) throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement tabElement = driver.findElement(By.xpath("//a[contains(@href, '" + tabName.toLowerCase() + "')]"));
        actions.moveToElement(tabElement).perform();
        Thread.sleep(5000);
        WebElement clickElement = driver.findElement(By.xpath("//a[contains(@href, '" + clickItem.toLowerCase() + "')]"));
        clickElement.click();

    }
}
