package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class GroupsPage extends BasePage {

    private static final By CREATE_GROUP_LOCATOR = By.className("create-group");
    private static final By OWNER_SIDEBAR_LOCATOR = By.xpath("//*[@id ='hook_Block_MyGroupsNavBlock']");
    private static final By GROUPS_CARDS_LOCATORS = By.xpath("//*[ @class ='ucard-v __trimmed']");
    private static final By WIDGET_LOCATOR = By.xpath("//*[@class='wide-profile portlet-ui small-widgets __over-additional-column mainContentDoubleColumn gm-cl-aft']");
    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");
    private static final By NAME_GROUP_LOCATOR = By.name("st.layer.name");
    private static final By DESCRIPTION_LOCATOR = By.id("field_description");
    private static final By CATEGORY_MENU_AUTO = By.xpath(".//*[@value='subcatVal12001']");
    private static final By AGE_18_SELECTOR = By.xpath(".//*[@value='18']");
    private static final By CREATE_GROUP_BUTTON = By.name("button_create");
    private static final By MY_GROUPS_LOCATOR = By.xpath("//*[@id='hook_Block_MyGroupsNavBlock']/div/div[1]/div/a");

    private final WebDriver webDriver;

    GroupsPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
    }

    public SelectGroupsDialogAlert clickToSelectGroupDialogAlert() {
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsDialogAlert();
    }

    public OwnerSideBar getOwnerSideBar() {
        Check.checkElementVisible(webDriver, OWNER_SIDEBAR_LOCATOR);
        return new OwnerSideBar();
    }

    @Override
    protected void check() {
        Check.checkElementVisible(webDriver, WIDGET_LOCATOR);
    }

    public final class OwnerSideBar {
        private OwnerSideBar(){}

        public List<MyGroupCard> clickToMineGroupsSelector() {
            Element.click(webDriver, MY_GROUPS_LOCATOR);
            final List <WebElement> webElements = Check
                    .checkListElementsNotEmpty(webDriver, GROUPS_CARDS_LOCATORS);
            return new ArrayList<MyGroupCard>(){{
                for (final WebElement webElement: webElements) {
                    add(new MyGroupCard(webElement));
                }
            }};
        }
    }

    public final class SelectGroupsDialogAlert {
        private SelectGroupsDialogAlert() {}

        public List<SelectGroupPageTypeDialogAlert> getGroupPageList() {
            final List <WebElement> webElements = Check
                    .checkListElementsNotEmpty(webDriver, GROUPS_SELECT_LOCATOR);
            return new ArrayList<SelectGroupPageTypeDialogAlert>(){{
                for (WebElement webElement : webElements) {
                    add(new SelectGroupPageTypeDialogAlert(webElement));
                }
            }};
        }
    }

    public final class SelectGroupPageTypeDialogAlert {
        private final WebElement webElement;

        private SelectGroupPageTypeDialogAlert(final WebElement webElement){
            this.webElement = webElement;
        }

        public ModalDialogAlert clickToModalDialogAlert(){
            webElement.click();
            return new ModalDialogAlert();
        }
    }

    public final class ModalDialogAlert {
        private ModalDialogAlert() {}

        public void inputName(final String groupName) {
            Element.sendKeys(webDriver, NAME_GROUP_LOCATOR, groupName);
        }

        public void inputDescription(final String description) {
            Element.sendKeys(webDriver, DESCRIPTION_LOCATOR, description);
        }

        public void selectCategory() {
            Element.click(webDriver, CATEGORY_MENU_AUTO);
        }

        public void selectRestriction() {
            Element.click(webDriver, AGE_18_SELECTOR);
        }

        public GroupPage createGroupPage() {
            Element.click(webDriver, CREATE_GROUP_BUTTON);
            return new GroupPage(webDriver);
        }
    }

    public final class MyGroupCard {

        private final WebElement mainWebElement;

        private MyGroupCard(final WebElement webElement) {
            this.mainWebElement = webElement;
        }

        public GroupPage clickToGroup() {
            mainWebElement.click();
            return new GroupPage(webDriver);
        }
    }
}