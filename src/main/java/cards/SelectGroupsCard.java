package cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.PageWrapper;

import java.util.List;

public class SelectGroupsCard {

    private final WebDriver webDriver;
    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");

    public SelectGroupsCard(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public List<SelectGroupPageTypeCard> getGroupPageList() {
        return PageWrapper.getGroupPage(webDriver.findElements(GROUPS_SELECT_LOCATOR), webDriver);
    }
}