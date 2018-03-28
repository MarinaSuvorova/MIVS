import Users.Admin;
import Users.Lecturer;
import Users.Student;
import Users.User;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserPropertiesReader {
    HashMap<String, String > userHashMap=new HashMap<String, String >();

    public void addUserPropertiesToHashMap() {
        File userPropertyFile = new File("UserProperties.txt");

        try (FileReader fileReader = new FileReader(userPropertyFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String userProperties=bufferedReader.readLine();
            while ((userProperties = bufferedReader.readLine()) != null) {
                String[] userProperty = userProperties.split("[;]");
                String key = userProperty[0];
                String[] userRole = userProperty[0].split("-");
                User user;
                switch (userRole[0]) {
                    case "ADM":
                        user = new Admin(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]);
                        break;
                    case "LEC":
                        user = new Lecturer(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]);
                        break;
                    case "STU":
                        user = new Student(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]);
                        break;
                    default:
                        return;

                }
                userHashMap.put(key,user.userPropertiesToFile());
            }

        } catch (Exception e) {
            System.out.println("user properties to hashmap failed");
        }

        userPropertyFile.renameTo(new File("deleteThis.txt"));
    }


//    public void storeAllUsers() {
//        Map<String, User> userPropertiesMap = new HashMap<String, User>();
//        try (FileReader fileReader = new FileReader("UserProperties.txt");
//             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
//            String line = bufferedReader.readLine();
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] userProperty = line.split(";");
//                String[] userRole = userProperty[0].split("-");
//                switch (userRole[0]) {
//                    case "ADM":
//                        userPropertiesMap.put(userProperty[0], new Admin(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]));
//                        break;
//                    case "LEC":
//                        userPropertiesMap.put(userProperty[0], new Lecturer(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]));
//                        break;
//                    case "STU":
//                        userPropertiesMap.put(userProperty[0], new Student(userProperty[1], userProperty[2], userProperty[3], userProperty[4], userProperty[5], userProperty[6], userProperty[7]));
//                        break;
//                    default:
//                        return;
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }


    public void temporaryUserProperties () {
        UserPropertiesReader userPropertiesReader = new UserPropertiesReader();

        File temporary = new File("UserProperties.txt");
        try (FileWriter fileWriter = new FileWriter(temporary);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("ID;firstName;lastName;dateOfBirth;email;mobileNumber;gender;address;\n");
            userHashMap.replace("LEC-1001", "Maru;Suvorova;1994-03-11;32.marina.suvorova@gmail.com;+370 600 51416;female;Architektu 67-34,Vilnius;");
            for (String p : userHashMap.keySet()) {
                bufferedWriter.write(p + ";" + userHashMap.get(p));
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public String[] fillUserProperties(String fullID) throws Exception {
        String[] userProperties = new String[8];
        try (FileReader fileReader = new FileReader("UserProperties.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(";");
                for (int i = 0; i < info.length; i++) {
                    if (info[0].equals(fullID)) {
                        userProperties[i] = info[i];
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return userProperties;
    }

}
