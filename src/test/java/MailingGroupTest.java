import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import model.User;

import java.util.List;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.group_page.GroupPage;
import pages.group_page.dialog_alerts.InviteDialogAlert;
import pages.groups_page.GroupsPage;
import pages.groups_page.cards.MyGroupsCard;
import pages.groups_page.side_bars.OwnerSideBar;
import pages.login_page.LoginPage;
import pages.user_page.UserPage;
import pages.user_page.dialog_alerts.NotificationDialogAlert;
import selenium_helpers.GroupsSubcategory;

public class MailingGroupTest {

    private User ownerUserGroup;
    private WebDriver ownerWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp(){
        ownerUserGroup = UserFactory.getUser(User.Role.CREATOR);
        ownerWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        final GroupsPage creatorGroupsPage =
                new LoginPage(ownerWebDriver)
                .clickToUserPage(ownerUserGroup)
                .clickToGroupsSelector();
        new CreatorPageHandler.Builder(creatorGroupsPage)
                .inputName("Riksha")
                .inputDescription("MoreRiksha")
                .category(GroupsSubcategory.AUTO)
                .isRestriction(false)
                .build()
                .create();
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void mailingTest() {
        ownerWebDriver.get("https://ok.ru/groups");
        new GroupsPage(ownerWebDriver)
                .getOwnerSideBar()
                .clickToMineGroupsSelector().get(0)
                .clickToGroup()
                .clickToInviteButton()
                .selectAllFriendsForInvite()
                .clickToInviteButton();
        final List <String> usersWhoSendInvites = new LoginPage(usrWebDriver)
                .clickToUserPage(usr)
                .clickToNotificationDialogAlert()
                .getUserNamesWhoSendInvitations();
        final String userName = Iterables.tryFind(usersWhoSendInvites, new Predicate<String>() {
            @Override
            public boolean apply(@NullableDecl String s) {
                return ownerUserGroup.getUserName().equals(s);
            }
        }).or("Not found User!");
        Assert.assertEquals(ownerUserGroup.getUserName(), userName);
    }

    @After
    public void afterTest(){
        ownerWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(ownerWebDriver))
                .build()
                .deleteAllGroups();
        ownerWebDriver.close();
        usrWebDriver.close();
    }
}
