package cards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectGroupPageTypeCard {

    private final WebElement webElement;
    private final WebDriver webDriver;

    public SelectGroupPageTypeCard(WebElement webElement, WebDriver webDriver){
        this.webElement = webElement;
        this.webDriver = webDriver;
    }

    public ModalNewHolderCard clickToSelectGroupPageType(){
        webElement.click();
        return new ModalNewHolderCard(webDriver);
    }
}
