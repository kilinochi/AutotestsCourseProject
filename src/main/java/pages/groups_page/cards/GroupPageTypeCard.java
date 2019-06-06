package pages.groups_page.cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.groups_page.dialog_alerts.ModalDialogAlert;

public final class GroupPageTypeCard {
    private final WebElement webElement;
    private final WebDriver webDriver;

    public GroupPageTypeCard(final WebElement webElement, final WebDriver webDriver){
        this.webElement = webElement;
        this.webDriver = webDriver;
    }

    public ModalDialogAlert clickToModalDialogAlert(){
        webElement.click();
        return new ModalDialogAlert(webDriver);
    }
}
