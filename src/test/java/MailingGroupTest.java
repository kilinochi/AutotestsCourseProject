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

import pages.GroupPage;
import pages.GroupsPage;
import pages.LoginPage;
import pages.UserPage;
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
        final LoginPage loginPageCreator = new LoginPage(ownerWebDriver);
        final UserPage ownerUserPage
                = loginPageCreator.clickToUserPage(ownerUserGroup);
        final GroupsPage creatorGroupsPage
                = ownerUserPage.clickToGroupsSelector();
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
        final GroupsPage creatorGroupsPage = new GroupsPage(ownerWebDriver);
        final GroupsPage.OwnerSideBar ownerSideBar
                = creatorGroupsPage.getOwnerSideBar();
        final GroupsPage.MyGroupsCard myGroupsCard
                = ownerSideBar.clickToMineGroupsSelector().get(0);
        final GroupPage ownerGroup
                = myGroupsCard.clickToGroup();
        final GroupPage.InviteDialogAlert inviteDialogAlert
                = ownerGroup.clickToInviteButton();
        inviteDialogAlert.selectAllFriendsForInvite();
        inviteDialogAlert.clickToInviteButton();
        final LoginPage usrLoginPage = new LoginPage(usrWebDriver);
        final UserPage usrUserPage = usrLoginPage.clickToUserPage(usr);
        final UserPage.NotificationDialogAlert usrNotifications = usrUserPage.clickToNotificationDialogAlert();
        final List <String> usersWhoSendInvites = usrNotifications.getUserNamesWhoSendInvitations();
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
