package homework.staff.tests;

import homework.staff.pages.DriverGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.screenshot.ScreenshotOnFailure;

public class BaseTest {

    protected final WebDriver driver = DriverGenerator.getDriver();

    @RegisterExtension
    protected ScreenshotOnFailure screenshot = new ScreenshotOnFailure(driver);

    @BeforeEach
    public void setUp(){
        DriverGenerator.getDriver();
    }

    @AfterEach
    public void tearDown()  {
        DriverGenerator.quitDriver();
    }
}
