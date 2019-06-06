package pages.groups_page.dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.groups_page.cards.GroupPageTypeCard;
import selenium_helpers.Check;

import java.util.ArrayList;
import java.util.List;

public final class SelectGroupsDialogAlert {

    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");

    public final WebDriver webDriver;

    public SelectGroupsDialogAlert(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<GroupPageTypeCard> getGroupPageList() {
        final List <WebElement> webElements = Check
                .checkListElementsNotEmpty(webDriver, GROUPS_SELECT_LOCATOR);
        return new ArrayList<GroupPageTypeCard>(){{
            for (WebElement webElement : webElements) {
                add(new GroupPageTypeCard(webElement, webDriver));
            }
        }};
    }
}
