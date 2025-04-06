package homework_29_03_25_Tests;

import homework_29_03_25_Pages.DriverGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestClass {

    @BeforeEach
    public void setUp(){
        DriverGenerator.initBrowser("https://staff.am/");
    }

    @AfterEach
    public void tearDown()  {
        DriverGenerator.quitDriver();
    }

}
