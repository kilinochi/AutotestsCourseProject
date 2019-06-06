package pages.user_page.dialog_alerts;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;

public final class SendMessageDialogAlert  {

    private static final By SEND_MESSAGE_INPUT_AREA_LOCATOR
            = By.xpath("//*[@data-placeholder='Напишите сообщение']");
    private static final By SEND_MESSAGE_BUTTON_LOCATOR =
            By.xpath("//*[@class='button-pro comments_add-controls_save']");
    private static final By GROUP_TAB_LOCATOR =
            By.xpath("//*[@data-l='t,tabGroups']");
    private static final By NAMES_WHO_SENDS_MESSAGES_TO_GROUP_LOCATOR =
            By.xpath("//*[@class='chats_i_h ellip']");

    private final WebDriver webDriver;

    public SendMessageDialogAlert(final WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public SendMessageDialogAlert sendMessage(String message) {
        if(message != null) {
            Element.sendKeys(webDriver, SEND_MESSAGE_INPUT_AREA_LOCATOR, message);
        }
        return this;
    }


    public SendMessageDialogAlert clickToSendMessageButton() {
        Element.click(webDriver, SEND_MESSAGE_BUTTON_LOCATOR);
        return this;
    }

    public SendMessageDialogAlert clickToGroupsTab() {
        Element.click(webDriver, GROUP_TAB_LOCATOR);
        return this;
    }

    public List<String> getUserListFromNotifications() {
        return Element.getUserNamesFromNotifications(webDriver, NAMES_WHO_SENDS_MESSAGES_TO_GROUP_LOCATOR);
    }
}
