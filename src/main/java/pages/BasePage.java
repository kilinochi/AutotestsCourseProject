package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    private WebDriver webDriver;

    BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected abstract void check();
}