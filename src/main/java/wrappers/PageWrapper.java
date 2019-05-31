package wrappers;

import cards.SelectGroupPageTypeCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PageWrapper {
    public static List<SelectGroupPageTypeCard> getGroupPage(final List<WebElement> webElements, final WebDriver webDriver) {
        return new ArrayList<SelectGroupPageTypeCard>(){{
            for (final WebElement webElement: webElements) {
                add(new SelectGroupPageTypeCard(webElement, webDriver));
            }
        }};
    }
}