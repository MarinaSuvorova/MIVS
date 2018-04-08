package Users;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class User {
    private String ID, userName, role, password, firstName, lastName, email, mobileNumber, gender, address;
    private LocalDate dateOfBirth;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public void setUserProperties(String ID, String[][] userInfo) {
        this.ID = ID;
        this.userName = userInfo[0][0];
        this.password = userInfo[0][1];
        this.firstName = userInfo[1][0];
        this.lastName = userInfo[1][1];
        try {
            this.dateOfBirth = LocalDate.parse(userInfo[1][2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
        this.email = userInfo[1][3];
        this.mobileNumber = userInfo[1][4];
        this.gender = userInfo[1][5];
        this.address = userInfo[1][6];
    }

    public void printUserTable() {
        switch (getID().split("-")[0]) {
            case "ADM":
                role = "admin";
                break;
            case "LEC":
                role = "lecturer";
                break;
            case "STU":
                role = "student";
                break;
            default:
                role = "student";
                break;
        }
        String lineSeparator = new String(new char[219]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", "USER ID", "USER ROLE", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME", "DATE OF BIRTH", "EMAIL", "MOBILE NUMBER", "GENDER", "ADDRESS");
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", ID, role, userName, password, firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
        System.out.println(lineSeparator);
    }

}




