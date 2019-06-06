package selenium_helpers;

import java.util.List;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Check {
    public static WebElement checkElementIsDisplayed(final WebDriver webDriver, final By locator) throws AssertionError {
        final WebElement result = webDriver.findElement(locator);
        Assert.assertTrue(new WebDriverWait(webDriver, 15).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                assert webDriver != null;
                return result.isDisplayed();
            }
        }));
        return result;
    }

    static WebElement checkElementToBeClickAble(final WebDriver webDriver, final WebElement webElement) {
        new WebDriverWait(webDriver, 15).until(ExpectedConditions
                .elementToBeClickable(webElement));
        return webElement;
    }

    public static boolean checkElementIsInvisible(final WebDriver webDriver, final By locator) {
        final WebElement webElement = webDriver.findElement(locator);
        return !webElement.isDisplayed();
    }

    public static List <WebElement> checkListElementsNotEmpty(final WebDriver webDriver, final By locator) {
        final List<WebElement> result = webDriver.findElements(locator);
        Assert.assertTrue(new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>(){
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return !result.isEmpty();
            }
        }));
        return result;
    }
}