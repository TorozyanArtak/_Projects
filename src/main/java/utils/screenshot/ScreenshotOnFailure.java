package utils.screenshot;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.loggers.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {
    private final WebDriver driver;

    public ScreenshotOnFailure(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            saveScreenshotToPackage(context.getDisplayName());
            AllureAttachments.attachScreenshot(driver, "screenshot.png");
            Log.info("Screenshot on test failure.");
        }
    }

    private void saveScreenshotToPackage(String testName) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path directory = Paths.get("screenshots");
            try {
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                String fileName = testName.replaceAll("[^a-zA-Z0-9.-]", "_") + ".png";
                Path destination = directory.resolve(fileName);
                Files.copy(screenshot.toPath(), destination);
                Log.info("Screenshot saved to: " + destination);
            } catch (IOException e) {
                Log.error("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}









