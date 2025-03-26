import org.openqa.selenium.By;

public class Locators {
    public static final By PRODUCTS = By.xpath("//div[@id='products']//article");
    public static final By PRODUCT_EXPECTED_NAMES = By.xpath(".//dt[text()='Product Name']/following-sibling::dd[1]");
    public static final By PRODUCT_EXPECTED_PRICES = By.xpath(".//dt[text()='Price']/following-sibling::dd/span[1]");
    public static final By PRODUCT_ACTUAL_NAME = By.xpath("//span[@itemprop='brand']/following-sibling::span");
    public static final By PRODUCT_ACTUAL_PRICE = By.xpath("//span[@itemprop='price']/span[1]");
    public static final By ADD_TO_BAG_BUTTON = By.xpath("//button[@id='add-to-cart-button']");
    public static final By REMOVE_FROM_BAG_BUTTON = By.xpath("//button[@aria-label = 'Remove Item']");
    public static final By EMPTY_BAG = By.xpath("//p[contains(text(), 'Nothing to see here yet!')]");
    public static final By CLOTHING_TAB = By.xpath("//a[contains(@href , '/c/clothing')]");
    public static final By SELECTOR_T_SHIRT = By.xpath("(//a[contains(text() , 'T-Shirts')])[1]");
    public static final By SELECTOR_BUTTON_CLOSE = By.xpath("//button[@aria-label = 'Close']");
//    public static final By SIGN_IN_BUTTON = By.xpath("//a[contains(text(), 'Sign In')]");
}
