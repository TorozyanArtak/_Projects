package components.dropdown;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropdownComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final String baseXPath;

    private final String dropdownTextXpath = "%s//following-sibling::span/div[text()='%s']";
    private final String optionXpath = "//div[text()='%s']";

    public DropdownComponent(WebDriver driver, String baseXPath) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.baseXPath = baseXPath;
    }

    public DropdownComponent(WebDriver driver, WebDriverWait wait, Actions actions, String baseXPath) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.baseXPath = baseXPath;
    }

    private By dropdownLocator(String dropdown) {
        return By.xpath(String.format(dropdownTextXpath, baseXPath, dropdown));
    }

    private By optionLocator(String option) {
        return By.xpath(String.format(optionXpath, option));
    }

    public void selectOption(String dropdownName, String optionText) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator(dropdownName)));
        actions.moveToElement(dropdown).perform();
        actions.click(dropdown).perform();
        boolean optionFound = false;
        int maxAttempts = 100;
        int attempts = 0;

        while (!optionFound && attempts < maxAttempts) {
            try {
                WebElement option = driver.findElement(optionLocator(optionText));
                actions.moveToElement(option).perform();
                actions.click(option).perform();
                optionFound = true;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                attempts++;
            }
        }
        if (!optionFound) {
            throw new RuntimeException(
                    String.format("Option '%s' not found in dropdown '%s' after %d attempts.", optionText, dropdownName, maxAttempts)
            );

        }
    }
}