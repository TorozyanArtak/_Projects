package homework_29_03_25_Tests;

import homework_29_03_25_Pages.DriverGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestClass {

    @BeforeEach
    public void setUp(){
        DriverGenerator.getDriver();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        DriverGenerator.quitDriver();
    }

}
