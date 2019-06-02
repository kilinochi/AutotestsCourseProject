import config.AppConfig;
import model.User;

class UserFactory {
    private UserFactory(){}

    static User getUser(final User.Role role){
        User user = null;
        switch (role){
            case USER:
                user = new User(AppConfig.LOGIN_USER, AppConfig.PASSWORD, AppConfig.USER_ID, AppConfig.USER_USERNAME);
                break;
            case CREATOR:
                user = new User(AppConfig.LOGIN_CREATOR, AppConfig.PASSWORD, AppConfig.CREATOR_ID, AppConfig.CREATOR_NAME);
                break;
        }
        return user;
    }
}
