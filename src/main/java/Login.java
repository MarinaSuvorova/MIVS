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

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String username = sc.next();
        DataStorage dataStorage = new DataStorage();
        HashMap<String, String> loginData = new HashMap<String, String>();
        dataStorage.storeData("LoginInfo.txt",loginData);
        for (String ID : loginData.keySet()) {
            String userLogin = loginData.get(ID);
            String[] loginInfo = userLogin.split(";");
            if (loginInfo[0].equals(username)) {
                foundUser = true;
                System.out.println("Enter password");
                String password = sc.next();
                if (loginInfo[1].equals(password)) {
                    setLoginDataValid(true);
                    try {
                        userSignedIn(ID, loginInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    int wrongInput = 1;

                    while ((wrongInput < 3) || (loginDataValid)) {
                        System.out.println("\nIncorrect password. \nPlease try again.");
                        if (loginInfo[1].equals(sc.next())) {
                            setLoginDataValid(true);
                            try {
                                userSignedIn(ID, loginInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            wrongInput++;
                        }
                    }
                    if (!loginDataValid) {
                        System.out.println("Please try again after 10 sec.");
                        Thread sleep = new Thread();
                        try {
                            sleep.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }


    public void userSignedIn(String ID, String[] loginInfo) throws Exception {
        Menu menu = new Menu();
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
        System.out.println("hello, " + user.getUserName());
        menu.runUserMenu(user);
    }


    // GAL PRIE USERIO KŪRIMO
    public void updateMIVSPropertiesID() {
        String lastID = null;
        //update LoginInfo.txt file!!
        try (FileReader fileReader = new FileReader("LoginInfo.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(";");
                String[] fullID = info[2].split("-");
                lastID = fullID[1];
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (FileWriter fileWriter = new FileWriter("mivs.properties")) {

            Properties p = new Properties();
            p.setProperty("lastID", lastID);
            p.store(fileWriter, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
