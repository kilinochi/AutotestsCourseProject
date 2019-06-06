import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GroupPage;
import pages.GroupsPage;
import pages.LoginPage;
import java.util.List;

public class CreatePostInGroup {

    private WebDriver webDriver;

    @Before
    public void setUp(){
        User user = UserFactory.getUser(User.Role.CREATOR);
        webDriver = WebDriversFactory.getDriver(Drivers.ChromeDriver);
        new LoginPage(webDriver)
                .clickToUserPage(user)
                .clickToGroupsSelector();
        new CreatorPageHandler.Builder(new GroupsPage(webDriver))
                .inputName("Test")
                .inputDescription("More Test")
                .build()
                .create();
    }

    @Test
    public void createMusicPost() {
        webDriver.get("https://ok.ru/groups");
        final GroupsPage groupsPage = new GroupsPage(webDriver);
        final GroupsPage.OwnerSideBar userGroups = groupsPage.getOwnerSideBar();
        final GroupsPage.MyGroupsCard groupsCard = userGroups.clickToMineGroupsSelector().get(0);
        final GroupPage group = groupsCard.clickToGroup();
        int olderCountPosts = group.getAllPosts().size();
        GroupPage.GroupTopicsTab groupTopicsTab = new CreatorPostHandler.Builder(group)
                .inputText("Rise Against!")
                .searchAndAttachMusic("Rise Against - Satellite")
                .fromRange(1,1)
                .build()
                .createMusicPost();
        int nowCountPosts = groupTopicsTab.getAllPosts().size();
        Assert.assertEquals(olderCountPosts + 1,  nowCountPosts);
    }

    @After
    public void afterTest(){
        webDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(webDriver))
                .build()
                .deleteAllGroups();
        webDriver.close();
    }
}
