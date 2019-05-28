import config.AppConfig;
import model.User;

class UserFactory {
    private UserFactory(){}

    static User getUser(User.Role role){
        User user = null;
        switch (role){
            case USER:
                user = new User(AppConfig.loginUser, AppConfig.password);
                break;
            case CREATOR:
                user = new User(AppConfig.loginCreator, AppConfig.password);
                break;
        }
        return user;
    }
}
