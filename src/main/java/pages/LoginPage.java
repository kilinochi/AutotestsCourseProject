package pages;

import model.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By LOGIN_LOCATOR = By.name("st.email");
    private static final By PASSWORD_LOCATOR = By.name("st.password");
    private static final By SUBMIT_LOCATOR = By.xpath(".//input[@data-l='t,sign_in']");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        check();
    }

    public UserPage get(User user) {
        driver.findElement(LOGIN_LOCATOR).sendKeys(user.getLogin());
        driver.findElement(PASSWORD_LOCATOR).sendKeys(user.getPassword());
        driver.findElement(SUBMIT_LOCATOR).click();
        return new UserPage(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue(driver.findElement(By.xpath(".//input[@data-l='t,sign_in']")).isDisplayed());
    }
}
