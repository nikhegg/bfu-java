package Lab3.src.Commands;

import java.util.ArrayList;
import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;

public class CreateHallCommand extends AbstractCommand {
    public CreateHallCommand() {
        super("add_hall", true);
        this.setDescription("Adds a hall to an existing cinema");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
        if(manager.getCinemas().size() == 0) {
            System.out.println("No cinemas are stored now. Create a new one with create_cinema command!");
            return;
        }

        System.out.println("What is the name of your cinema?");
        String name = app.input();
        Cinema cinema = manager.findCinema(name);
        if(cinema == null) {
            System.out.println("No cinema with name " + name + " found. Create one or try again");
            return;
        }

        System.out.println("Found " + cinema.getName() + " cinema. How many rows are in new hall?");
        int rowCount = app.inputInt();
        if(rowCount <= 0) {
            System.out.println("Hall should have at least 1 row!");
            return;
        }

        ArrayList<Integer> rows = new ArrayList<Integer>();
        for(int i = 0; i < rowCount; i++) {
            System.out.println("How many seats are in row " + (i+1) + "?");
            int seatsCount = app.inputInt();
            if(seatsCount <= 0) {
                System.out.println("Row should have at least 1 seat!");
                i -= 1;
                continue;
            }
            rows.add(seatsCount);
        }
        
        cinema.createHall(rows);
        System.out.println("Successfully added new hall!");
    }
}
