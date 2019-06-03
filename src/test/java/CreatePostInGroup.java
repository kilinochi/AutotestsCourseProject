import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CreatePostInGroup {

    private WebDriver webDriver;
    private User user;

    @Before
    public void setUp(){
        user = UserFactory.getUser(User.Role.CREATOR);
        webDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);

    }

    @Test
    public void createPost() {

    }

    @After
    public void endTest(){
        webDriver.close();
    }
}
