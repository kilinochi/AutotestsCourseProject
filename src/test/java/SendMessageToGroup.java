import java.util.List;

import model.User;
import net.bytebuddy.utility.RandomString;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.group_page.settings.tabs.Tab;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;
import pages.user_page.UserPage;

public final class SendMessageToGroup {

    private static final String message = RandomString.make(5);
    private String groupId;
    private WebDriver creatorWebDriver;
    private WebDriver usrWebDriver;

    @Before
    public void setUp() {
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
                .selectCategoryWhoCanSendMessage(Tab.Category.EVERYBODY)
                .clickToSaveButton()
                .clickToGroupProfile()
                .getGroupId();
    }

    @Test
    public void sendMessageToGroup() {
        usrWebDriver
                = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        final User usr = UserFactory.getUser(User.Role.USER);
        new LoginPage(usrWebDriver).clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/" + groupId);
        new GroupPage(usrWebDriver)
                .clickToSendMessageButton()
                .sendMessage(message)
                .clickToSendMessageButton();
        creatorWebDriver.get("https://www.ok.ru/");
        final List<String> usersWhoSendMessagesToGroup = new UserPage(creatorWebDriver)
                .clickToMessagesDialogAlert()
                .clickToGroupsTab()
                .getUserListFromNotifications();
        Assert.assertTrue(usersWhoSendMessagesToGroup.contains(usr.getUserName()));
    }

    @After
    public void afterTest() {
        usrWebDriver.close();
        creatorWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(creatorWebDriver))
                .build()
                .deleteAllGroups();
        creatorWebDriver.close();
    }
}