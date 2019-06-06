package pages.groups_page.cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.group_page.GroupPage;

public final class MyGroupsCard {

    private final WebElement mainWebElement;
    private final WebDriver webDriver;

    public MyGroupsCard(final WebElement webElement, final WebDriver webDriver) {
        this.mainWebElement = webElement;
        this.webDriver = webDriver;
    }

    public GroupPage clickToGroup() {
        mainWebElement.click();
        return new GroupPage(webDriver);
    }
}