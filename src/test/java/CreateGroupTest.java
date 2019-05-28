import cards.ModalNewHolderCard;
import cards.SelectGroupPageTypeCard;
import cards.SelectGroupsCard;
import config.AppConfig;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
        creatorWebDriver = WebDriversFactory.getDriver();
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver();
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
        GroupPage newGroup = creatorModalNewHolderCard.getGroupPage();
        String groupUrl = newGroup.getIdGroup();
        LoginPage usrLoginPage = new LoginPage(usrWebDriver);
        UserPage usrPage = usrLoginPage.get(usr);
        usrWebDriver.get("https://ok.ru/group/"+groupUrl);
        Assert.assertTrue(usrWebDriver.findElement(By.xpath("//*[@class ='stub-empty __18plus']")).isDisplayed());
        usrWebDriver.close();
    }

    @After
    public void endTest() {
        creatorWebDriver.close();
        usrWebDriver.close();
    }
}
