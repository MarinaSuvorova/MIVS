import Users.Admin;
import Users.Lecturer;
import Users.Student;
import Users.User;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
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
        DataStorage dataStorage = new DataStorage();
        dataStorage.storeLoginInfo();
        HashMap<String, String> loginData = dataStorage.getLoginInfo();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter login");
        String login = sc.next();
        for (String ID : loginData.keySet()) {
            String[] loginInfo = loginData.get(ID).split(";");
            for (int i = 0; i < loginInfo.length; i++) {
                if (loginInfo[0].equals(login)) {
                    foundUser = true;
                    System.out.println("Enter password");
                    String password = sc.next();
                    if (loginInfo[1].equals(password)) {
                        setLoginDataValid(true);
                        userSignedIn(ID, loginInfo);
                        break;
                    } else {
                        int wrongInput = 1;

                        while ((wrongInput < 3) || (loginDataValid)) {
                            System.out.println("\nIncorrect password. \nPlease try again.");
                            if (loginInfo[1].equals(sc.next())) {
                                setLoginDataValid(true);
                                userSignedIn(ID, loginInfo);
                                break;
                            } else {
                                wrongInput++;
                            }
                        }
                        if (!loginDataValid) {
                            System.out.println("Please try again after 5 sec.");
                            Thread sleep = new Thread();
                            sleep.sleep(5000);
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
    }


    public void userSignedIn(String ID, String[] loginInfo) throws Exception {
        Menu menu = new Menu();
        DataStorage dataStorage = new DataStorage();
        String[] userProperties = dataStorage.getUserPropertiesByID(ID);
        String[] userRole = ID.split("-");
        User user = null;
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
                return;
        }
        user.setLoginData(ID, loginInfo);
        user.setUserProperties(ID, userProperties);
//        System.out.println(user.toString());
        System.out.println("hello, " + user.getUserName());
        menu.runUserMenu(user);
    }

}
