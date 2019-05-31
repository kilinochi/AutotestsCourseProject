package pages;

import cards.SelectGroupsCard;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupsPage extends BasePage {

    private final WebDriver webDriver;
    private final By CREATE_GROUP_LOCATOR = By.className("create-group");

    GroupsPage(final WebDriver webDriver) {
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
        Assert.assertTrue(webDriver.findElements(LoginPage.SUBMIT_LOCATOR).isEmpty());
    }
}