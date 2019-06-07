package pages.group_page.settings.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.Element;

public final class AdminsTab implements SettingsTab {

    private static final By ADD_MODERATORS_BUTTON_SELECTOR
            = By.xpath("//*[@class='add-stub al add-stub__hor ']");

    private final WebDriver webDriver;

    public AdminsTab(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MembersTab clickToAddToModeratorsButton() {
        Element.click(webDriver, ADD_MODERATORS_BUTTON_SELECTOR);
        return new MembersTab(webDriver);
    }
}
