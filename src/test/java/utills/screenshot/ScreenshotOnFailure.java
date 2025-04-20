package utills.screenshot;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import utills.loggers.Log;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {
    private final WebDriver driver;

    public ScreenshotOnFailure(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            AllureAttachments.attachScreenshot(driver, "screenshot.png");
            Log.info("Screenshot on test failure.");
        }
    }
}
