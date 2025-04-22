package steps;

import components.dropdown.DropdownComponent;
import homework.staff.pages.DriverGenerator;
import homework.staff.pages.RegisterPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utills.screenshot.AllureAttachments;

public class RegistrationTestSteps {
    private final WebDriver driver = DriverGenerator.getDriver();
    private final RegisterPage registerPage = new RegisterPage();
    private final DropdownComponent dropdown = new DropdownComponent(driver, "//span[input[@type='search']]");

    @Step("Open main page")
    public void openMainPage() {
        DriverGenerator.getDriver().get("https://staff.am/");
    }

    @Step("Select 'Candidate' from header")
    public void selectCandidate() {
        registerPage.header.hoverAndClick("Candidate");
    }

    @Step("Fill birthday dropdowns: Year: {year}, Month: {month}, Day: {day}")
    public void fillBirthday(String year, String month, String day) {
        dropdown.selectOption("Year", year);
        dropdown.selectOption("Month", month);
        dropdown.selectOption("Day", day);
    }

    @Step("Set first name: {firstName}")
    public void setFirstName(String firstName) {
        registerPage.setFirstName(firstName);
    }

    @Step("Set last name: {lastName}")
    public void setLastName(String lastName) {
        registerPage.setLastName(lastName);
    }

    @Step("Fill password fields")
    public void fillPasswords() {
        registerPage.setPassword();
        registerPage.confirmPassword();
    }

    @Step("Agree to Terms and Conditions")
    public void agreeToTerms() {
        registerPage.agreeToTerms();
    }

    @Step("Enter invalid email and verify error message is displayed'{expectedErrorText}'")
    public boolean enterEmailAndCheckInvalidMessage(String email, String expectedErrorText) {
        AllureAttachments.attachScreenshot(driver,"Shows expected error");
        return registerPage.enterEmailAndCheckInvalidMessage(email, expectedErrorText);
    }

    @Step("Enter valid email and verify no error message is displayed")
    public boolean enterValidEmailAndVerifyNoError(String email) {
        return registerPage.enterValidEmailAndVerifyNoError(email);
    }

    @Step("Submit registration")
    public void submitRegistration() {
        registerPage.submitRegister();
    }

    @Step("Get expected style value for the Register button")
    public String getExpectedRegisterButtonStyle() {
        return registerPage.getRegisterButtonStyle();
    }

    @Step("Get actual style value for the Register button")
    public String getActualRegisterButtonStyle() {
        return registerPage.getRegisterButtonStyle();
    }

}
