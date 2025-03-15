package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public class StatusCommand extends AbstractCommand {
    public StatusCommand() {
        super("Status", false);
        this.setDescription("Shows if you are an admin");
    }

    public void execute(ConsoleApp app) {
        if(app.isLoggedAsAdmin()) System.out.println("You are an admin");
        else System.out.println("You are NOT an admin");
    }
}
