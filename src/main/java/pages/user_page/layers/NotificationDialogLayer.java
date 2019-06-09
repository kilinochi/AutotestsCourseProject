package pages.user_page.layers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.Element;

import java.util.List;

public final class NotificationDialogLayer {

    private static final By NOTIFICATIONS_USERS_LOCATORS = By.xpath("//*[@data-l='nA,LINK_USER,t,user_link']");

    private final WebDriver webDriver;

    public NotificationDialogLayer(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<String> getUserListFromNotifications() {
        return Element.getUserNamesFromNotifications(webDriver, NOTIFICATIONS_USERS_LOCATORS);
    }
}
