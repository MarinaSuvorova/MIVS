package Users;

public class Student extends User {
    UserType userType = UserType.STUDENT;
    private int TotalNumberOfCredits;


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
