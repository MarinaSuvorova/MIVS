import Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Properties;

public class DataStorage {
    boolean uniqueUsername;

    DataStorage() {
    }

    private static HashMap<String, String> loginInfo = new HashMap<String, String>();
    private static HashMap<String, String> userProperties = new HashMap<String, String>();
    private static HashMap<String, String> coursesInfo = new HashMap<String, String>();
    private static HashMap<String, String> studentCourses = new HashMap<String, String>();
    private int lastID;


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
        for (String p : coursesInfo.keySet()) {
            System.out.println(p + ";" + coursesInfo.get(p));
        }
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
//        for (String p : loginInfo.keySet()) {
//            System.out.println(p + ";" + loginInfo.get(p));
//        }
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
                if(this.lastID<Integer.parseInt(splitID[1])){
                this.lastID = Integer.parseInt(splitID[1]);
            } }catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getLastID() {
        return lastID;
    }
}
