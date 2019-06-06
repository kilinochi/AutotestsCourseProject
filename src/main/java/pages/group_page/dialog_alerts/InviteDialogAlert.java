package pages.group_page.dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.Element;

public final class InviteDialogAlert {

    private static final By INVITE_ALL_FRIEND_SELECTOR = By.xpath("//*[ @class ='irc-vis']");
    private static final By INVITE_FRIENDS_BUTTON = By.xpath("//*[ @class ='button-pro form-actions_yes']");

    private final WebDriver webDriver;

    public InviteDialogAlert(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public InviteDialogAlert selectAllFriendsForInvite(){
        Element.click(webDriver, INVITE_ALL_FRIEND_SELECTOR);
        return this;
    }

    public InviteDialogAlert clickToInviteButton(){
        Element.click(webDriver, INVITE_FRIENDS_BUTTON);
        return this;
    }
}