import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.group_page.GroupPage;
import pages.group_page.posts.GroupPostPage;
import pages.groups_page.GroupsPage;
import pages.login_page.LoginPage;

public final class CreatePostInGroup {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        final User user = UserFactory.getUser(User.Role.CREATOR);
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
        final GroupPage group = new GroupsPage(webDriver)
                .getOwnerSideBar()
                .clickToMineGroupsSelector().get(0)
                .clickToGroup();
        final int olderCountPosts = group
                .getAllPosts()
                .size();
        final GroupPostPage groupPostPage
                = new CreatorPostHandler.Builder(group)
                .inputText("Rise Against!")
                .searchAndAttachMusic("Rise Against - Satellite")
                .fromRange(1, 1)
                .build()
                .createMusicPost();
        final int nowCountPosts = groupPostPage.getAllPosts().size();
        Assert.assertEquals(olderCountPosts + 1, nowCountPosts);
    }

    @After
    public void afterTest() {
        webDriver.get("https://ok.ru/groups");
        new CreatorPageHandler.Builder(new GroupsPage(webDriver))
                .build()
                .deleteAllGroups();
        webDriver.close();
    }
}
