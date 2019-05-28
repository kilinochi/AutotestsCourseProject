package pages;

import config.AppConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupPage extends BasePage {

    private static final By GROUP_NAME_SELECTOR = By.xpath("//*[ @class ='mctc_name_tx']");
    private static final By SETTINGS_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");
    private static final By DELETE_GROUP_SELECTOR = By.xpath("//*[@class ='u-menu_li __divided __custom']");
    private static final By DELETE_BUTTON_SELECTOR = By.xpath("//*[ @class ='u-menu_li expand-action-item']");

    private final String groupName;
    private final WebDriver webDriver;
    private final String idGroup;

    public GroupPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        idGroup = webDriver.getCurrentUrl().split("\\.")[1].split("/")[2];
        groupName = webDriver.findElement(GROUP_NAME_SELECTOR).getAttribute("innerHTML");
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void deleteGroup() {
        final WebElement settingsUl = webDriver.findElement(SETTINGS_SELECTOR);
        settingsUl.click();
        final WebElement deleteElement = webDriver.findElement(DELETE_GROUP_SELECTOR);
        deleteElement.click();
        final WebElement deleteButtonWebElement = webDriver.findElement(DELETE_BUTTON_SELECTOR);
        deleteButtonWebElement.click();
    }

    @Override
    protected void check() {
        Assert.assertEquals(AppConfig.groupPageName, groupName);
    }
}