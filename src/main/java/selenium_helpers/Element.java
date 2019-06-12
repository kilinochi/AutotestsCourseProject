package selenium_helpers;


import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public final class Element {
    private Element() {
    }

    public static void click(final WebDriver webDriver, final By locator) {
        try {
            final WebElement webElement = Check
                    .checkElementIsDisplayed(webDriver, locator);
            Check.checkElementToBeClickable(webDriver, webElement)
                    .click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static void sendKeys(final WebDriver webDriver, final By locator, final String description) {
        try {
            final WebElement webElement = Check
                    .checkElementIsDisplayed(webDriver, locator);
            Check.checkElementToBeClickable(webDriver, webElement)
                    .sendKeys(description);
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static String getAttribute(final WebDriver webDriver, final By locator, final String attribute) {
        try {
            return Check
                    .checkElementIsDisplayed(webDriver, locator)
                    .getAttribute(attribute);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static List<String> getUserNamesFromNotifications(final WebDriver webDriver, final By locator) {
        final List<String> result = new ArrayList<>();
        try {
            final List<WebElement> webElements =
                    Check.checkListElementsNotEmpty(webDriver, locator);
            for (final WebElement webElement : webElements) {
                Check.checkElementToBeClickable(webDriver, webElement);
                result.add(webElement.getAttribute("innerHTML"));
            }
        } catch (StaleElementReferenceException e) {
            final List<WebElement> webElementsA =
                    Check.checkListElementsNotEmpty(webDriver, locator);
            for (final WebElement webElement : webElementsA) {
                Check.checkElementToBeClickable(webDriver, webElement);
                result.add(webElement.getAttribute("innerHTML"));
            }
        }
        return result;
    }
}