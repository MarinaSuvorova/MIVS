import Users.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Properties;

public class DataWriter {
    DataStorage dataStorage = new DataStorage();

    DataWriter() {
    }

    public void updateUserProperties() {
        HashMap<String, String> userProperties = dataStorage.getUserProperties();
        File updateFile = new File("UserProperties.txt");
        try (FileWriter fileWriter = new FileWriter(updateFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("ID;firstName;lastName;dateOfBirth;email;mobileNumber;gender;address;\n");
            for (String ID : userProperties.keySet()) {
                bufferedWriter.write(ID + ";" + userProperties.get(ID));
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateLoginInfo() {
        HashMap<String, String> loginInfo = dataStorage.getLoginInfo();
        File updateFile = new File("LoginInfo.txt");
        try (FileWriter fileWriter = new FileWriter(updateFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("ID;username;password;\n");
            for (String ID : loginInfo.keySet()) {
                bufferedWriter.write(ID + ";" + loginInfo.get(ID));
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCoursesInfo() {
        HashMap<String, String> coursesInfo = dataStorage.getCoursesInfo();
        File updateFile = new File("Courses.txt");
        try (FileWriter fileWriter = new FileWriter(updateFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("CourseCode;lecID;Credit;Title;Description;StartDate;\n");
            for (String courseCode : coursesInfo.keySet()) {
                bufferedWriter.write(courseCode + ";" + coursesInfo.get(courseCode));
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void updateUserFiles() {
        updateUserProperties();
        updateLoginInfo();
    }

}
