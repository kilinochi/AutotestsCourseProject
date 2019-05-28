import cards.ModalNewHolderCard;
import cards.SelectGroupPageTypeCard;
import cards.SelectGroupsCard;
import config.AppConfig;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.GroupsPage;
import pages.LoginPage;
import pages.UserPage;


public class GroupsPageTest extends BaseTest{

    private User creatorGroupUser;
    private User usr;
    private WebDriver usrWebDriver;
    private WebDriver creatorWebDriver;


    @Before
    public void setUp()  {
        creatorWebDriver = WebDriversFactory.getDriver();
        creatorGroupUser = UserFactory.getUser(User.Role.CREATOR);
    }

    @Test
    public void createAndCheckGroup() {
        LoginPage loginPageCreator = new LoginPage(creatorWebDriver);
        UserPage creatorUserPage = loginPageCreator.get(creatorGroupUser);
        GroupsPage groupsPage = creatorUserPage.getGroupsPage();
        SelectGroupsCard selectCreatorGroupsCard = groupsPage.getSelectGroupCard();
        SelectGroupPageTypeCard selectCreatorGroupPageTypeCard = selectCreatorGroupsCard.getGroupPageList().get(0);
        ModalNewHolderCard creatorModalNewHolderCard = selectCreatorGroupPageTypeCard.clickToSelectGroupPageType();
        creatorModalNewHolderCard.inputName(AppConfig.groupPageName);
        creatorModalNewHolderCard.inputDescription("This is a very SecretGroup!!");
        creatorModalNewHolderCard.selectCategory();
        creatorModalNewHolderCard.selectRestriction();
        String groupUrl = creatorModalNewHolderCard.getGroupPage().getUrlGroup();
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver();
        LoginPage usrLoginPage = new LoginPage(usrWebDriver);
        UserPage usrPage = usrLoginPage.get(usr);
        usrWebDriver.get(groupUrl);
    }


    @After
    public void afterTest() {
        creatorWebDriver.close();
        usrWebDriver.close();
    }
}
