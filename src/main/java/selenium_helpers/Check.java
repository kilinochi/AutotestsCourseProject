package selenium_helpers;

import java.util.List;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Check {
    public static WebElement checkElementIsDisplayed(final WebDriver webDriver
            , final By locator) throws NoSuchElementException {
        final WebElement result = webDriver.findElement(locator);
        Assert.assertTrue(new WebDriverWait(webDriver, 15).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return result.isDisplayed();
            }
        }));
        return result;
    }

    static WebElement checkElementToBeClickable(final WebDriver webDriver
            , final WebElement webElement) throws NoSuchElementException {
        Assert.assertTrue(new WebDriverWait(webDriver, 15)
                .until(ExpectedConditions.elementToBeClickable(webElement)).isDisplayed());
        return webElement;
    }

    public static boolean checkElementIsInvisible(final WebDriver webDriver
            , final By locator) throws NoSuchElementException {
        final WebElement webElement = webDriver.findElement(locator);
        return !webElement.isDisplayed();
    }

    public static List<WebElement> checkListElementsNotEmpty(final WebDriver webDriver, final By locator) {
        final List<WebElement> result = webDriver.findElements(locator);
        Assert.assertTrue(new WebDriverWait(webDriver, 20).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return !result.isEmpty();
            }
        }));
        return result;
    }

    private Check() {
    }
}