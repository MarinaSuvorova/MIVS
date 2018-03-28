package Users;

import java.time.LocalDate;

public class Student extends User {
    UserType userType = UserType.STUDENT;
    private int TotalNumberOfCredits;

    public Student(){}

    public Student(String firstName, String lastName, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        super(firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
    }


    public UserType getUserType() {
        return userType;
    }

    public int getTotalNumberOfCredits() {
        return TotalNumberOfCredits;
    }

    // change
    public void setTotalNumberOfCredits(int totalNumberOfCredits) {
        TotalNumberOfCredits = totalNumberOfCredits;
    }
}
