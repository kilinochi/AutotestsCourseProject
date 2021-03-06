package pages.login_page;

import pages.BasePage;
import pages.user_page.UserPage;
import selenium_helpers.Check;
import selenium_helpers.Element;
import model.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BasePage {

    private static final By LOGIN_LOCATOR = By.name("st.email");
    private static final By PASSWORD_LOCATOR = By.name("st.password");
    private static final By SUBMIT_LOCATOR = By.xpath(".//input[@data-l='t,sign_in']");

    private final WebDriver driver;

    public LoginPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.check();
    }

    public UserPage clickToUserPage(final User user) {
        Element.sendKeys(webDriver, LOGIN_LOCATOR, user.getLogin());
        Element.sendKeys(webDriver, PASSWORD_LOCATOR, user.getPassword());
        Element.click(driver, SUBMIT_LOCATOR);
        return new UserPage(driver);
    }

    @Override
    protected void check() {
        Check.checkElementIsDisplayed(driver, SUBMIT_LOCATOR);
    }
}
