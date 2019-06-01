package pages;

import cards.SelectGroupsCard;
import selenium_helpers.Check;
import selenium_helpers.Element;
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
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsCard(webDriver);
    }

    @Override
    protected void check() {
        Check.checkElementMissing(webDriver, LoginPage.SUBMIT_LOCATOR);
    }
}