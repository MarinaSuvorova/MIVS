import Users.Admin;
import Users.Lecturer;
import Users.Student;
import Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Scanner;

public class Login {
    private boolean foundUser;
    private boolean loginDataValid;

    public boolean isFoundUser() {
        return foundUser;
    }

    public void setFoundUser(boolean foundUser) {
        this.foundUser = foundUser;
    }

    public boolean isLoginDataValid() {
        return loginDataValid;
    }

    public void setLoginDataValid(boolean loginDataValid) {
        this.loginDataValid = loginDataValid;
    }

    public void close() {
        this.loginDataValid = false;
    }

    public void login() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter login");
        String login = sc.next();
        //     UserInfo userInfo = new UserInfo();

        try (FileReader fileReader = new FileReader("LoginInfo.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(";");

                for (int i = 0; i < info.length; i++) {
                    if (info[0].equals(login)) {
                        foundUser = true;
                        System.out.println("Enter password");
                        String password = sc.next();
                        if (info[1].equals(password)) {
                            setLoginDataValid(true);
                            userSignedIn(info[2], info[0]);
                            break;
                        } else {
                            int wrongInput = 1;

                            while ((wrongInput < 3) || (loginDataValid)) {
                                System.out.println("\nIncorrect password. \nPlease try again.");
                                if (info[1].equals(sc.next())) {
                                    setLoginDataValid(true);
                                    userSignedIn(info[2], info[0]);
                                    break;
                                } else {
                                    wrongInput++;
                                }
                            }
                            if (!loginDataValid) {
                                System.out.println("Please try again after 10 sec.");
                                Thread sleep = new Thread();
                                sleep.sleep(10000);
                                break;
                            } else {

                                break;
                            }
                        }
                    }
                }
            }
            if (!foundUser) {
                System.out.println("\nWrong username. \nPlease try again.\n");
                login();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void userSignedIn(String fullID, String userName) {
        String[] userRole = fullID.split("-");
        User user = new User();

        switch (userRole[0]) {
            case "ADM":
                user = new Admin();
                break;
            case "LEC":
                user = new Lecturer();
                break;
            case "STU":
                user = new Student();
                break;
            default:
                break;
        }
        user.setUserName(userName);
        System.out.println("hello, " + user.getUserName());

    }


    // GAL PRIE USERIO KŪRIMO
    public void updateMIVSPropertiesID() {
        String id = "1000";
        try (FileReader fileReader = new FileReader("LoginInfo.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(";");
                String[] fullID = info[2].split("-");
                id = fullID[1];
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (FileWriter fileWriter = new FileWriter("mivs.properties")) {

            Properties p = new Properties();
            p.setProperty("id", id);
            p.store(fileWriter, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
