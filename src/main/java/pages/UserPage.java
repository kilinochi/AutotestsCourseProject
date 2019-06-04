package pages;

import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public final class UserPage extends BasePage {

    private static final By TOOLBAR_LOCATOR = By.className("toolbar_c");
    private static final By GROUPS_LOCATOR = By.xpath(".//*[@class='navigation']/*[@class='nav-side __navigation']/*[@data-l='t,userAltGroup']");
    private static final By NOTIFICATION_TOOLBAR_LOCATOR = By.xpath("//*[ @data-l ='t,notifications']");
    private static final By NOTIFICATIONS_USERS_LOCATORS = By.xpath("//*[@data-l='nA,LINK_USER,t,user_link']");

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
        return new NotificationDialogAlert();
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(webDriver, TOOLBAR_LOCATOR);
    }

    public final class NotificationDialogAlert {

        private NotificationDialogAlert() {}

        public List<String> getUserNamesWhoSendInvitations() {
            final List <WebElement> webElements = Check
                    .checkListElementsNotEmpty(webDriver, NOTIFICATIONS_USERS_LOCATORS);
            final List <String> result = new ArrayList<>();
            for (final WebElement webElement : webElements) {
                result.add(webElement.getAttribute("innerHTML"));
            }
            return result;
        }
    }
}
