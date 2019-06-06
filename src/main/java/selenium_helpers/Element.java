package selenium_helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class Element {
    public static void click(final WebDriver webDriver, final By locator) {
            final WebElement webElement = Check
                    .checkElementIsDisplayed(webDriver, locator);
            Check.checkElementToBeClickAble(webDriver, webElement)
                    .click();
    }

    public static void sendKeys(final WebDriver webDriver, final By locator, final String description) {
            final WebElement webElement = Check
                    .checkElementIsDisplayed(webDriver, locator);
            Check.checkElementToBeClickAble(webDriver, webElement)
                    .sendKeys(description);
    }

    public static String getAttribute(final WebDriver webDriver, final By locator, final String attribute) {
        return Check
                .checkElementIsDisplayed(webDriver, locator)
                .getAttribute(attribute);
    }
}