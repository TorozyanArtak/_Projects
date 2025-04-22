package homework.staff.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RegistrationTestSteps;

public class RegistrationTest extends BaseTest{
    RegistrationTestSteps steps = new RegistrationTestSteps();

    @Test
    @DisplayName("Register with valid email after invalid input")
    void testRegistration() {
        steps.openMainPage();
        steps.selectCandidate();
        steps.setFirstName("Artak");
        steps.setLastName("Torozyan");
        steps.fillBirthday("1985", "January", "19");
        Assertions.assertTrue(steps.enterEmailAndCheckInvalidMessage("artak.", "The field must be a valid email address.")
                , "Expected an invalid email error message when entering 'artak.' but it was not displayed.");
        Assertions.assertTrue(steps.enterValidEmailAndVerifyNoError("art85@mail.ru")
                , "Error message should not be displayed for a valid email");
        steps.fillPasswords();
        String expectedStyleOfRegisterButton = steps.getExpectedRegisterButtonStyle();
        steps.agreeToTerms();
        String actualStyleOfRegisterButton = steps.getActualRegisterButtonStyle();
        steps.submitRegistration();
        Assertions.assertNotEquals(expectedStyleOfRegisterButton, actualStyleOfRegisterButton
                , "Register button style should not match expected value");
    }
}