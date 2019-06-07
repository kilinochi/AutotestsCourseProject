import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;

//todo - дописать

public final class AssignAdminToGroup {

    private String groupId;
    private WebDriver creatorsWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp() {
        creatorsWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        User creator = UserFactory.getUser(User.Role.CREATOR);
        final GroupsPage creatorGroupsPage = new LoginPage(creatorsWebDriver)
                .clickToUserPage(creator)
                .clickToGroupsSelector();
        groupId = new CreatorPageHandler.Builder(creatorGroupsPage)
                .inputName("Tests")
                .inputDescription("More Tests")
                .build()
                .create()
                .getGroupId();
    }

    @Test
    public void assignAdmin() {
        new LoginPage(usrWebDriver)
                .clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/" + groupId);
        new GroupPage(usrWebDriver)
                .clickToJoinToGroupButton();
        creatorsWebDriver.get("https://ok.ru/group/" + groupId);
        new GroupPage(creatorsWebDriver)
                .clickToGroupSettings()
                .clickToModeratorsTab();

    }

    @After
    public void afterTest() {

    }
}
