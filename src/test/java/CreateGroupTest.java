import dialog_alerts.ModalDialogAlert;
import dialog_alerts.SelectGroupPageTypeDialogAlert;
import dialog_alerts.SelectGroupsDialogAlert;
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
        final SelectGroupsDialogAlert selectCreatorGroupsCard = groupsPage.getSelectGroupDialogAlert();
        final SelectGroupPageTypeDialogAlert selectCreatorGroupPageTypeCard = selectCreatorGroupsCard.getGroupPageList().get(0);
        final ModalDialogAlert
                creatorModalNewHolderCard = selectCreatorGroupPageTypeCard.getModalDialogAlert();
        creatorModalNewHolderCard.inputName(AppConfig.groupPageName);
        creatorModalNewHolderCard.inputDescription("This is a very SecretGroup!!");
        creatorModalNewHolderCard.selectCategory();
        creatorModalNewHolderCard.selectRestriction();
        creatorModalNewHolderCard.getGroupPage();
        final String groupId = creatorWebDriver.getCurrentUrl().split("\\.")[1].split("/")[2];
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
