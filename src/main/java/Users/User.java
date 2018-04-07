package Users;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class User {
    private String ID, userName,role, password, firstName, lastName, email, mobileNumber, gender, address;
    private LocalDate dateOfBirth;

    enum UserType {ADMIN, LECTURER, STUDENT}

    public User(String firstName, String lastName, LocalDate dateOfBirth, String email, String mobileNumber, String gender, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
    }

    public User() {
    }

    public User(String firstName, String lastName, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.address = address;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public void setUserProperties(String ID,String[][]userInfo) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginData(String ID, String[] loginInfo) {
        this.ID = ID;
        this.userName = loginInfo[0];
        this.password = loginInfo[1];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        try {
            this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("\nWrong date format\n");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String userPropertiesToFile() {
        return firstName + ";" +
                lastName + ";" +
                dateOfBirth + ";" +
                email + ";" +
                mobileNumber + ";" +
                gender + ";" +
                address + ";";
    }

    public String userLoginInfoToFile() {
        return userName + ";" +
                password + ";";
    }

    public void printUserTable() {
        switch (getID().split("-")[0]){
            case "ADM":
                role="admin";
                break;
            case "LEC":
                role="lecturer";
                break;
            case "STU":
                role="student";
                break;
            default:
                role="student";
                break;}
        String lineSeparator = new String(new char[219]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", "USER ID", "USER ROLE", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME", "DATE OF BIRTH", "EMAIL", "MOBILE NUMBER", "GENDER", "ADDRESS");
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", ID, role, userName, password, firstName, lastName, dateOfBirth, email, mobileNumber, gender, address);
        System.out.println(lineSeparator);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


