package dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.Wrapper;

import java.util.List;

public class SelectGroupsDialogAlert {

    private final WebDriver webDriver;
    private static final By GROUPS_SELECT_LOCATOR = By.className("create-group-dialog_i");

    public SelectGroupsDialogAlert(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public List<SelectGroupPageTypeDialogAlert> getGroupPageList() {
        return Wrapper.getGroupPage(webDriver.findElements(GROUPS_SELECT_LOCATOR), webDriver);
    }
}