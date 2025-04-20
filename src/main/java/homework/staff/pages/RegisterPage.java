package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class RegisterPage extends BasePage {

    @FindBy(xpath = "//div[text()='First name']//following-sibling::div/input")
    private WebElement firstname;
    @FindBy(xpath = "//div[text()='Last name']//following-sibling::div/input")
    private WebElement lastname;
    @FindBy(xpath = "//div[text()=\"Password\"]//following-sibling::div/input")
    private WebElement password;
    @FindBy(xpath = "//div[text()='Confirm password']//following-sibling::div/input")
    private WebElement confirmPassword;
    @FindBy(xpath = "(//div[text()='Register'])[3]")
    private WebElement register;
    @FindBy(xpath = "//div[text()='Email']//following-sibling::div/input")
    private WebElement email;
    @FindBy(xpath = "//div[text()='The field must be a valid email address.']")
    private WebElement emailError;
    private final By agreeToTermLoc = RelativeLocator.with(By.tagName("div")).toLeftOf(By.xpath("//span[contains(text(), 'I agree')]"));


    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstname)).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastname)).sendKeys(lastName);
    }

    public void setPassword() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/data.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(prop.getProperty("password"));
    }

    public void confirmPassword() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/data.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(confirmPassword)).sendKeys(prop.getProperty("password"));
    }

    public void agreeToTerms() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(agreeToTermLoc));
        actions.moveToElement(checkbox).perform();

        if (!checkbox.isSelected()) {
            actions.click(checkbox).perform();
        }
    }

    public void submitRegister() {
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(register))).click().perform();
    }

    public boolean enterEmailAndCheckInvalidMessage(String emailText, String expectedErrorText) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(email));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(emailText);
        emailField.sendKeys(Keys.TAB);
        return wait.until(ExpectedConditions.textToBePresentInElement(emailError, expectedErrorText));
    }

    public boolean enterValidEmailAndVerifyNoError(String emailText) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(email));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(emailText);
        emailField.sendKeys(Keys.TAB);
        try {
            return wait.until(ExpectedConditions.invisibilityOf(emailError))
                    || wait.until(_ -> emailError.getText().isBlank());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getRegisterButtonStyle() {
        return wait.until(ExpectedConditions.visibilityOf(register)).getDomAttribute("style");
    }

}
