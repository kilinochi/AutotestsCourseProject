package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver webDriver;

    public BasePage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected abstract void check();
}