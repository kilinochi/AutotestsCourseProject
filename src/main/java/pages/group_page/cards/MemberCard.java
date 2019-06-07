package pages.group_page.cards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class MemberCard {

    private final String userName;

    public MemberCard(final WebElement webElement) {
        userName = webElement
                .findElement(By.xpath("//*[@class='user-grid-card']/div[2]/div[1]/a"))
                .getAttribute("innerHTML");
    }

    public String getUserName() {
        return userName;
    }

    public void assignAdmin() {

    }
}
