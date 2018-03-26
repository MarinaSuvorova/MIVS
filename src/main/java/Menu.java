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

    public void runAdminMenu() {
        //
        System.out.println("Edit profile");
        System.out.println("View all Courses");
        // View info / Edit / Delete
        System.out.println("View all Users");
        // [firstName, lastName], [email], [role]
        System.out.println("Add new User");
        System.out.println("Add new Course");
        System.out.println("Exit");


    }

    public void runLecturerMenu() {
        System.out.println("Edit profile");
        System.out.println("My Courses");
        // View info / Edit / Delete
        System.out.println("Add new Course");
        System.out.println("Exit");


    }

    public void runStudentMenu() {
        System.out.println("Edit profile");
        System.out.println("My Courses");
        System.out.println("View all Courses");
        System.out.println("Enroll in a New Course");
        //Check TotalNumberOfCredits
        //Print only those Courses with right StartDate
        System.out.println("Exit");


    }

}

