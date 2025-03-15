package Lab3.src;

import java.util.ArrayList;

import Lab3.src.Commands.AbstractCommand;
import Lab3.src.Commands.CreateCinemaCommand;
import Lab3.src.Commands.ExitCommand;
import Lab3.src.Commands.HelpCommand;
import Lab3.src.Commands.LogoutCommand;
import Lab3.src.Commands.StatusCommand;

public class CommandHandler {
    protected ConsoleApp app;
    private ArrayList<AbstractCommand> commands;

    public CommandHandler(ConsoleApp app) {
        this.commands = new ArrayList<AbstractCommand>();
        this.app = app;

        this.addCommand(new CreateCinemaCommand());
        this.addCommand(new HelpCommand());
        this.addCommand(new StatusCommand());
        this.addCommand(new LogoutCommand());
        this.addCommand(new ExitCommand());
    }

    private void addCommand(AbstractCommand command) {
        this.commands.add(command);
    }

    public ArrayList<AbstractCommand> getCommands() {
        return this.commands;
    }

    public AbstractCommand findCommand(String commandName) throws Exception {
        for(int i = 0; i < this.commands.size(); i++) {
            AbstractCommand cmd = this.commands.get(i);
            if(commandName.equals(cmd.getName())) return cmd;
        }
        throw new Exception("Command with name " + commandName + " not found");
    }

    public void execute(String commandName) throws Exception {
        if(!this.app.isLogged()) return; // Impossible thing 0_0
        try {
            AbstractCommand cmd = this.findCommand(commandName);
            if(cmd.isAdminOnly() && !this.app.isLoggedAsAdmin()) {
                System.out.println("You do not have access to this command");
                return;
            }
            cmd.execute(app);
        } catch (Exception e) {
            throw e;
        }
    }
}
