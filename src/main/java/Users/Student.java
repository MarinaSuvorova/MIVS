package Users;

public class Student extends User {
    private final String ROLE = "Student";
    private int TotalNumberOfCredits;


    public String getROLE() {
        return ROLE;
    }

    public int getTotalNumberOfCredits() {
        return TotalNumberOfCredits;
    }

    // change
    public void setTotalNumberOfCredits(int totalNumberOfCredits) {
        TotalNumberOfCredits = totalNumberOfCredits;
    }
}
