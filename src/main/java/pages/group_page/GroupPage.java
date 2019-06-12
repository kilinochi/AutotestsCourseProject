package pages.group_page;

import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.group_page.layers.InviteDialogLayer;
import pages.group_page.layers.PostDialogLayer;
import pages.group_page.settings.GroupSettingsPage;
import pages.user_page.layers.SendMessageDialogLayer;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public final class GroupPage extends BasePage {

    private static final By GROUP_NAME_SELECTOR = By.xpath("//*[ @class ='mctc_name_tx']");
    private static final By SETTINGS_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By DELETE_GROUP_SELECTOR = By.xpath("//*[@class ='u-menu_li __divided __custom']");
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[@id ='hook_FormButton_button_delete']");
    private static final By INVITE_TO_GROUP_BUTTON = By.xpath("//*[ @class ='u-menu_li __hl __custom']");
    private static final By CREATE_POST_LOCATOR = By.xpath("//*[@class='pf-with-ava __group-main __with-ava']");
    private static final By UL_OPTIONS_MENU = By.xpath("//*[@class='u-menu_a toggle-dropdown']");
    private static final By LI_OPTIONS_MENU = By.xpath("//*[@class='u-menu_li  __custom']");
    private static final By POST_LOCATORS = By.xpath("//*[@class='feed-w']");
    private static final By SEND_MESSAGE_TO_GROUP_BUTTON_LOCATOR = By.xpath("//*[@class='u-menu_li u-menu_li__pro __hl']");
    private static final By JOIN_TO_GROUP_BUTTON_LOCATOR =
            By.xpath("//*[@class='button-pro __wide']");

    private static final Object NO_NAME = new Object();

    private final String groupName;
    private final String groupId;
    private final WebDriver webDriver;

    public GroupPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
        groupName
                =  Element.getAttribute(webDriver, GROUP_NAME_SELECTOR, "innerHTML") != null ?
        Element.getAttribute(webDriver, GROUP_NAME_SELECTOR, "innerHTML") : (String) NO_NAME;
        groupId = this.webDriver.getCurrentUrl().split("\\.")[1].split("/")[2];
    }

    public List<WebElement> getAllPosts() {
        return webDriver.findElements(POST_LOCATORS);
    }

    public GroupSettingsPage clickToGroupSettings() {
        Element.click(webDriver, UL_OPTIONS_MENU);
        Check.checkListElementsNotEmpty(webDriver, LI_OPTIONS_MENU)
                .get(1).click();
        return new GroupSettingsPage(webDriver);
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public InviteDialogLayer clickToInviteButton() {
        Element.click(webDriver, INVITE_TO_GROUP_BUTTON);
        return new InviteDialogLayer(webDriver);
    }

    public PostDialogLayer clickToCreatePostArea() {
        Element.click(webDriver, CREATE_POST_LOCATOR);
        return new PostDialogLayer(webDriver);
    }

    public SendMessageDialogLayer clickToSendMessageButton() {
        Element.click(webDriver, SEND_MESSAGE_TO_GROUP_BUTTON_LOCATOR);
        return new SendMessageDialogLayer(webDriver);
    }

    public void deleteGroup() {
        Element.click(webDriver, SETTINGS_SELECTOR);
        Element.click(webDriver, DELETE_GROUP_SELECTOR);
        Element.click(webDriver, DELETE_BUTTON_SELECTOR);
    }

    public GroupPage clickToJoinToGroupButton() {
        Element.click(webDriver, JOIN_TO_GROUP_BUTTON_LOCATOR);
        return this;
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(webDriver, GROUP_NAME_SELECTOR);
    }
}