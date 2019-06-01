package cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.GroupPage;
import selenium_helpers.Element;

public class ModalDialogAlert {

    private static final By NAME_GROUP_LOCATOR = By.name("st.layer.name");
    private static final By DESCRIPTION_LOCATOR = By.id("field_description");
    // private static final By CATEGORY_MENU = By.("st.layer.pageMixedCategory");
    //  private static final By AGE_RESTRICTION = By.name("st.layer.ageRestriction");
    private static final By CATEGORY_MENU_AUTO = By.xpath(".//*[@value='subcatVal12001']");
    private static final By AGE_18_SELECTOR = By.xpath(".//*[@value='18']");
    private static final By CREATE_GROUP_BUTTON = By.name("button_create");

    private final WebDriver webDriver;

    ModalDialogAlert(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

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

    public GroupPage getGroupPage() {
        Element.click(webDriver, CREATE_GROUP_BUTTON);
        return new GroupPage(webDriver);
    }
}