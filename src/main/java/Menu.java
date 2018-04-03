import Users.Admin;
import Users.Lecturer;
import Users.Student;
import Users.User;

import java.util.Scanner;

public class Menu {
    boolean runApp = true;
    Login app = new Login();
    DataWriter dataWriter = new DataWriter();
    DataStorage dataStorage = new DataStorage();
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
            runLecturerMenu(user);
        } else if (user instanceof Student) {
            runStudentMenu(user);
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
                    editProfileMenu(user);
                    //Edit profile menu
                    break;
                case 6:
                    app.close();
                    runApp = false;
                    break;
                default:
                    System.out.println("\nWrong input\n");
                    runUserMenu(user);
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nWrong number format\n");
        }
    }

    private void runLecturerMenu(User user) {
        System.out.println("1. Edit profile");
        System.out.println("2. My Courses");
        System.out.println("3. My Students");
        // View info / Edit / Delete
        System.out.println("4. Add new Course");
        System.out.println("5. Exit");
        try {
            String userInput = sc.next();
            switch (Integer.parseInt(userInput)) {
                case 1:
                    editProfileMenu(user);
                    break;
                case 5:
                    app.close();
                    runApp = false;
                    break;
                default:
                    System.out.println("\nWrong input\n");
                    runUserMenu(user);
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nWrong number format\n");
        }

    }

    private void runStudentMenu(User user) {
        System.out.println("1. Edit profile");
        System.out.println("2. My Courses");
        System.out.println("3. View all Courses");
        System.out.println("4. Enroll in a New Course");
        //Check TotalNumberOfCredits
        //Print only those Courses with right StartDate
        System.out.println("5. Exit");
        try {
            String userInput = sc.next();
            switch (Integer.parseInt(userInput)) {
                case 1:
                    editProfileMenu(user);
                    break;
                case 5:
                    app.close();
                    runApp = false;
                    break;
                default:
                    System.out.println("\nWrong input\n");
                    runUserMenu(user);
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nWrong number format\n");
        }
    }

    private void editProfileMenu(User user) {
        //  DataStorage dataStorage = new DataStorage();
        while (runApp) {
            System.out.println(user.toString());
            System.out.println("Choose what you want to change:");
            System.out.println("1. username");
            System.out.println("2. password");
            System.out.println("3. first name");
            System.out.println("4. last name");
            System.out.println("5. date of birth");
            System.out.println("6. email address");
            System.out.println("7. mobile number");
            System.out.println("8. gender");
            System.out.println("9. address");
            System.out.println();
            System.out.println("10. Back to main menu");
            try {
                String userInput = sc.next();
                switch (Integer.parseInt(userInput)) {
                    case 1:
                        System.out.println("Enter new username");
                        String newUsername = sc.next();
                        if (dataStorage.isUsernameUnique(newUsername)) {
                            user.setUserName(newUsername);
                            System.out.println("Your username has been changed successfully! Thank you.");
                        } else {
                            System.out.println("This username is already taken. Please choose another username.");
                        }
                        //check if username is unique
                        break;
                    case 2:
                        System.out.println("Enter old password");
                        String oldPassword = sc.next();
                        System.out.println("Enter new password");
                        String newPassword = sc.next();
                        System.out.println("Confirm new password");
                        String confirmedPassword = sc.next();
                        if ((oldPassword.equals(user.getPassword())) && (newPassword.equals(confirmedPassword))) {
                            user.setPassword(newPassword);
                            System.out.println("Your password has been changed successfully! Thank you.");
                        } else {
                            System.out.println("Something went wrong. Please try again");
                        }
                        break;
                    case 3:
                        System.out.println("Enter your first name");
                        user.setFirstName(sc.next());
                        System.out.println("Your first name has been changed successfully! Thank you.");
                        break;
                    case 4:
                        System.out.println("Enter your last name");
                        user.setLastName(sc.next());
                        System.out.println("Your last name has been changed successfully! Thank you.");
                        break;
                    case 5:
                        System.out.println("Enter your date of birth (yyyy-mm-dd");
                        user.setDateOfBirth(sc.next());
                        System.out.println("Your date of birth has been changed successfully! Thank you.");
                        break;
                    case 6:
                        System.out.println("Enter your email address");
                        user.setEmail(sc.next());
                        System.out.println("Your email address has been changed successfully! Thank you.");
                        break;
                    case 7:
                        System.out.println("Enter your mobile number");
                        user.setMobileNumber(sc.nextLine());
                        break;
                    case 8:
                        System.out.println("Choose gender: \n0. -- \n1. male\n2. female");
                        try {
                            switch (sc.nextInt()) {
                                case 0:
                                    user.setGender("");
                                    break;
                                case 1:
                                    user.setGender("male");
                                    break;
                                case 2:
                                    user.setGender("female");
                                    break;
                                default:
                                    System.out.println("\nWrong input\n");
                                    break;
                            }

                        } catch (Exception e) {
                            System.out.println("\nWrong number format\n");
                        }
                        System.out.println("Your gender has been changed successfully! Thank you.");
                        break;
                    case 9:
                        System.out.println("Enter your address");
                        user.setAddress(sc.nextLine());
                        System.out.println("Your address has been changed successfully! Thank you.");
                        break;
                    case 10:
                        runUserMenu(user);
                        break;
                    default:
                        System.out.println("\nWrong input\n");
                        editProfileMenu(user);
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nWrong number format\n");
            }
            dataWriter.updateFiles(user);
            editProfileMenu(user);
        }
    }


}

