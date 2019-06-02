package pages;

import cards.MyGroupCard;
import java.util.List;

import dialog_alerts.SelectGroupsDialogAlert;
import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.Wrapper;

public class GroupsPage extends BasePage {

    private static final By CREATE_GROUP_LOCATOR = By.className("create-group");
    private static final By OWNER_SIDEBAR_LOCATOR = By.xpath("//*[@id ='hook_Block_MyGroupsNavBlock']");
    private static final By GROUPS_CARDS_LOCATORS = By.xpath("//*[ @class ='ucard-v __trimmed']");

    private final WebDriver webDriver;

    GroupsPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        check();
    }

    public SelectGroupsDialogAlert getSelectGroupDialogAlert() {
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsDialogAlert(webDriver);
    }


    public OwnerSideBar getOwnerSideBar() {
        Check.checkElementVisible(webDriver, OWNER_SIDEBAR_LOCATOR);
        return new OwnerSideBar(webDriver);
    }

    @Override
    protected void check() {
        Check.checkElementMissing(webDriver, LoginPage.SUBMIT_LOCATOR);
    }

    public final class OwnerSideBar {

        private final By MY_GROUPS_LOCATOR = By.xpath("//*[@id='hook_Block_MyGroupsNavBlock']/div/div[1]/div/a");
        private final WebDriver webDriver;

        private OwnerSideBar(final WebDriver webDriver){
            this.webDriver = webDriver;
        }

        public List<MyGroupCard> clickToMineGroupsSelector() {
            Element.click(webDriver, MY_GROUPS_LOCATOR);
            final List <WebElement> webElements = Check.checkListElementsNotEmpty(webDriver, GROUPS_CARDS_LOCATORS);
            return Wrapper.getMyGroupsCards(webElements, webDriver);
        }
    }
}