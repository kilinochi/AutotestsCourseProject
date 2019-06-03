package pages;

import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class GroupPage extends BasePage {

    public static final By RESTRICTION_LOCATOR = By.xpath("//*[@class ='stub-empty __18plus']");

    private static final By GROUP_NAME_SELECTOR = By.xpath("//*[ @class ='mctc_name_tx']");
    private static final By SETTINGS_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By DELETE_GROUP_SELECTOR = By.xpath("//*[@class ='u-menu_li __divided __custom']");
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By INVITE_TO_GROUP_BUTTON = By.xpath("//*[ @class ='u-menu_li __hl __custom']");
    private static final By CREATE_POST_LOCATOR = By.xpath("//*[@class='pf-with-ava __group-main __with-ava']");
    private static final By INVITE_ALL_FRIEND_SELECTOR = By.xpath("//*[ @class ='irc-vis']");
    private static final By INVITE_FRIENDS_BUTTON = By.xpath("//*[ @class ='button-pro form-actions_yes']");

    private final String groupName;
    private final String groupId;
    private final WebDriver webDriver;

    private WebElement createPostElem;

    GroupPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
        groupName = Element.getAttribute(webDriver, GROUP_NAME_SELECTOR, "innerHTML");
        groupId = this.webDriver.getCurrentUrl().split("\\.")[1].split("/")[2];
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public InviteDialogAlert clickToInviteButton() {
        Element.click(webDriver, INVITE_TO_GROUP_BUTTON);
        return new InviteDialogAlert();
    }

    public CreatePostDialogAlert clickToCreatePostArea() {
        createPostElem.click();
        return new CreatePostDialogAlert();
    }

    public void deleteGroup() {
        Element.click(webDriver, SETTINGS_SELECTOR);
        Element.click(webDriver, DELETE_GROUP_SELECTOR);
        Element.click(webDriver, DELETE_BUTTON_SELECTOR);
    }

    @Override
    protected void check() {
        Check.checkElementVisible(webDriver, GROUP_NAME_SELECTOR);
        createPostElem = Check.checkElementVisible(webDriver, CREATE_POST_LOCATOR);
    }

    public final class InviteDialogAlert {
        private InviteDialogAlert() {}

        public void selectAllFriendsForInvite(){
            Element.click(webDriver, INVITE_ALL_FRIEND_SELECTOR);
        }

        public void clickToInviteButton(){
            Element.click(webDriver, INVITE_FRIENDS_BUTTON);
        }
    }

    public final class CreatePostDialogAlert {
        private CreatePostDialogAlert(){}


    }
}