package cards;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GroupPage;

public class MyGroupCard {

    private final WebDriver webDriver;
    private final WebElement mainWebElement;

    public MyGroupCard(final WebDriver webDriver, final WebElement webElement) {
        this.webDriver = webDriver;
        this.mainWebElement = webElement;
    }

    public GroupPage clickToGroup() {
        mainWebElement.click();
        return new GroupPage(webDriver);
    }
}
