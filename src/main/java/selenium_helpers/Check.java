package selenium_helpers;

import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Check {
    public static void checkElementVisible(final WebDriver webDriver, final By locator) throws AssertionError {
        Assert.assertTrue(new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                assert webDriver != null;
                return webDriver.findElement(locator).isDisplayed();
            }
        }));
    }

    public static void checkElementMissing(final WebDriver webDriver, final By locator) {
        Assert.assertTrue(new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                assert webDriver != null;
                return webDriver.findElements(locator).isEmpty();
            }
        }));
    }
    public static void checkEquals(final Object expected,final Object actual) {
        Assert.assertEquals(expected, actual);
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