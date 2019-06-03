package selenium_helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {
    public static void click(final WebDriver webDriver, final By locator) {
            final WebElement webElement = Check
                    .checkElementVisible(webDriver, locator);
            Check.elementToBeClickable(webDriver, webElement)
                    .click();
    }

    public static void sendKeys(final WebDriver webDriver, final By locator, final String description) {
            final WebElement webElement = Check
                    .checkElementVisible(webDriver, locator);
            Check.elementToBeClickable(webDriver, webElement)
                    .sendKeys(description);
    }

    public static String getAttribute(final WebDriver webDriver, final By locator, final String attribute) {
        return Check
                .checkElementVisible(webDriver, locator)
                .getAttribute(attribute);
    }
}