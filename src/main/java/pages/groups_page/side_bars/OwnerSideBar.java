package pages.groups_page.side_bars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.groups_page.cards.MyGroupsCard;
import selenium_helpers.Check;
import selenium_helpers.Element;

import java.util.ArrayList;
import java.util.List;

import static pages.groups_page.GroupsPage.OWNER_SIDEBAR_LOCATOR;

public final class OwnerSideBar {

    private static final By MY_GROUPS_LOCATOR = By.xpath("//*[@id='hook_Block_MyGroupsNavBlock']/div/div[1]/div/a");
    private static final By GROUPS_CARDS_LOCATORS = By.xpath("//*[ @class ='ucard-v __trimmed']");

    private final WebDriver webDriver;

    public OwnerSideBar(final WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public List<MyGroupsCard> clickToMineGroupsSelector() {
        Element.click(webDriver, MY_GROUPS_LOCATOR);
        final List <WebElement> webElements = Check
                .checkListElementsNotEmpty(webDriver, GROUPS_CARDS_LOCATORS);
        return new ArrayList<MyGroupsCard>(){{
            for (final WebElement webElement: webElements) {
                add(new MyGroupsCard(webElement, webDriver));
            }
        }};
    }

    public boolean isVisible() {
        return !Check.checkElementIsInvisible(webDriver, OWNER_SIDEBAR_LOCATOR);
    }
}
