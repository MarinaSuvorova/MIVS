import Users.Admin;
import Users.Lecturer;
import Users.Student;
import Users.User;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class Menu {
    boolean runApp = true;
    Login app = new Login();
    Scanner sc = new Scanner(System.in);

    public void runWelcomeMenu() throws Exception {
        System.out.println("Welcome to MIVS");
        while (runApp && app.isLoginDataValid() == false) {
            System.out.println("1. Sign in");
            System.out.println("2. Exit");
            try {
                String userInput = sc.next();
                switch (Integer.parseInt(userInput)) {
                    case 1:
                        if (!app.isLoginDataValid()) {
                            app.login();
                        }
                        break;
                    case 2:
                        app.close();
                        runApp = false;
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("\nWrong number format\n");
            }
        }
    }

    public void runUserMenu(User user) {

        if (user instanceof Admin) {
            runAdminMenu(user);
        } else if (user instanceof Lecturer) {
            runLecturerMenu();
        } else if (user instanceof Student) {
            runStudentMenu();
        } else {
            return;
        }

    }


    private void runAdminMenu(User user) {
        System.out.println("1. Edit profile");
        System.out.println("2. View all Courses");
        // View info / Edit / Delete
        System.out.println("3. View all Users");
        // [firstName, lastName], [email], [role]
        System.out.println("4. Add new User");
        System.out.println("5. Add new Course");
        System.out.println("6. Exit");
        try {
            String userInput = sc.next();
            switch (Integer.parseInt(userInput)) {
                case 1:
                    //Edit profile menu
                    break;
                case 2:
                    app.close();
                    runApp = false;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("\nWrong number format\n");
        }
    }

    private void runLecturerMenu() {
        System.out.println("1. Edit profile");
        System.out.println("2. My Courses");
        System.out.println("3. My Students");
        // View info / Edit / Delete
        System.out.println("4. Add new Course");
        System.out.println("5. Exit");
    }

    private void runStudentMenu() {
        System.out.println("1. Edit profile");
        System.out.println("2. My Courses");
        System.out.println("3. View all Courses");
        System.out.println("4. Enroll in a New Course");
        //Check TotalNumberOfCredits
        //Print only those Courses with right StartDate
        System.out.println("5. Exit");
    }
}

