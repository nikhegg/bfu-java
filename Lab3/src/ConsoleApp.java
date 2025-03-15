package Lab3.src;

import java.util.Scanner;
import Lab3.src.Cinema.CinemaManager;

public class ConsoleApp {
    protected Scanner scanner;
    protected Auth auth;
    protected CinemaManager cManager;
    protected CommandHandler commands;
    private String loggedAs;

    public ConsoleApp() {
        this.scanner = new Scanner(System.in, "UTF-8");
        this.auth = new Auth();
        this.cManager = new CinemaManager();
        this.commands = new CommandHandler(this);
        try {
            User admin = this.auth.registerUser("admin", "admin");
            admin.setAdmin(true);
            this.auth.registerUser("user", "strong");
        } catch (Exception e) {
            System.out.println("Console app crashed:");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public String input() {
        System.out.print("> ");
        return this.scanner.nextLine();
    }
    public int inputInt() {
        System.out.print("> ");
        int result = this.scanner.nextInt();
        this.scanner.nextLine(); // После nextInt остаётся \n как строка 💪💪💪
        return result;
    }

    // Returns boolean that states whether the user is admin or not
    private boolean authorize() {
        System.out.println("Enter your username:");
        String username = this.scanner.nextLine();
        while(!this.auth.userExists(username)) {
            System.out.println("User doesn't exist. Try another username");
            username = this.scanner.next();
        }
        System.out.println("Enter your password:");
        boolean loggedIn = false;
        while(!loggedIn) {
            try {
                String password = this.scanner.nextLine();
                this.auth.login(username, password);
                loggedIn = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        StringBuilder loggedInMsg = new StringBuilder();
        loggedInMsg.append("\n--- Welcome back, ");
        loggedInMsg.append(username);
        loggedInMsg.append("! ---");
        System.out.println(loggedInMsg.toString());
        this.loggedAs = username;

        try {
            return this.auth.isAdmin(username);
        } catch (Exception e) {
            return false;
        }
    }

    private void openUserPanel(boolean isAdmin) {
        System.out.println("Type help if you need the list of commands");
        while(true) {
            String cmd = this.input().toLowerCase();
            try {
                this.commands.execute(cmd);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void start() {
        boolean isAdmin = this.authorize();
        this.openUserPanel(isAdmin);
        this.scanner.close();
    }

    public boolean isLogged() {
        return this.loggedAs != null;
    }
    public String getLoggedAsUsername() {
        if(!this.isLogged()) return null;
        return this.loggedAs;
    }
    public boolean isLoggedAsAdmin() {
        if(!this.isLogged()) return false;
        try {
            boolean isAdmin = this.auth.isAdmin(this.loggedAs);
            return isAdmin;
        } catch (Exception e) {
            return false;
        }
    }
    public Auth getAuth() {
        return this.auth;
    }
    public CinemaManager getCinemaManager() {
        return this.cManager;
    }
    public CommandHandler getCommandHandler() {
        return this.commands;
    }
}
