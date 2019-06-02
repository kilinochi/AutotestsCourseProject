package dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GroupPage;
import selenium_helpers.Check;
import selenium_helpers.Element;
import wrappers.Wrapper;

import java.util.List;

public class NotificationDialogAlert {

    private static final By NOTIFICATIONS_GROUPS_PHOTO_LOCATORS = By.xpath("//*[@class='ucard_img stub-img stub-group-interest-64 stub-img__64']/img");
    private static final By GROUPS_NOTIFICATION_TAB = By.xpath("//*[@id='ntf_layer_menu_link_Groups']");

    private final WebDriver webDriver;

    public NotificationDialogAlert(final WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public List <Notification> getNotificationsGroups() {
        Element.click(webDriver, GROUPS_NOTIFICATION_TAB);
        final List <WebElement> webElements = Check.checkListElementsNotEmpty(webDriver, NOTIFICATIONS_GROUPS_PHOTO_LOCATORS);
        return Wrapper.getNotifications(webElements, webDriver);
    }


    public static class NotificationsGroup implements Notification {

        private final WebElement webElement;
        private final WebDriver webDriver;
        private final String nameNotification;

        public NotificationsGroup(WebElement webElement, WebDriver webDriver){
            this.webElement = webElement;
            this.webDriver = webDriver;
            this.nameNotification = webElement.getAttribute("alt");
        }


        public GroupPage clickToGroupIcon(){
            webElement.click();
            return new GroupPage(webDriver);
        }

        @Override
        public String getNotificationName() {
            return nameNotification;
        }
    }

    public interface Notification {
        String getNotificationName();
    }
}