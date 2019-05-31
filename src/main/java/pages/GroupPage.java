package pages;

import config.AppConfig;
import model.Element;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupPage extends BasePage {

    private static final By GROUP_NAME_SELECTOR = By.xpath("//*[ @class ='mctc_name_tx']");
    private static final By SETTINGS_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By DELETE_GROUP_SELECTOR = By.xpath("//*[@class ='u-menu_li __divided __custom']");
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    public static final By RESTRICTION_LOCATOR = By.xpath("//*[@class ='stub-empty __18plus']");

    private final String groupName;
    private final WebDriver webDriver;

    public GroupPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        groupName = webDriver.findElement(GROUP_NAME_SELECTOR).getAttribute("innerHTML");
        check();
    }

    public String getGroupName() {
        return groupName;
    }

    public void deleteGroup() {
        Element.click(webDriver.findElement(SETTINGS_SELECTOR));
        Element.click(webDriver.findElement(DELETE_GROUP_SELECTOR));
        Element.click(webDriver.findElement(DELETE_BUTTON_SELECTOR));
    }

    @Override
    protected void check() {
        Assert.assertEquals(AppConfig.groupPageName, groupName);
    }
}