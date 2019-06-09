import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.group_page.cards.MemberCard;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;
import java.util.List;


public final class CheckMemberInGroup {

    private String groupId;
    private WebDriver creatorsWebDriver;
    private User usr;
    private WebDriver usrWebDriver;
    private GroupPage newGroup;

    @Before
    public void setUp() {
        creatorsWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        final User creator = UserFactory.getUser(User.Role.CREATOR);
        final GroupsPage creatorGroupsPage = new LoginPage(creatorsWebDriver)
                .clickToUserPage(creator)
                .clickToGroupsSelector();
        newGroup = new CreatorPageHandler.Builder(creatorGroupsPage)
                .inputName("Tests")
                .inputDescription("More Tests")
                .build()
                .create();
        groupId = newGroup.getGroupId();
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void checkMember() {
        new LoginPage(usrWebDriver)
                .clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/" + groupId);
        new GroupPage(usrWebDriver)
                .clickToJoinToGroupButton();
        creatorsWebDriver.get("https://ok.ru/group/" + groupId);
        final List <MemberCard> members = newGroup
                .clickToGroupSettings()
                .clickToModeratorsTab()
                .clickToAddModeratorsButton()
                .searchMembersByName(usr.getUserName());
        Assert.assertTrue(checkMemberInGroup(members, usr.getUserName()));
    }

    private static boolean checkMemberInGroup(final List <MemberCard> members, final String name) {
        for (MemberCard member : members) {
            if(member.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @After
    public void afterTest() {
        creatorsWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(creatorsWebDriver))
                .build()
                .deleteAllGroups();
        creatorsWebDriver.close();
        usrWebDriver.close();
    }
}
