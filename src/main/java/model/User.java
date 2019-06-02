package model;

public class User {

    public enum Role {
        CREATOR, USER
    }

    private final String password;
    private final String login;
    private final String userId;
    private final String userName;

    public User(final String login, final String password, final String userId, final String userName) {
        this.password = password;
        this.login = login;
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getUserId() {
        return userId;
    }
}
