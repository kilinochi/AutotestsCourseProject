package pages;

import model.Element;
import model.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By LOGIN_LOCATOR = By.name("st.email");
    private static final By PASSWORD_LOCATOR = By.name("st.password");
    static final By SUBMIT_LOCATOR = By.xpath(".//input[@data-l='t,sign_in']");
    private final WebDriver driver;

    public LoginPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        check();
    }

    public UserPage get(final User user) {
        Element.sendKeys(webDriver.findElement(LOGIN_LOCATOR),user.getLogin());
        Element.sendKeys(webDriver.findElement(PASSWORD_LOCATOR),user.getPassword());
        Element.click(driver.findElement(SUBMIT_LOCATOR));
        return new UserPage(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue(driver.findElement(SUBMIT_LOCATOR).isDisplayed());
    }
}
