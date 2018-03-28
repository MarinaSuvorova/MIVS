package Users;

import java.time.LocalDate;

public class Lecturer extends User {
    UserType userType = UserType.LECTURER;
    public Lecturer(){}

    public Lecturer(String firstName, String lastName, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        super(firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
    }




    public UserType getUserType() {
        return userType;
    }

}
