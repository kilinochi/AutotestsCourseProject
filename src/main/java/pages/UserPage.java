package pages;

import dialog_alerts.NotificationDialogAlert;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage {

    private static final By TOOLBAR_LOCATOR = By.className("toolbar_c");
    private static final By GROUPS_LOCATOR = By.xpath(".//*[@class='navigation']/*[@class='nav-side __navigation']/*[@data-l='t,userAltGroup']");
    private static final By NOTIFICATION_TOOLBAR_LOCATOR = By.xpath("//*[ @data-l ='t,notifications']");

    private final WebDriver webDriver;

    UserPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
    }

    public GroupsPage clickToGroupsSelector(){
        Element.click(webDriver,GROUPS_LOCATOR);
        return new GroupsPage(webDriver);
    }

    public NotificationDialogAlert clickToNotificationDialogAlert(){
        Element.click(webDriver, NOTIFICATION_TOOLBAR_LOCATOR);
        return new NotificationDialogAlert(webDriver);
    }

    @Override
    protected void check() {
        Check.checkElementVisible(webDriver, TOOLBAR_LOCATOR);
    }
}
