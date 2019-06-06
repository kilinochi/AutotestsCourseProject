package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.Element;

public final class GroupsSettingsPage {

    private static final By MODERATORS_LI_SELECTOR = By.xpath("//*[@class='nav-side_i  __ac __with-ic']");
    private static final By ADD_MODERATORS_BUTTON_SELECTOR = By.xpath("//*[@class='add-stub al add-stub__hor ']");

    private final WebDriver webDriver;

    public GroupsSettingsPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    void clickToModeratorsTab() {
        Element.click(webDriver, MODERATORS_LI_SELECTOR);
    }
}

