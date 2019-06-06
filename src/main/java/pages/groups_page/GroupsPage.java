package pages.groups_page;

import pages.BasePage;
import pages.groups_page.dialog_alerts.SelectGroupsDialogAlert;
import pages.groups_page.side_bars.OwnerSideBar;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class GroupsPage extends BasePage {

    private static final By CREATE_GROUP_LOCATOR = By.className("create-group");
    private static final By SEARCH_GROUPS_PLACEHOLDER = By.xpath("//*[@id ='hook_Block_UserGroupSearch2QueryBlock']");
    public static final By OWNER_SIDEBAR_LOCATOR = By.xpath("//*[@id ='hook_Block_MyGroupsNavBlock']");

    private final WebDriver webDriver;

    public GroupsPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
    }

    public SelectGroupsDialogAlert clickToSelectGroupDialogAlert() {
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsDialogAlert(webDriver);
    }

    public OwnerSideBar getOwnerSideBar() {
        Check.checkElementIsDisplayed(webDriver, OWNER_SIDEBAR_LOCATOR);
        return new OwnerSideBar(webDriver);
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(webDriver, SEARCH_GROUPS_PLACEHOLDER);
    }
}