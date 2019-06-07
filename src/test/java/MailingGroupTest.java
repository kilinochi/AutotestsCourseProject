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

import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;
import selenium_helpers.GroupsSubcategory;

public final class MailingGroupTest {

    private User ownerUserGroup;
    private WebDriver ownersWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp() {
        ownerUserGroup = UserFactory.getUser(User.Role.CREATOR);
        ownersWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        final GroupsPage creatorGroupsPage =
                new LoginPage(ownersWebDriver)
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
        ownersWebDriver.get("https://ok.ru/groups");
        new GroupsPage(ownersWebDriver)
                .getOwnerSideBar()
                .clickToMineGroupsSelector().get(0)
                .clickToGroup()
                .clickToInviteButton()
                .selectAllFriendsForInvite()
                .clickToInviteButton();
        final List<String> usersWhoSendInvites = new LoginPage(usrWebDriver)
                .clickToUserPage(usr)
                .clickToNotificationDialogAlert()
                .getUserListFromNotifications();
        final String userName = Iterables.tryFind(usersWhoSendInvites, new Predicate<String>() {
            @Override
            public boolean apply(@NullableDecl String s) {
                return ownerUserGroup.getUserName().equals(s);
            }
        }).or("Not found User!");
        Assert.assertEquals(ownerUserGroup.getUserName(), userName);
    }

    @After
    public void afterTest() {
        ownersWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(ownersWebDriver))
                .build()
                .deleteAllGroups();
        ownersWebDriver.close();
        usrWebDriver.close();
    }
}
