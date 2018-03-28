package Users;

public class Lecturer extends User {
    UserType userType = UserType.LECTURER;


    public UserType getUserType() {
        return userType;
    }

}
