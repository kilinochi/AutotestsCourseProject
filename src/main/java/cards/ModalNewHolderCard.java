package cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GroupPage;

public class ModalNewHolderCard {

    private static final By NAME_GROUP_LOCATOR = By.name("st.layer.name");
    private static final By DESCRIPTION_LOCATOR = By.id("field_description");
    // private static final By CATEGORY_MENU = By.("st.layer.pageMixedCategory");
    //  private static final By AGE_RESTRICTION = By.name("st.layer.ageRestriction");
    private static final By CATEGORY_MENU_AUTO = By.xpath(".//*[@value='subcatVal12001']");
    private static final By AGE_18_SELECTOR = By.xpath(".//*[@value='18']");
    private static final By CREATE_GROUP_BUTTON = By.name("button_create");

    private final WebDriver webDriver;

    ModalNewHolderCard(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void inputName(final String groupName) {
        final WebElement inputName = webDriver.findElement(NAME_GROUP_LOCATOR);
        inputName.sendKeys(groupName);
    }

    public void inputDescription(final String description) {
        webDriver.findElement(DESCRIPTION_LOCATOR).sendKeys(description);
    }

    public void selectCategory() {
        webDriver.findElement(CATEGORY_MENU_AUTO).click();
    }
    public void selectRestriction() {
        webDriver.findElement(AGE_18_SELECTOR).click();
    }

    public GroupPage getGroupPage() {
        webDriver.findElement(CREATE_GROUP_BUTTON).click();
        return new GroupPage(webDriver);
    }
}