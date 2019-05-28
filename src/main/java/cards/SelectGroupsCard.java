package cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.PageWrapper;

import java.util.List;

public class SelectGroupsCard {

    private final WebDriver webDriver;
    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");


    public SelectGroupsCard(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public List<SelectGroupPageTypeCard> getGroupPageList() {
        List <WebElement> webElementsLocators = (List<WebElement>) webDriver.findElements(GROUPS_SELECT_LOCATOR);
        return PageWrapper.getGroupPage(webElementsLocators, webDriver);
    }
}