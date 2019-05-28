package pages;

import config.AppConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupPage extends BasePage {

    private final String groupName;
    private final WebDriver webDriver;
    private final String urlGroup;

    public GroupPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        urlGroup = webDriver.getCurrentUrl();
        groupName = webDriver.findElement(By.xpath("//*[ @class ='mctc_name_tx']")).getAttribute("innerHTML");
    }

    public String getUrlGroup() {
        return urlGroup;
    }

    @Override
    protected void check() {
        Assert.assertEquals(AppConfig.groupPageName, groupName);
    }
}