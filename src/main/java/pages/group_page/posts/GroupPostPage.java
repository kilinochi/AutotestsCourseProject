package pages.group_page.posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public final class GroupPostPage extends BasePage {

    private static final By POST_LOCATORS = By.xpath("//*[@class='groups_post-w __search-enabled']");

    private final WebDriver webDriver;

    public GroupPostPage(final WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public List <WebElement> getAllPosts () {
        return webDriver.findElements(POST_LOCATORS);
    }

    @Override
    protected void check() {

    }
}
