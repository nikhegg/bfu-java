package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", false);
        this.setDescription("Closes the program");
    }

    public void execute(ConsoleApp app) {
        System.exit(0);
    }
}
