import cards.MyGroupCard;
import model.User;

import org.junit.After;
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

    @Before
    public void setUp(){
        ownerUserGroup = UserFactory.getUser(User.Role.CREATOR);
        ownerWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void mailingTest() {
        final LoginPage loginPageCreator = new LoginPage(ownerWebDriver);
        final UserPage creatorUserPage = loginPageCreator.clickToUserPage(ownerUserGroup);
        final GroupsPage creatorGroupsPage = creatorUserPage.clickToGroupsSelector();
        final GroupsPage.OwnerSideBar ownerSideBar = creatorGroupsPage.getOwnerSideBar();
        final MyGroupCard myGroupCard = ownerSideBar.clickToMineGroupsSelector().get(0);
        final GroupPage ownerGroup = myGroupCard.clickToGroup();

    }

    @After
    public void endTest(){

    }
}
