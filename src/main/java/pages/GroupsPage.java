package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import selenium_helpers.Check;
import selenium_helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium_helpers.GroupsSubcategory;

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

    public GroupsPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.check();
    }

    public SelectGroupsDialogAlert clickToSelectGroupDialogAlert() {
        Element.click(webDriver, CREATE_GROUP_LOCATOR);
        return new SelectGroupsDialogAlert();
    }

    public OwnerSideBar getOwnerSideBar() {
        Check.checkElementIsDisplayed(webDriver, OWNER_SIDEBAR_LOCATOR);
        return new OwnerSideBar();
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(webDriver, WIDGET_LOCATOR);
    }

    public final class OwnerSideBar {
        private OwnerSideBar(){}

        public List<MyGroupsCard> clickToMineGroupsSelector() {
            Element.click(webDriver, MY_GROUPS_LOCATOR);
            final List <WebElement> webElements = Check
                    .checkListElementsNotEmpty(webDriver, GROUPS_CARDS_LOCATORS);
            return new ArrayList<MyGroupsCard>(){{
                for (final WebElement webElement: webElements) {
                    add(new MyGroupsCard(webElement));
                }
            }};
        }

        public boolean isVisible() {
            return !Check.checkElementIsInvisible(webDriver, OWNER_SIDEBAR_LOCATOR);
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
            if(groupName != null) {
                Element.sendKeys(webDriver, NAME_GROUP_LOCATOR, groupName);
            }
        }

        public void inputDescription(final String description) {
            if(description != null) {
                Element.sendKeys(webDriver, DESCRIPTION_LOCATOR, description);
            }
        }

        public void selectSubcategory(final GroupsSubcategory subcategory) {
            if(subcategory != null) {
                switch (subcategory) {
                    case AUTO:
                        Element.click(webDriver, CATEGORY_MENU_AUTO);
                        break;
                    default:
                        throw new IllegalArgumentException("BAD ARGUMENT!");
                }
            }
        }

        public void selectRestriction(boolean isRestriction) {
            if(isRestriction) {
                Element.click(webDriver, AGE_18_SELECTOR);
            }
        }

        public GroupPage clickToCreateGroupButton() {
            Element.click(webDriver, CREATE_GROUP_BUTTON);
            return new GroupPage(webDriver);
        }
    }

    public final class MyGroupsCard {

        private final WebElement mainWebElement;

        private MyGroupsCard(final WebElement webElement) {
            this.mainWebElement = webElement;
        }

        public GroupPage clickToGroup() {
            mainWebElement.click();
            return new GroupPage(webDriver);
        }
    }
}