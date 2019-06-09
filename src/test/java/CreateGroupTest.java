import config.AppConfig;
import org.junit.Assert;
import selenium_helpers.Check;
import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.group_page.GroupPage;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;
import selenium_helpers.GroupsSubcategory;

public final class CreateGroupTest extends BaseTest {

    private User creatorGroupUser;
    private WebDriver creatorsWebDriver;
    private User usr;
    private WebDriver usrWebDriver;

    @Before
    public void setUp() {
        creatorGroupUser = UserFactory.getUser(User.Role.CREATOR);
        creatorsWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        usr = UserFactory.getUser(User.Role.USER);
        usrWebDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
    }

    @Test
    public void createAndCheckRestrictionGroup() {
        final GroupsPage creatorsGroupsPage = new LoginPage(creatorsWebDriver)
                .clickToUserPage(creatorGroupUser)
                .clickToGroupsSelector();
        final GroupPage newPage = new CreatorPageHandler.Builder(creatorsGroupsPage)
                .inputName(AppConfig.GROUP_PAGE_NAME)
                .inputDescription("This is a very cool Group!")
                .category(GroupsSubcategory.AUTO)
                .isRestriction(true)
                .build()
                .create();
        final String groupId = newPage.getGroupId();
        new LoginPage(usrWebDriver).clickToUserPage(usr);
        usrWebDriver.get("https://ok.ru/group/" + groupId);
        Assert.assertTrue(usrWebDriver.findElement(AppConfig.RESTRICTION_LOCATOR).isDisplayed());
    }

    @After
    public void afterTest() {
        creatorsWebDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(creatorsWebDriver))
                .build()
                .deleteAllGroups();
        creatorsWebDriver.close();
        usrWebDriver.close();
    }
}
