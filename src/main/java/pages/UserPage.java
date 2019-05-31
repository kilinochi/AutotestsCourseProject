package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage{

    private WebDriver webDriver;
    private static final By TOOLBAR_LOCATOR = By.className("toolbar_c");
    private static final By GROUPS_LOCATOR = By.xpath(".//*[@class='navigation']/*[@class='nav-side __navigation']/*[@data-l='t,userAltGroup']");

    UserPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        check();
    }

    public GroupsPage getGroupsPage(){
        webDriver.findElement(GROUPS_LOCATOR).click();
        return new GroupsPage(webDriver);
    }

    @Override
    protected void check() {
        Assert.assertTrue(webDriver.findElement(TOOLBAR_LOCATOR).isDisplayed());
    }
}
