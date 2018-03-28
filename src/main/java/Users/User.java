package Users;


import de.vandermeer.asciitable.AsciiTable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class User {
    private String ID, userName, password, firstName, lastName, email, mobileNumber, gender, address;
    private LocalDate dateOfBirth;


    enum UserType {ADMIN, LECTURER, STUDENT}


    //
//    public User(String firstName, String lastName, LocalDate dateOfBirth, String email, String mobileNumber, String gender, String address) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.email = email;
//        this.mobileNumber = mobileNumber;
//        this.gender = gender;
//        this.address = address;
//    }
    public User() {
    }

    public User(String firstName, String lastName,  String dateOfBirth, String email, String mobileNumber, String gender, String address) {
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


    public void setUserProperties(String[] userProperties) {
        this.ID = userProperties[0];
        this.firstName = userProperties[1];
        this.lastName = userProperties[2];
        try {
            this.dateOfBirth = LocalDate.parse(userProperties[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
        this.email = userProperties[4];
        this.mobileNumber = userProperties[5];
        this.gender = userProperties[6];
        this.address = userProperties[7];
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

    public void setUserNameAndPassword(String[] loginInfo) {
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
            System.out.println("Wrong date format");
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
    public String userPropertiesToFile(){
        return  firstName+";"+
                lastName+";"+
                dateOfBirth+";"+
                email+";"+
                mobileNumber+";"+
                gender+";"+
                address+";";

    }

    @Override
    public String toString() {

        return "User{" +
                "ID: " + ID +
                ", userName: " + userName +
                ", password: " + password +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", dateOfBirth: " + dateOfBirth +
                ", email: " + email +
                ", mobileNumber: " + mobileNumber +
                ", gender: " + gender +
                ", address: " + address +
                '}';

    }
}

