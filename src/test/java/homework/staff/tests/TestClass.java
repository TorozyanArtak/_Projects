package homework.staff.tests;

import homework.staff.pages.DriverGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestClass {

    @BeforeEach
    public void setUp(){
        DriverGenerator.initDriver();
    }

    @AfterEach
    public void tearDown()  {
        DriverGenerator.quitDriver();
    }
}
