package pages.group_page.settings.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import selenium_helpers.Element;

public final class MessagesTab implements SettingsTab {

    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//*[@name='button_save_settings']");
    private static final By EVERYBODY_SELECT_BOX = By.xpath("//*[@value='EVERYBODY']");
    private static final By GROUP_PROFILE_SELECTOR = By.xpath("//*[@class='compact-profile']");

    private final WebDriver webDriver;

    public MessagesTab(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public GroupPage clickToGroupProfile() {
        Element.click(webDriver, GROUP_PROFILE_SELECTOR);
        return new GroupPage(webDriver);
    }

    public MessagesTab clickToSaveButton() {
        Element.click(webDriver, SAVE_BUTTON_LOCATOR);
        return this;
    }

    public MessagesTab selectCategoryWhoCanSendMessage(Category category) {
        switch (category) {
            case EVERYBODY:
                Element.click(webDriver, EVERYBODY_SELECT_BOX);
                break;
            case NOBODY:
                break;
            case MEMBERS:
                break;
            default:
        }
        return this;
    }
}