package cards;

import model.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectGroupPageTypeCard {

    private final WebElement webElement;
    private final WebDriver webDriver;

    public SelectGroupPageTypeCard(final WebElement webElement, final WebDriver webDriver){
        this.webElement = webElement;
        this.webDriver = webDriver;
    }

    public ModalNewHolderCard getSelectGroupPageType(){
        Element.click(webElement);
        return new ModalNewHolderCard(webDriver);
    }
}
