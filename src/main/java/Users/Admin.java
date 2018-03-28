package Users;

import java.time.LocalDate;

public class Admin extends User {

    public Admin(){}

    public Admin(String firstName, String lastName, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        super(firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
    }

    UserType userType = UserType.ADMIN;


    public UserType getUserType() {
        return userType;
    }

}
