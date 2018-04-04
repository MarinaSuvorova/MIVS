package Users;

import java.time.LocalDate;

public class Student extends User {
    UserType userType = UserType.STUDENT;
    private int TotalNumberOfCredits;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String email, String mobileNumber, String gender, String address, UserType userType) {
        super(firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
        this.userType = userType;
    }
    public Student() {}

    public int getTotalNumberOfCredits() {
        return TotalNumberOfCredits;
    }

    // change
    public void setTotalNumberOfCredits(int totalNumberOfCredits) {
        TotalNumberOfCredits = totalNumberOfCredits;
    }
}
