package pages.groups_page;

import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;
import pages.groups_page.layers.SelectGroupsDialogLayer;
import pages.groups_page.side_bars.GroupsSideBar;
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

    public SelectGroupsDialogLayer clickToSelectGroupDialogAlert() {
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsDialogLayer(webDriver);
    }

    public GroupsSideBar getOwnerSideBar() {
        Check.checkElementIsDisplayed(webDriver, OWNER_SIDEBAR_LOCATOR);
        return new GroupsSideBar(webDriver);
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(webDriver, SEARCH_GROUPS_PLACEHOLDER);
    }
}