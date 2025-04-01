package homework_29_03_25_Pages;

import org.openqa.selenium.WebDriver;

abstract class BasePage {
    protected WebDriver driver = DriverGenerator.getDriver();
    public HeaderComponent header = new HeaderComponent();
    public FooterComponent footer = new FooterComponent();

}
