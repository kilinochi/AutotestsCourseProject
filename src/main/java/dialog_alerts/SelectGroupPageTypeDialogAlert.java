package dialog_alerts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectGroupPageTypeDialogAlert {

    private final WebElement webElement;
    private final WebDriver webDriver;

    public SelectGroupPageTypeDialogAlert(final WebElement webElement, final WebDriver webDriver){
        this.webElement = webElement;
        this.webDriver = webDriver;
    }

    public ModalDialogAlert getModalDialogAlert(){
        webElement.click();
        return new ModalDialogAlert(webDriver);
    }
}
