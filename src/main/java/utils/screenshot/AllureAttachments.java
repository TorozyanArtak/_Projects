package utils.screenshot;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.loggers.Log;

import java.io.ByteArrayInputStream;

public class AllureAttachments {

    public static void attachScreenshot(WebDriver driver, String name){
        try {
            byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        }catch (Exception e){
            Log.info("Failed to take screenshot: " + e.getMessage());
        }
    }
}
