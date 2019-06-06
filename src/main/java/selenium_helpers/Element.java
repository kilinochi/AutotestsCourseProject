package selenium_helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public static List <String> getUserNamesFromNotifications(final WebDriver webDriver, final By locator) {
        final List <WebElement> webElements =
                Check.checkListElementsNotEmpty(webDriver, locator);
        final List <String> result = new ArrayList<>();
        try {
            for(final WebElement webElement : webElements) {
                Check.checkElementToBeClickAble(webDriver, webElement);
                result.add(webElement.getAttribute("innerHTML"));
            }
        }
        catch (StaleElementReferenceException e) {
            final List <WebElement> webElementsA =
                    Check.checkListElementsNotEmpty(webDriver, locator);
            for(final WebElement webElement : webElementsA) {
                Check.checkElementToBeClickAble(webDriver, webElement);
                result.add(webElement.getAttribute("innerHTML"));
            }
            return result;
        }
        return result;
    }
}