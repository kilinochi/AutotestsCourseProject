package pages.group_page.settings.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.group_page.cards.MemberCard;
import selenium_helpers.Check;
import selenium_helpers.Element;

import java.util.ArrayList;
import java.util.List;

public final class MembersTab {

    private static final By FIND_USER_PLACEHOLDER = By.xpath("//*[@name='st.query']");
    private static final By USER_GRID_CARD_LOCATOR = By.xpath("//*[@class='user-grid-card']");

    private final WebDriver webDriver;

    public MembersTab(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MembersTab searchMembersByName(final String name) {
        Element.sendKeys(webDriver, FIND_USER_PLACEHOLDER, name);
        return this;
    }


    public List<String> membersNames() {
        final List<WebElement> webElements
                = Check.checkListElementsNotEmpty(webDriver, USER_GRID_CARD_LOCATOR);
        return new ArrayList<String>() {
            {
                for (WebElement webElement : webElements) {
                    add(new MemberCard(webElement).getUserName());
                }
            }
        };
    }
}
