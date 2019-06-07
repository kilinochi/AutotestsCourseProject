package pages.group_page.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.group_page.settings.tabs.AdminsTab;
import pages.group_page.settings.tabs.MembersTab;
import pages.group_page.settings.tabs.MessagesTab;
import selenium_helpers.Check;

public final class GroupSettingsPage {

    private static final By MESSAGES_LOCATOR = By.xpath("//*[@class='nav-side_i  __with-ic']");

    private final WebDriver webDriver;

    public GroupSettingsPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AdminsTab clickToModeratorsTab() {
        Check.checkListElementsNotEmpty(webDriver, MESSAGES_LOCATOR).get(1)
                .click();
        return new AdminsTab(webDriver);
    }

    public MessagesTab clickToMsgSettingsTab() {
        Check.checkListElementsNotEmpty(webDriver, MESSAGES_LOCATOR).get(3)
                .click();
        return new MessagesTab(webDriver);
    }
}

