import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.GroupPage;
import pages.GroupsPage;
import pages.LoginPage;
import pages.UserPage;

public class CreatePostInGroup {

    private WebDriver webDriver;
    private User user;

    @Before
    public void setUp(){
        user = UserFactory.getUser(User.Role.CREATOR);
        webDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void createMusicPost() {
        final LoginPage loginPage = new LoginPage(webDriver);
        final UserPage userPage = loginPage.clickToUserPage(user);
        final GroupsPage groupsPage = userPage.clickToGroupsSelector();
        final GroupsPage.OwnerSideBar userGroups = groupsPage.getOwnerSideBar();
        final GroupsPage.MyGroupsCard groupsCard = userGroups.clickToMineGroupsSelector().get(0);
        final GroupPage group = groupsCard.clickToGroup();
        final GroupPage.CreatePostDialogAlert postDialogAlert = group.clickToCreatePostArea();
        postDialogAlert.inputText("Rise Against!");
        postDialogAlert.searchAndAttachMusic("Rise Against - Satellite", 1, 1);
        postDialogAlert.clickToCreatePostButton();
    }

    @After
    public void afterTest(){
        webDriver.close();
    }
}
