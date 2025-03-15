package Lab3.src.Commands;

import Lab3.src.ConsoleApp;

public class CreateCinemaCommand extends AbstractCommand {
    public CreateCinemaCommand() {
        super("create_cinema", true);
        this.setDescription("Creates a new cinema in system");
    }

    public void execute(ConsoleApp app) {

    }
}
