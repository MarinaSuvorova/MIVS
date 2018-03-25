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
    public void runAppMenu(){
        System.out.println("1. Sign in");
        System.out.println("2. Exit");

    }
}

