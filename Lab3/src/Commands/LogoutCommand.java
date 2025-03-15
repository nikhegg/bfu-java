package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public class LogoutCommand extends AbstractCommand {
    public LogoutCommand() {
        super("logout", false);
        this.setDescription("Log out the account. This command allows you to switch between accounts");
    }

    public void execute(ConsoleApp app) {
        String username = app.getLoggedAsUsername();
        if(username == null) return;
        app.getAuth().logout(username);
        System.out.println("Successfully logged out from " + username + " account\n\n");
        app.start();
    }
}
