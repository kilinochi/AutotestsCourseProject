import cards.MyGroupCard;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import dialog_alerts.InviteDialogAlert;
import dialog_alerts.NotificationDialogAlert;
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

public class MailingGroupTest {

    private User ownerUserGroup;
    private WebDriver ownerWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp(){
        ownerUserGroup = UserFactory.getUser(User.Role.CREATOR);
        ownerWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void mailingTest() {
        final LoginPage loginPageCreator = new LoginPage(ownerWebDriver);
        final UserPage creatorUserPage = loginPageCreator.clickToUserPage(ownerUserGroup);
        final GroupsPage creatorGroupsPage = creatorUserPage.clickToGroupsSelector();
        final GroupsPage.OwnerSideBar ownerSideBar = creatorGroupsPage.getOwnerSideBar();
        final MyGroupCard myGroupCard = ownerSideBar.clickToMineGroupsSelector().get(1);
        final GroupPage ownerGroup = myGroupCard.clickToGroup();
        final InviteDialogAlert inviteDialogAlert = ownerGroup.clickToInviteButton();
        inviteDialogAlert.selectAllFriendsForInvite();
        inviteDialogAlert.clickToInviteButton();
        final LoginPage usrLoginPage = new LoginPage(usrWebDriver);
        final UserPage usrUserPage = usrLoginPage.clickToUserPage(usr);
        final NotificationDialogAlert usrNotifications = usrUserPage.clickToNotificationDialogAlert();
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
    public void endTest(){
        ownerWebDriver.close();
        usrWebDriver.close();
    }
}
