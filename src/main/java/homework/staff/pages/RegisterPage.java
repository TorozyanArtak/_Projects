package homework.staff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//div[text()='First name']//following-sibling::div/input")
    private WebElement firstname;
    @FindBy(xpath = "//div[text()='Last name']//following-sibling::div/input")
    private WebElement lastname;
    @FindBy(xpath = "//div[text()=\"Password\"]//following-sibling::div/input")
    private WebElement passwordField;
    @FindBy(xpath = "//div[text()='Confirm password']//following-sibling::div/input")
    private WebElement confirmPasswordField;
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

    private void setPasswordToField(WebElement passwordField) {
        String password = System.getProperty("password");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password must be provided via -Dpassword=yourPassword");
        }
        WebElement field = wait.until(ExpectedConditions.visibilityOf(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    public void setPassword() {
        setPasswordToField(passwordField);
    }

    public void confirmPassword() {
        setPasswordToField(confirmPasswordField);
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

    private void enterEmail(String emailText) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(email));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(emailText);
        emailField.sendKeys(Keys.TAB);
    }

    public boolean enterEmailAndCheckInvalidMessage(String emailText, String expectedErrorText) {
        enterEmail(emailText);
        return wait.until(ExpectedConditions.textToBePresentInElement(emailError, expectedErrorText));
    }

    public boolean enterValidEmailAndVerifyNoError(String emailText) {
        enterEmail(emailText);
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
