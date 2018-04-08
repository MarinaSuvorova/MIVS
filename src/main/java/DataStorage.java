import Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStorage {
    boolean uniqueUsername;

    private static HashMap<String, String> loginInfo = new HashMap<String, String>();
    private static HashMap<String, String> userProperties = new HashMap<String, String>();
    private static HashMap<String, String> coursesInfo = new HashMap<String, String>();
    private static List<String> studentCourses = new ArrayList<>();
    private static List<String> allowedCourses = new ArrayList<>();
    private int lastID;
    private int lastCourseCode;
    public boolean userAdded;

    public void setUserAdded(boolean userAdded) {
        this.userAdded = userAdded;
    }

    public void storeData(String fileName, HashMap mapName) {

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String fileLine = bufferedReader.readLine();
            while ((fileLine = bufferedReader.readLine()) != null) {
                String[] fileData = fileLine.split("[;]");
                String key = fileData[0];
                String data = "";
                for (int i = 1; i < fileData.length; i++) {
                    data = data + fileData[i] + ";";
                }
                mapName.put(key, data);

            }
        } catch (Exception e) {
            System.out.println("storeData failed at " + fileName);
        }
    }

    public void storeLoginInfo() {
        storeData("LoginInfo.txt", loginInfo);
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public void storeList(String fileName, List list) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String fileLine = bufferedReader.readLine();
            while ((fileLine = bufferedReader.readLine()) != null) {
                if (!list.contains(fileLine)) {
                    list.add(fileLine);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void storeStudentCourses() {
        storeList("StudentCourses.txt", studentCourses);
    }

    public List<String> getStudentCourses() {
        return studentCourses;
    }

    public void storeCoursesInfo() {
        storeData("Courses.txt", coursesInfo);
    }

    public HashMap<String, String> getCoursesInfo() {
        return coursesInfo;
    }

    public boolean isUsernameUnique(String username) {
        for (String ID : loginInfo.keySet()) {
            String[] loginData = loginInfo.get(ID).split(";");
            if (!loginData[0].equals(username)) {
                this.uniqueUsername = true;
            } else {
                this.uniqueUsername = false;
                break;
            }
        }
        return uniqueUsername;
    }


    public void storeUserProperties() {
        storeData("UserProperties.txt", userProperties);
    }

    public HashMap<String, String> getUserProperties() {

        return userProperties;
    }

    public String[][] getUserInfoByID(String ID) {
        storeUserProperties();
        String[][] currentUserProperties = {(loginInfo.get(ID).split(";")), (userProperties.get(ID).split(";"))};
        return currentUserProperties;
    }

    public String[] getCurrentCourseInfo(String courseCode) {
        storeCoursesInfo();
        String[] currentCourseInfo = coursesInfo.get(courseCode).split(";");
        return currentCourseInfo;
    }

    public void updateUserInfoHashMap(String userID, String userPropertiesToFile, String userLoginInfoToFile) {
        for (String hashKey : userProperties.keySet()) {
            if (hashKey.equals(userID)) {
                userProperties.put(userID, userPropertiesToFile);
            }
        }
        for (String hashKey : loginInfo.keySet()) {
            if (hashKey.equals(userID)) {
                loginInfo.put(userID, userLoginInfoToFile);
            }
        }
    }

    public void addNewUserToHashMaps(String ID, String loginData, String userPropertiesData) {
        loginInfo.put(ID, loginData);
        userProperties.put(ID, userPropertiesData);
        setUserAdded(true);
    }

    public void changeCourseInfoHashMap(String courseCode, String courseData) {
        for (String key : coursesInfo.keySet()) {
            if (key.equals(courseCode)) {
                coursesInfo.put(courseCode, courseData);
            }

        }
    }

    public void setLastID() {
        for (String key : loginInfo.keySet()) {
            String[] splitID = key.split("-");
            try {
                if (this.lastID < Integer.parseInt(splitID[1])) {
                    this.lastID = Integer.parseInt(splitID[1]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastCourseCode() {
        for (String key : coursesInfo.keySet()) {
            String[] splitCourseCode = key.split("-");
            try {
                if (this.lastCourseCode < Integer.parseInt(splitCourseCode[1])) {
                    this.lastCourseCode = Integer.parseInt(splitCourseCode[1]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getLastCourseCode() {
        setLastCourseCode();
        return lastCourseCode;
    }

    public void printUsersTable() {
        String lineSeparator = new String(new char[219]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |", "USER ID", "USER ROLE", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME", "DATE OF BIRTH", "EMAIL", "MOBILE NUMBER", "GENDER", "ADDRESS");
        System.out.println();
        System.out.println(lineSeparator);
        for (String ID : userProperties.keySet()) {
            String userRole = defineUserRole(ID);
            String username = (loginInfo.get(ID).split(";")[0]);
            int passwordLength = loginInfo.get(ID).split(";")[1].length();
            String password = new String(new char[passwordLength]).replace('\0', '*');
            String firstName = (userProperties.get(ID).split(";")[0]);
            String lastName = (userProperties.get(ID).split(";")[1]);
            String dateOfBirth = (userProperties.get(ID).split(";")[2]);
            String email = (userProperties.get(ID).split(";")[3]);
            String mobilenumber = (userProperties.get(ID).split(";")[4]);
            String gender = (userProperties.get(ID).split(";")[5]);
            String address = (userProperties.get(ID).split(";")[6]);
            System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", ID, userRole, username, password, firstName, lastName, dateOfBirth, email, mobilenumber, gender, address);

        }
        System.out.println(lineSeparator);
    }

    public void printCurrentUsersTable(String userID) {
        String lineSeparator = new String(new char[219]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |", "USER ID", "USER ROLE", "USERNAME", "PASSWORD", "FIRST NAME", "LAST NAME", "DATE OF BIRTH", "EMAIL", "MOBILE NUMBER", "GENDER", "ADDRESS");
        System.out.println();
        System.out.println(lineSeparator);
        for (String ID : userProperties.keySet()) {
            if (ID.equals(userID)) {
                String userRole = defineUserRole(ID);
                String username = (loginInfo.get(ID).split(";")[0]);
                int passwordLength = loginInfo.get(ID).split(";")[1].length();
                String password = new String(new char[passwordLength]).replace('\0', '*');
                String firstName = (userProperties.get(ID).split(";")[0]);
                String lastName = (userProperties.get(ID).split(";")[1]);
                String dateOfBirth = (userProperties.get(ID).split(";")[2]);
                String email = (userProperties.get(ID).split(";")[3]);
                String mobilenumber = (userProperties.get(ID).split(";")[4]);
                String gender = (userProperties.get(ID).split(";")[5]);
                String address = (userProperties.get(ID).split(";")[6]);
                System.out.printf("| %-8s | %-9s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s| %-15s | %-6s | %-35s |\n", ID, userRole, username, password, firstName, lastName, dateOfBirth, email, mobilenumber, gender, address);

            }
        }
        System.out.println(lineSeparator);
    }

    public String defineUserRole(String ID) {
        String[] role = ID.split("-");
        String userRole;
        if (role[0].equals("ADM")) {
            userRole = "admin";
        } else if (role[0].equals("LEC")) {
            userRole = "lecturer";

        } else {
            userRole = "student";
        }
        return userRole;
    }

    public void printAllLecturers() {
        String lineSeparator = new String(new char[143]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-8s | %-9s | %-20s | %-20s | %-20s | %-30s| %-15s |\n", "USER ID", "USER ROLE", "USERNAME", "FIRST NAME", "LAST NAME", "EMAIL", "MOBILE NUMBER");
        System.out.println(lineSeparator);
        for (String ID : userProperties.keySet()) {
            if (ID.split("-")[0].equals("LEC")) {
                String userRole = defineUserRole(ID);
                String username = (loginInfo.get(ID).split(";")[0]);
                String firstName = (userProperties.get(ID).split(";")[0]);
                String lastName = (userProperties.get(ID).split(";")[1]);
                String email = (userProperties.get(ID).split(";")[3]);
                String mobilenumber = (userProperties.get(ID).split(";")[4]);
                System.out.printf("| %-8s | %-9s | %-20s | %-20s | %-20s | %-30s| %-15s |\n", ID, userRole, username, firstName, lastName, email, mobilenumber);

            }
        }
        System.out.println(lineSeparator);
    }

    public void printCoursesTable() {
        storeCoursesInfo();
        String lineSeparator = new String(new char[254]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", "COURSE CODE", "LECTURER", "CREDIT", "TITLE", "DESCRIPTION", "START DATE");
        System.out.println(lineSeparator);
        for (String courseCode : coursesInfo.keySet()) {
            String lecID = (coursesInfo.get(courseCode).split(";")[0]);
            String lecturer = (getUserProperties().get(lecID).split(";")[0]) + " " + (getUserProperties().get(lecID).split(";")[1]);
            String credit = (coursesInfo.get(courseCode).split(";")[1]);
            String title = (coursesInfo.get(courseCode).split(";")[2]);
            String description = (coursesInfo.get(courseCode).split(";")[3]);
            String startDate = (coursesInfo.get(courseCode).split(";")[4]);
            System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", courseCode, lecturer, credit, title, description, startDate);
        }
        System.out.println(lineSeparator);
    }

    public void printCurrentCourseTable(String courseCode) {
        storeCoursesInfo();
        String lineSeparator = new String(new char[254]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", "COURSE CODE", "LECTURER", "CREDIT", "TITLE", "DESCRIPTION", "START DATE");
        System.out.println(lineSeparator);
        for (String key : coursesInfo.keySet()) {
            if (key.equals(courseCode)) {
                String lecID = (coursesInfo.get(courseCode).split(";")[0]);
                String lecturer = (getUserProperties().get(lecID).split(";")[0]) + " " + (getUserProperties().get(lecID).split(";")[1]);
                String credit = (coursesInfo.get(courseCode).split(";")[1]);
                String title = (coursesInfo.get(courseCode).split(";")[2]);
                String description = (coursesInfo.get(courseCode).split(";")[3]);
                String startDate = (coursesInfo.get(courseCode).split(";")[4]);
                System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", courseCode, lecturer, credit, title, description, startDate);
                System.out.println(lineSeparator);
            }
        }
    }

    public void printLecturersCoursesTable(String ID) {
        storeCoursesInfo();
        String lineSeparator = new String(new char[231]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-6s | %-40s | %-145s | %-13s |\n", "COURSE CODE", "CREDIT", "TITLE", "DESCRIPTION", "START DATE");
        System.out.println(lineSeparator);
        for (String courseCode : coursesInfo.keySet()) {
            String lecID = (coursesInfo.get(courseCode).split(";")[0]);
            String credit = (coursesInfo.get(courseCode).split(";")[1]);
            String title = (coursesInfo.get(courseCode).split(";")[2]);
            String description = (coursesInfo.get(courseCode).split(";")[3]);
            String startDate = (coursesInfo.get(courseCode).split(";")[4]);
            if (lecID.equals(ID)) {
                System.out.printf("| %-11s | %-6s | %-40s | %-145s | %-13s |\n", courseCode, credit, title, description, startDate);
            }
        }
        System.out.println(lineSeparator);
    }

    public void printStudentsCoursesTable(String stuID) {
        storeCoursesInfo();
        storeStudentCourses();
        int totalNumberOfCredits = 0;
        String lineSeparator = new String(new char[254]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-20s | %-40s | %-145s | %-6s | %-13s |\n", "COURSE CODE", "LECTURER", "TITLE", "DESCRIPTION", "CREDIT", "START DATE");
        System.out.println(lineSeparator);
        for (String data : studentCourses) {
            if (stuID.equals(data.split(";")[0])) {
                String courseCode = data.split(";")[1];
                if (coursesInfo.containsKey(courseCode)) {
                    String lecID = coursesInfo.get(courseCode).split(";")[0];
                    String lecturer = (getUserProperties().get(lecID).split(";")[0]) + " " + (getUserProperties().get(lecID).split(";")[1]);
                    try {
                        String credit = (coursesInfo.get(courseCode).split(";")[1]);

                        totalNumberOfCredits = totalNumberOfCredits + Integer.parseInt(credit);

                        String title = (coursesInfo.get(courseCode).split(";")[2]);
                        String description = (coursesInfo.get(courseCode).split(";")[3]);
                        String startDate = (coursesInfo.get(courseCode).split(";")[4]);
                        System.out.printf("| %-11s | %-20s | %-40s | %-145s | %-6s | %-13s |\n", courseCode, lecturer, title, description, credit, startDate);
                    } catch (Exception e) {
                    }
                }
            }

        }
        System.out.println(lineSeparator);
        System.out.println("\nTotal Number of Credits: " + totalNumberOfCredits);
    }


    public void addNewCourseToHashMaps(String courseCode, String courseData) {
        storeCoursesInfo();
        coursesInfo.put(courseCode, courseData);
    }

    public void showLecturersStudents(String userID) {
        storeStudentCourses();
        storeCoursesInfo();
        String lineSeparator = new String(new char[141]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-40s | %-20s | %-20s | %-30s | %-15s |\n", "COURSE TITLE", "FIRST NAME", "LAST NAME", "EMAIL", "MOBILE NUMBER");
        System.out.println(lineSeparator);
        for (String courseCode : coursesInfo.keySet()) {
            if (coursesInfo.get(courseCode).split(";")[0].equals(userID)) {
                for (String data : studentCourses) {
                    if (courseCode.equals(data.split(";")[1])) {
                        String stuID = data.split(";")[0];
                        String courseTitle = coursesInfo.get(courseCode).split(";")[2];
                        String firstName = userProperties.get(stuID).split(";")[0];
                        String lastName = userProperties.get(stuID).split(";")[1];
                        String email = userProperties.get(stuID).split(";")[3];
                        String mobileNumber = userProperties.get(stuID).split(";")[4];
                        System.out.printf("| %-40s | %-20s | %-20s | %-30s | %-15s |\n", courseTitle, firstName, lastName, email, mobileNumber);
                    }
                }

            }

        }
        System.out.println(lineSeparator);
    }

    public void addStudentsCourse(String studentsCourse) {
        storeStudentCourses();
        if (!studentCourses.contains(studentsCourse)) {
            studentCourses.add(studentsCourse);
        }
    }

    public int countStudentsCredits(String stuID) {
        storeStudentCourses();
        storeCoursesInfo();
        int totalNumberOfCredits = 0;
        for (String data : studentCourses) {
            if (stuID.equals(data.split(";")[0])) {
                String courseCode = data.split(";")[1];
                try {
                    String credit = (coursesInfo.get(courseCode).split(";")[1]);
                    totalNumberOfCredits = totalNumberOfCredits + Integer.parseInt(credit);
                } catch (Exception e) {
                }
            }
        }
        return totalNumberOfCredits;
    }

    public void deleteStudentsCourse(String studentsCourse) {
        if (studentCourses.contains(studentsCourse)) {
            studentCourses.remove(studentsCourse);
        }
    }

    public void printAllowedCoursesTable() {
        String lineSeparator = new String(new char[254]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", "COURSE CODE", "LECTURER", "CREDIT", "TITLE", "DESCRIPTION", "START DATE");
        System.out.println(lineSeparator);
        for (String courseCode : allowedCourses) {
            String lecID = (coursesInfo.get(courseCode).split(";")[0]);
            String lecturer = (getUserProperties().get(lecID).split(";")[0]) + " " + (getUserProperties().get(lecID).split(";")[1]);
            String credit = coursesInfo.get(courseCode).split(";")[1];
            String startDate = coursesInfo.get(courseCode).split(";")[4];
            String title = (coursesInfo.get(courseCode).split(";")[2]);
            String description = (coursesInfo.get(courseCode).split(";")[3]);
            System.out.printf("| %-11s | %-20s | %-6s | %-40s | %-145s | %-13s |\n", courseCode, lecturer, credit, title, description, startDate);

        }
        System.out.println(lineSeparator);
    }

    public int countAllowedCourses(String stuID, int allowedCredits) {
        allowedCourses.clear();
        for (String courseCode : coursesInfo.keySet()) {
            try {
                int credit = Integer.parseInt(coursesInfo.get(courseCode).split(";")[1]);
                LocalDate startDate = LocalDate.parse((coursesInfo.get(courseCode).split(";")[4]), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if ((credit <= allowedCredits) && (startDate.isAfter(LocalDate.now()))) {
                    allowedCourses.add(courseCode);
                }
                for (String data : studentCourses) {
                    if (stuID.equals(data.split(";")[0])) {
                        allowedCourses.remove(data.split(";")[1]);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }


        }
        return allowedCourses.size();
    }

    public void deleteUser(String userID) {
        userProperties.remove(userID);
        loginInfo.remove(userID);
        if (userID.split("-")[0].equals("LEC")) {
            List<String> coursesToRemove = new ArrayList<>();
            for (String courseCode : coursesInfo.keySet()) {
                if (coursesInfo.get(courseCode).split(";")[0].equals(userID)) {
                    coursesToRemove.add(courseCode);
                }
            }
            removeLecturersCourses(coursesToRemove);
        }
    }

    private void removeLecturersCourses(List<String> coursesToRemove) {
        List<String> studentCoursesToRemove = new ArrayList<>();
        for (String code : coursesToRemove) {
            for (String data : studentCourses) {
                if (data.split(";")[1].equals(code)) {
                    studentCoursesToRemove.add(data);
                }
            }
            coursesInfo.remove(code);
        }
        removeStunedntsFromCOurse(studentCoursesToRemove);

    }

    private void removeStunedntsFromCOurse(List<String> studentCoursesToRemove) {
        for (String data : studentCoursesToRemove) {
            studentCourses.remove(data);
        }
    }


}

