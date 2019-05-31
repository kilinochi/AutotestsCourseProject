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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void createAndCheckGroup() {
        final LoginPage loginPageCreator = new LoginPage(creatorWebDriver);
        final UserPage creatorUserPage = loginPageCreator.get(creatorGroupUser);
        final GroupsPage groupsPage = creatorUserPage.getGroupsPage();
        final SelectGroupsCard selectCreatorGroupsCard = groupsPage.getSelectGroupCard();
        final SelectGroupPageTypeCard selectCreatorGroupPageTypeCard = selectCreatorGroupsCard.getGroupPageList().get(0);
        final ModalNewHolderCard creatorModalNewHolderCard = selectCreatorGroupPageTypeCard.getSelectGroupPageType();
        creatorModalNewHolderCard.inputName(AppConfig.groupPageName);
        creatorModalNewHolderCard.inputDescription("This is a very SecretGroup!!");
        creatorModalNewHolderCard.selectCategory();
        creatorModalNewHolderCard.selectRestriction();
        creatorModalNewHolderCard.getGroupPage();
        new WebDriverWait(creatorWebDriver, 10).until(ExpectedConditions.);
        String groupId = creatorWebDriver.getCurrentUrl().split("\\.")[1].split("/")[2];
        new LoginPage(usrWebDriver).get(usr);
        usrWebDriver.get("https://ok.ru/group/"+groupId);
        Assert.assertTrue(usrWebDriver.findElement(GroupPage.RESTRICTION_LOCATOR).isDisplayed());
    }

    @After
    public void endTest() {
        creatorWebDriver.close();
        usrWebDriver.close();
    }
}
