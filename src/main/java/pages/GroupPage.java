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
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[@id ='hook_FormButton_button_delete']");
    private static final By INVITE_TO_GROUP_BUTTON = By.xpath("//*[ @class ='u-menu_li __hl __custom']");
    private static final By CREATE_POST_LOCATOR = By.xpath("//*[@class='pf-with-ava __group-main __with-ava']");
    private static final By INVITE_ALL_FRIEND_SELECTOR = By.xpath("//*[ @class ='irc-vis']");
    private static final By INVITE_FRIENDS_BUTTON = By.xpath("//*[ @class ='button-pro form-actions_yes']");

    private static final By MUSICS_LOCATORS = By.xpath("//*[@data-action='track']/div[2]/div[1]/div[1]");
    private static final By INPUT_PLACEHOLDER_LOCATOR = By.xpath("//*[@class='posting_itx emoji-tx h-mod js-ok-e js-posting-itx ok-posting-handler']");
    private static final By ATTACH_MUSIC_BUTTON = By.xpath("//*[@data-module='postingForm/mediaMusicAddButton']");
    private static final By INPUT_SEARCH_MUSIC_LOCATOR = By.xpath("//*[@data-id='searchInput']");
    private static final By SEND_MUSIC_BUTTON = By.xpath("//*[@class='modal-new_actions __center']/div[1]/a[1]");
    private static final By CREATE_POST_BUTTON_LOCATOR = By.xpath("//*[@class='posting_f_ac']/div[2]");

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
        Check.checkElementIsDisplayed(webDriver, GROUP_NAME_SELECTOR);
        createPostElem = Check.checkElementIsDisplayed(webDriver, CREATE_POST_LOCATOR);
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

        public void inputText(final String text) {
            Element.sendKeys(webDriver, INPUT_PLACEHOLDER_LOCATOR, text);
        }

        public void searchAndAttachMusic(final String music, int from, int to) {
            Element.click(webDriver, ATTACH_MUSIC_BUTTON);
            Element.sendKeys(webDriver, INPUT_SEARCH_MUSIC_LOCATOR, music);
            Check.checkListElementsNotEmpty(webDriver, MUSICS_LOCATORS)
                    .stream()
                    .skip(from)
                    .limit(to-from+1)
                    .forEach(WebElement::click);
            Element.click(webDriver, SEND_MUSIC_BUTTON);
        }

        public void clickToCreatePostButton() {
            Element.click(webDriver, CREATE_POST_BUTTON_LOCATOR);
        }
    }
}