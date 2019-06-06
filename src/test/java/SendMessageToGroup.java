import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import model.User;
import net.bytebuddy.utility.RandomString;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.group_page.settings.tabs.SettingsTab;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;
import pages.user_page.UserPage;

public class SendMessageToGroup {

    private static final String message = RandomString.make(5);
    private String groupId;
    private WebDriver creatorWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

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
       usrWebDriver
                = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        usr = UserFactory.getUser(User.Role.USER);
        new LoginPage(usrWebDriver).clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/"+groupId);
        new GroupPage(usrWebDriver)
            .clickToSendMessageButton()
            .sendMessage(message)
            .clickToSendMessageButton();
        creatorWebDriver.get("https://www.ok.ru/");
        final List<String> usersWhoSendMessagesToGroup = new UserPage(creatorWebDriver)
            .clickToMessagesDialogAlert()
            .clickToGroupsTab()
            .getUserListFromNotifications();
        final String userName = Iterables.tryFind(usersWhoSendMessagesToGroup, new Predicate<String>() {
            @Override
            public boolean apply(@NullableDecl String s) {
                return usr.getUserName().equals(s);
            }
        }).or("Not found User!");
        Assert.assertEquals(usr.getUserName(), userName);
    }

    @After
    public void afterTest(){
        usrWebDriver.close();
        creatorWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(creatorWebDriver))
                .build()
                .deleteAllGroups();
        creatorWebDriver.close();
    }
}
