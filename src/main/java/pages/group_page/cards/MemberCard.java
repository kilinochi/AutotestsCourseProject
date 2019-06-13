package pages.group_page.cards;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public final class MemberCard {

    private static final By NAME_LOCATOR = By.xpath("//*[@class='user-grid-card']/div[2]/div[1]/a");

    private String userName;

    public MemberCard(final WebElement webElement) {
        try {
            userName = webElement
                    .findElement(NAME_LOCATOR)
                    .getAttribute("innerHTML");
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void assignAdmin() {

    }
}
