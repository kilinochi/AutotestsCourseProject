import config.AppConfig;
import selenium_helpers.Check;
import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.GroupPage;
import pages.GroupsPage;
import pages.LoginPage;
import pages.UserPage;


public class CreateGroupTest extends BaseTest{

    private User creatorGroupUser;
    private WebDriver creatorWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp()  {
        creatorGroupUser = UserFactory.getUser(User.Role.CREATOR);
        creatorWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void createAndCheckRestrictionGroup() {
        final LoginPage loginPageCreator = new LoginPage(creatorWebDriver);
        final UserPage creatorUserPage = loginPageCreator.clickToUserPage(creatorGroupUser);
        final GroupsPage groupsPage = creatorUserPage.clickToGroupsSelector();
        final GroupsPage.SelectGroupsDialogAlert selectCreatorGroupsCard
                = groupsPage.clickToSelectGroupDialogAlert();
        final GroupsPage.SelectGroupPageTypeDialogAlert selectCreatorGroupPageTypeCard
                = selectCreatorGroupsCard.getGroupPageList().get(0);
        final GroupsPage.ModalDialogAlert modalDialogAlert
                = selectCreatorGroupPageTypeCard.clickToModalDialogAlert();
        modalDialogAlert.inputName(AppConfig.GROUP_PAGE_NAME);
        modalDialogAlert.inputDescription("This is a very SecretGroup!!");
        modalDialogAlert.selectCategory();
        modalDialogAlert.selectRestriction();
        final GroupPage newPage = modalDialogAlert.createGroupPage();
        final String groupId = newPage.getGroupId();
        new LoginPage(usrWebDriver).clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/"+groupId);
        Check.checkElementVisible(usrWebDriver, GroupPage.RESTRICTION_LOCATOR);
    }

    @After
    public void endTest() {
        creatorWebDriver.close();
        usrWebDriver.close();
    }
}
