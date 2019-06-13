package pages.user_page.layers;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.Element;

public final class SendMessageDialogLayer {

    private static final By SEND_MESSAGE_INPUT_AREA_LOCATOR
            = By.xpath("//*[@data-l ='t,msgInput']");
    private static final By SEND_MESSAGE_BUTTON_LOCATOR =
            By.xpath(".//*[@data-l ='t,sendButton']");
    private static final By GROUP_TAB_LOCATOR =
            By.xpath("//*[@data-l='t,tabGroups']");
    private static final By NAMES_WHO_SENDS_MESSAGES_TO_GROUP_LOCATOR =
            By.xpath("//*[@class='chats_i_h ellip']");

    private final WebDriver webDriver;

    public SendMessageDialogLayer(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SendMessageDialogLayer sendMessage(String message) {
        if (message != null) {
            Element.sendKeys(webDriver, SEND_MESSAGE_INPUT_AREA_LOCATOR, message);
        }
        return this;
    }


    public SendMessageDialogLayer clickToSendMessageButton() {
        Element.click(webDriver, SEND_MESSAGE_BUTTON_LOCATOR);
        return this;
    }

    public SendMessageDialogLayer clickToGroupsTab() {
        Element.click(webDriver, GROUP_TAB_LOCATOR);
        return this;
    }

    public List<String> getUserListFromNotifications() {
        return Element.getUserNamesFromNotifications(webDriver, NAMES_WHO_SENDS_MESSAGES_TO_GROUP_LOCATOR);
    }
}
