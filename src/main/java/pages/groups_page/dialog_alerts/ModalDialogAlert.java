package pages.groups_page.dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import selenium_helpers.Element;
import selenium_helpers.GroupsSubcategory;

public final class ModalDialogAlert {

    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");
    private static final By NAME_GROUP_LOCATOR = By.name("st.layer.name");
    private static final By DESCRIPTION_LOCATOR = By.id("field_description");
    private static final By CATEGORY_MENU_AUTO = By.xpath(".//*[@value='subcatVal12001']");
    private static final By AGE_18_SELECTOR = By.xpath(".//*[@value='18']");
    private static final By CREATE_GROUP_BUTTON = By.name("button_create");

    private final WebDriver webDriver;

    public ModalDialogAlert(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ModalDialogAlert inputName(final String groupName) {
        if (groupName != null) {
            Element.sendKeys(webDriver, NAME_GROUP_LOCATOR, groupName);
        }
        return this;
    }

    public ModalDialogAlert inputDescription(final String description) {
        if (description != null) {
            Element.sendKeys(webDriver, DESCRIPTION_LOCATOR, description);
        }
        return this;
    }

    public ModalDialogAlert selectSubcategory(final GroupsSubcategory subcategory) {
        if (subcategory != null) {
            switch (subcategory) {
                case AUTO:
                    Element.click(webDriver, CATEGORY_MENU_AUTO);
                    break;
                default:
                    throw new IllegalArgumentException("BAD ARGUMENT!");
            }
        }
        return this;
    }

    public ModalDialogAlert selectRestriction(boolean isRestriction) {
        if (isRestriction) {
            Element.click(webDriver, AGE_18_SELECTOR);
        }
        return this;
    }

    public GroupPage clickToCreateGroupButton() {
        Element.click(webDriver, CREATE_GROUP_BUTTON);
        return new GroupPage(webDriver);
    }
}