package pages.user_page.dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;

import java.util.ArrayList;
import java.util.List;

public final class NotificationDialogAlert  {

    private static final By NOTIFICATIONS_USERS_LOCATORS = By.xpath("//*[@data-l='nA,LINK_USER,t,user_link']");

    private final WebDriver webDriver;

    public NotificationDialogAlert(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<String> getUserListFromNotifications() {
        return Element.getUserNamesFromNotifications(webDriver, NOTIFICATIONS_USERS_LOCATORS);
    }
}
