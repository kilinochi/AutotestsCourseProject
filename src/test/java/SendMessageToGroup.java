import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.group_page.settings.GroupSettingsPage;
import pages.group_page.settings.tabs.SettingsTab;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;

public class SendMessageToGroup {

    private String groupId;
    private WebDriver creatorWebDriver;
    private User usr;

    @Before
    public void setUp(){
        User creatorGroupUser = UserFactory.getUser(User.Role.CREATOR);
        creatorWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        final GroupsPage groupsPage = new LoginPage(creatorWebDriver)
                .clickToUserPage(creatorGroupUser)
                .clickToGroupsSelector();
        groupId = new CreatorPageHandler.Builder(groupsPage)
                .inputName("Test")
                .inputDescription("more test")
                .build()
                .create()
                .clickToGroupSettings()
                .clickToMsgSettingsTab()
                .selectCategoryWhoCanSendMessage(SettingsTab.Category.EVERYBODY)
                .clickToSaveButton()
                .clickToGroupProfile()
                .getGroupId();
    }

    @Test
    public void sendMessageToGroup() {
        final WebDriver usrWebDriver
                = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        new LoginPage(usrWebDriver).clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/"+groupId);
        GroupPage groupPage = new GroupPage(usrWebDriver);
    }

    @After
    public void afterTest(){

    }
}
