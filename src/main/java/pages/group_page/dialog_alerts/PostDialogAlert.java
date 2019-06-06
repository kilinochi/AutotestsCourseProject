package pages.group_page.dialog_alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.group_page.posts.GroupPostPage;
import selenium_helpers.Check;
import selenium_helpers.Element;

public class PostDialogAlert {

    private static final By MUSICS_LOCATORS = By.xpath("//*[@data-action='track']/div[2]/div[1]/div[1]");
    private static final By INPUT_PLACEHOLDER_LOCATOR = By.xpath("//*[@class='posting_itx emoji-tx h-mod js-ok-e js-posting-itx ok-posting-handler']");
    private static final By ATTACH_MUSIC_BUTTON = By.xpath("//*[@data-module='postingForm/mediaMusicAddButton']");
    private static final By INPUT_SEARCH_MUSIC_LOCATOR = By.xpath("//*[@data-id='searchInput']");
    private static final By SEND_MUSIC_BUTTON = By.xpath("//*[@class='modal-new_actions __center']/div[1]/a[1]");
    private static final By CREATE_POST_BUTTON_LOCATOR = By.xpath("//*[@class='posting_f_ac']/div[2]");

    private final WebDriver webDriver;

    public PostDialogAlert(final WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public PostDialogAlert inputText(final String text) {
        if(text != null) {
            Element.sendKeys(webDriver, INPUT_PLACEHOLDER_LOCATOR, text);
        }
        return this;
    }

    public PostDialogAlert attachMusic(int from, int count) {
        if(from >= 0 && count >= 0) {
            Element.click(webDriver, ATTACH_MUSIC_BUTTON);
            Check.checkListElementsNotEmpty(webDriver, MUSICS_LOCATORS)
                    .stream()
                    .skip(from)
                    .limit(count - from + 1)
                    .forEach(WebElement::click);
            Element.click(webDriver, SEND_MUSIC_BUTTON);
        }
        return this;
    }

    public PostDialogAlert searchAndAttachMusic(final String music, int from, int count) {
        if(music != null && from >= 0 && count >= 0) {
            Element.click(webDriver, ATTACH_MUSIC_BUTTON);
            Element.sendKeys(webDriver, INPUT_SEARCH_MUSIC_LOCATOR, music);
            Check.checkListElementsNotEmpty(webDriver, MUSICS_LOCATORS)
                    .stream()
                    .skip(from)
                    .limit(count - from + 1)
                    .forEach(WebElement::click);
            Element.click(webDriver, SEND_MUSIC_BUTTON);
        }
        return this;
    }

    public GroupPostPage clickToCreatePostButton() {
        Element.click(webDriver, CREATE_POST_BUTTON_LOCATOR);
        return new GroupPostPage(webDriver);
    }
}
