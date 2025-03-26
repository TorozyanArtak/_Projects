import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAutomation {

    private WebDriver driver;
    List<WebElement> products;
    Actions actions;

    public void hoverAndClick(String tabName, String clickItem) throws InterruptedException {
        actions = new Actions(driver);
        WebElement tabElement = driver.findElement(By.xpath("//a[contains(@href, '" + tabName.toLowerCase() + "')]"));
        actions.moveToElement(tabElement).perform();
        Thread.sleep(5000);
        WebElement clickElement = driver.findElement(By.xpath("//a[contains(@href, '" + clickItem.toLowerCase() + "')]"));
        clickElement.click();
        Thread.sleep(3000);
        products = driver.findElements(Locators.PRODUCTS);
        for (WebElement el : products) {
            Thread.sleep(5000);
            String productName = el.findElement(Locators.PRODUCT_EXPECTED_NAMES).getText();
            String productPrice = el.findElement(Locators.PRODUCT_EXPECTED_PRICES).getText();
            System.out.println(productName);
            System.out.println(productPrice);
        }
    }

    public void selectAsideTab(String asideTab, String tabCategory) throws InterruptedException {
        WebElement asideOption = driver.findElement(By.xpath("//button[@data-test-id-facet-head-name='" + asideTab + "']"));
        asideOption.click();
        Thread.sleep(5000);
        WebElement category = driver.findElement(By.xpath("//aside[@id='searchFilters']//a//span[text()='" + tabCategory + "']"));
        Thread.sleep(5000);
        category.click();
        Thread.sleep(5000);
    }

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://6pm.com");
        driver.manage().window().maximize();
    }

    @Test
    @Tag("First")
    public void testProducts() throws InterruptedException {
        hoverAndClick("Bags", "Luggage");
        if (!products.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(products.size());
            WebElement randomTab = products.get(randomIndex);
            String expectedName = randomTab.findElement(Locators.PRODUCT_EXPECTED_NAMES).getText();
            String expectedPrice = randomTab.findElement(Locators.PRODUCT_EXPECTED_PRICES).getText();
            randomTab.click();
            Thread.sleep(5000);
            String actualName = driver.findElement(Locators.PRODUCT_ACTUAL_NAME).getText();
            String actualPrice = driver.findElement(Locators.PRODUCT_ACTUAL_PRICE).getText();
            Assertions.assertEquals(expectedName, actualName, "Actual name should match the expected name");
            Assertions.assertEquals(expectedPrice, actualPrice, "Actual price should match the expected price");
            Thread.sleep(5000);
            WebElement addToShoppingBag = driver.findElement(Locators.ADD_TO_BAG_BUTTON);
            addToShoppingBag.click();
            Thread.sleep(5000);
            WebElement removeElement = driver.findElement(Locators.REMOVE_FROM_BAG_BUTTON);
            removeElement.click();
            Thread.sleep(5000);
            Assertions.assertNotNull(Locators.EMPTY_BAG, "Bag should be empty");
            WebElement closeButton = driver.findElement(Locators.SELECTOR_BUTTON_CLOSE);
            Thread.sleep(3000);
            closeButton.click();

        } else {
            System.out.println("No luggage found!");
        }

    }

    @Test
    @Tag("Second")
    public void testProductSelection() throws InterruptedException {
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(Locators.CLOTHING_TAB)).perform();
        Thread.sleep(7000);
        WebElement selectT_shirt = driver.findElement(Locators.SELECTOR_T_SHIRT);
        Thread.sleep(5000);
        selectT_shirt.click();
        Thread.sleep(5000);
        selectAsideTab("Color", "Brown");
        Thread.sleep(3000);
        String expectedCount = driver.findElement(By.xpath("//a//span[text()='Brown']/following-sibling::span")).getText().replaceAll("[^0-9.]", "");
        String actualCount = driver.findElement(By.xpath("//span[contains(text(), 'items found')]")).getText().replaceAll("[^0-9.]", "");
        Assertions.assertEquals(expectedCount,actualCount, "Expected count should be equal to actual count");
        Thread.sleep(3000);
        WebElement brownRemove = driver.findElement(By.xpath("//a[@aria-label='Remove Brown filter']"));
        Thread.sleep(3000);
        brownRemove.click();
        Thread.sleep(5000);
        boolean isElementRemoved = driver.findElements(By.xpath("//ul[@id='searchSelectedFilters']//a[text()='Brown']")).isEmpty();
        Thread.sleep(5000);
        Assertions.assertTrue(isElementRemoved,"'Brown' filter should be removed after closing!");
        WebElement brownCheckbox = driver.findElement(By.xpath("//ul[contains(@aria-labelledby , 'color')]//a//span[text()='Brown']"));
        Assertions.assertFalse(brownCheckbox.isSelected(),"'Brown' checkbox should be unselected" );
    }

    @AfterAll
    public void closeWebPage() {
        driver.quit();
    }
}