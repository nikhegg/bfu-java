package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public class ACommandExample extends AbstractCommand {
    public ACommandExample() {
        super("example", false);
        this.setDescription("Closes the program");
    }

    public void execute(ConsoleApp app) {

    }
}
