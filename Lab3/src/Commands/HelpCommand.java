package Lab3.src.Commands;

import java.util.ArrayList;
import Lab3.src.ConsoleApp;

public class HelpCommand extends AbstractCommand {
    public HelpCommand() {
        super("help", false);
        this.setDescription("Prints all available commands");
    }

    public void execute(ConsoleApp app) {
        ArrayList<AbstractCommand> cmds = app.getCommandHandler().getCommands();
        System.out.println("List of available commands:");
        for(int i = 0; i < cmds.size(); i++) {
            AbstractCommand cmd = cmds.get(i);
            if(!app.isLoggedAsAdmin() && cmd.isAdminOnly()) continue;
            System.out.print("\t- " + cmd.getName());
            if(cmd.isAdminOnly()) System.out.println(" [Admin only]");
            else System.out.println();
            System.out.println("\t" + cmd.getDescription());
        }
    }
}
