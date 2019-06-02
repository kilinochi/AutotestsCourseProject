package wrappers;

import cards.MyGroupCard;
import dialog_alerts.NotificationDialogAlert;
import dialog_alerts.SelectGroupPageTypeDialogAlert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GroupPage;


import java.util.ArrayList;
import java.util.List;

public class Wrapper {
    public static List<SelectGroupPageTypeDialogAlert> getGroupPage(final List<WebElement> webElements, final WebDriver webDriver) {
        return new ArrayList<SelectGroupPageTypeDialogAlert>(){{
            for (final WebElement webElement: webElements) {
                add(new SelectGroupPageTypeDialogAlert(webElement, webDriver));
            }
        }};
    }
    public static List <MyGroupCard> getMyGroupsCards(final List <WebElement> webElements, final WebDriver webDriver){
        return new ArrayList<MyGroupCard>(){{
            for (final WebElement webElement: webElements) {
                add(new MyGroupCard(webDriver, webElement));
            }
        }};
    }

   /* public static List <NotificationDialogAlert.Notification> getNotifications(final int count, final WebDriver webDriver) {
        return new ArrayList<GroupPage>(){{
            for (int i = 0; i < count; i++) {
                add(new GroupPage(webDriver));
            }
        }};
    }*/

   public static List <NotificationDialogAlert.Notification> getNotifications(final List <WebElement> webElements, final WebDriver webDriver){
       return new ArrayList<NotificationDialogAlert.Notification>(){{
           for (final WebElement webElement : webElements){
               add(new NotificationDialogAlert.NotificationsGroup(webElement, webDriver));
           }
       }};
   }
}