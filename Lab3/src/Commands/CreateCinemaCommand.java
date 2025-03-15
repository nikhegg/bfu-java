package Lab3.src.Commands;

import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.CinemaManager;

public class CreateCinemaCommand extends AbstractCommand {
    public CreateCinemaCommand() {
        super("create_cinema", true);
        this.setDescription("Creates a new cinema in system");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();

        System.out.println("What is the name of your cinema?");
        String name = app.input();

        manager.createCinema(name);
        System.out.println("Created " + name + " cinema");
        System.out.println("Willing to add halls? Use add_hall command and write " + name);
        System.out.println("You can also view all your cinemas using cinemas command");
    }
}
