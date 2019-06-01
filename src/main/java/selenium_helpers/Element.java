package selenium_helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Element {
    public static void click(final WebDriver webDriver, final By locator) {
        Check.checkElementVisible(webDriver, locator);
        webDriver.findElement(locator).click();
    }
    public static void sendKeys(final WebDriver webDriver, final By locator, final String description) {
        Check.checkElementVisible(webDriver, locator);
        webDriver.findElement(locator).sendKeys(description);
    }
    public static String getAttribute(final WebDriver webDriver, final By locator, final String attribute) {
        Check.checkElementVisible(webDriver, locator);
        return webDriver.findElement(locator).getAttribute(attribute);
    }
}