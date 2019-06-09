package pages.groups_page.layers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.groups_page.cards.GroupPageTypeCard;
import selenium_helpers.Check;

import java.util.ArrayList;
import java.util.List;

public final class SelectGroupsDialogLayer {

    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");

    public final WebDriver webDriver;

    public SelectGroupsDialogLayer(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<GroupPageTypeCard> getGroupPageList() {
        final List<WebElement> webElements = Check
                .checkListElementsNotEmpty(webDriver, GROUPS_SELECT_LOCATOR);
        return new ArrayList<GroupPageTypeCard>() {{
            for (WebElement webElement : webElements) {
                add(new GroupPageTypeCard(webElement, webDriver));
            }
        }};
    }
}
