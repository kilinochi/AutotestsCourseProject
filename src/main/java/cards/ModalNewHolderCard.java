package cards;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
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

    public ModalNewHolderCard(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void inputName(String groupName) {
        WebElement inputName = webDriver.findElement(NAME_GROUP_LOCATOR);
        inputName.sendKeys(groupName);
    }

    public void inputDescription(String description) {
        WebElement inputDescription = webDriver.findElement(DESCRIPTION_LOCATOR);
        inputDescription.sendKeys(description);
    }

    public void selectCategory() {
        WebElement selectCategory = webDriver.findElement(CATEGORY_MENU_AUTO);
        selectCategory.click();
    }
    public void selectRestriction() {
        WebElement selectRestriction = webDriver.findElement(AGE_18_SELECTOR);
        selectRestriction.click();
    }

    public GroupPage getGroupPage() {
        WebElement createGroupBtn = webDriver.findElement(CREATE_GROUP_BUTTON);
        createGroupBtn.click();
        return new GroupPage(webDriver);
    }
}

