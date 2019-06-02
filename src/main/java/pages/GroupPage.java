package pages;

import config.AppConfig;
import dialog_alerts.InviteDialogAlert;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupPage extends BasePage {

    private static final By GROUP_NAME_SELECTOR = By.xpath("//*[ @class ='mctc_name_tx']");
    private static final By SETTINGS_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By DELETE_GROUP_SELECTOR = By.xpath("//*[@class ='u-menu_li __divided __custom']");
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    public static final By RESTRICTION_LOCATOR = By.xpath("//*[@class ='stub-empty __18plus']");
    private static final By INVITE_TO_GROUP_BUTTON = By.xpath("//*[ @class ='u-menu_li __hl __custom']");

    private final String groupName;
    private final WebDriver webDriver;

    public GroupPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        check();
        groupName = Element.getAttribute(webDriver, GROUP_NAME_SELECTOR, "innerHTML");
    }

    public String getGroupName() {
        return groupName;
    }

    public InviteDialogAlert clickToInviteButton() {
        Element.click(webDriver, INVITE_TO_GROUP_BUTTON);
        return new InviteDialogAlert(webDriver);
    }

    public void deleteGroup() {
        Element.click(webDriver, SETTINGS_SELECTOR);
        Element.click(webDriver, DELETE_GROUP_SELECTOR);
        Element.click(webDriver, DELETE_BUTTON_SELECTOR);
    }

    @Override
    protected void check() {
        Check.checkElementVisible(webDriver, GROUP_NAME_SELECTOR);
    }
}