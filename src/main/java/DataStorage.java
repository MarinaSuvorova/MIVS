import Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class DataStorage {
    boolean uniqueUsername;

    private static HashMap<String, String> loginInfo = new HashMap<String, String>();
    private static HashMap<String, String> userProperties = new HashMap<String, String>();
    private static HashMap<String, String> coursesInfo = new HashMap<String, String>();
    private static HashMap<String, String> studentCourses = new HashMap<String, String>();
    private int lastID;
    private int lastCourseCode;


    public void storeData(String fileName, HashMap mapName) {

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String loginLine = bufferedReader.readLine();
            while ((loginLine = bufferedReader.readLine()) != null) {
                String[] loginData = loginLine.split("[;]");
                String key = loginData[0];
                String data = "";
                for (int i = 1; i < loginData.length; i++) {
                    data = data + loginData[i] + ";";
                }
                mapName.put(key, data);

            }
        } catch (Exception e) {
            System.out.println("storeData failed: " + fileName);
        }
    }

    public void storeLoginInfo() {
        storeData("LoginInfo.txt", loginInfo);
    }

    public HashMap<String, String> getLoginInfo() {
//        for (String p : loginInfo.keySet()) {
//            System.out.println(p + ";" + loginInfo.get(p));
//        }
        return loginInfo;
    }

    public void storeStudentCourses() {
        storeData("StudentCourses.txt", studentCourses);
    }

    public HashMap<String, String> getStudentCourses() {
//        for (String p : studentCourses.keySet()) {
//            System.out.println(p + ";" + loginInfo.get(p));
//        }
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

    public String[] getUserPropertiesByID(String ID) {
        storeUserProperties();
        String[] currentUserProperties = userProperties.get(ID).split(";");
        return currentUserProperties;
    }

    public void updateUserPropertiesHashMap(User user) {
        String key = user.getID();
        for (String hashKey : userProperties.keySet()) {
            if (hashKey.equals(key)) {
                userProperties.put(key, user.userPropertiesToFile());
            }
        }
    }

    public void updateLoginInfoHashMap(User user) {
        String key = user.getID();
        for (String hashKey : loginInfo.keySet()) {
            if (hashKey.equals(key)) {
                loginInfo.put(key, user.userLoginInfoToFile());
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

    private String defineUserRole(String ID) {
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
                System.out.println(lineSeparator);
            }


        }
    }

    public void printCoursesTable() {
        storeCoursesInfo();
        String lineSeparator = new String(new char[245]).replace('\0', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-11s | %-11s | %-6s | %-40s | %-145s | %-13s |\n", "COURSE CODE", "LECTURER ID", "CREDIT", "TITLE", "DESCRIPTION", "START DATE");
        System.out.println(lineSeparator);
        for (String courseCode : coursesInfo.keySet()) {
            String lecID = (coursesInfo.get(courseCode).split(";")[0]);
            String credit = (coursesInfo.get(courseCode).split(";")[1]);
            String title = (coursesInfo.get(courseCode).split(";")[2]);
            String description = (coursesInfo.get(courseCode).split(";")[3]);
            String startDate = (coursesInfo.get(courseCode).split(";")[4]);
            System.out.printf("| %-11s | %-11s | %-6s | %-40s | %-145s | %-13s |\n", courseCode, lecID, credit, title, description, startDate);
        }
        System.out.println(lineSeparator);
    }

    public void printLecturersCoursesTable(String ID) {
        storeCoursesInfo();
        String lineSeparator = new String(new char[245]).replace('\0', '-');
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

    public void printStudentsCoursesTable(String ID) {
        storeCoursesInfo();
        String lineSeparator = new String(new char[245]).replace('\0', '-');
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
}
