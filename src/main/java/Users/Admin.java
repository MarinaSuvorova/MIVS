package Users;

public class Admin extends User {
    UserType userType = UserType.ADMIN;

    public UserType getUserType() {
        return userType;
    }
}
