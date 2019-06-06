package pages.group_page.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.group_page.settings.tabs.MessagesTab;
import selenium_helpers.Check;
import selenium_helpers.Element;

public final class GroupSettingsPage {

    private static final By MODERATORS_SELECTOR = By.xpath("//*[@class='nav-side_i  __ac __with-ic']");
    private static final By ADD_MODERATORS_BUTTON_SELECTOR = By.xpath("//*[@class='add-stub al add-stub__hor ']");
    private static final By MESSAGES_LOCATOR = By.xpath("//*[@class='nav-side_i  __with-ic']");

    private final WebDriver webDriver;

    public GroupSettingsPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    void clickToModeratorsTab() {
        Element.click(webDriver, MODERATORS_SELECTOR);
    }

    public MessagesTab clickToMsgSettingsTab(){
        Check.checkListElementsNotEmpty(webDriver, MESSAGES_LOCATOR).get(3)
            .click();;
        return new MessagesTab(webDriver);
    }
}

