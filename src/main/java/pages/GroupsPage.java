package pages;

import cards.SelectGroupsCard;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupsPage extends BasePage {

    private final WebDriver webDriver;
    private final By CREATE_GROUP_LOCATOR = By.className("create-group");
    private final By FIND_GROUP_LOCATOR = By.id(".//*[@id='query_userAltGroupSearch']");

    GroupsPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        check();
    }


    public SelectGroupsCard getSelectGroupCard() {
        webDriver.findElement(CREATE_GROUP_LOCATOR).click();
        return new SelectGroupsCard(webDriver);
    }

    @Override
    protected void check() {
        Assert.assertTrue(webDriver.findElements(By.xpath(".//input[@data-l='t,sign_in']")).isEmpty());
    }
}