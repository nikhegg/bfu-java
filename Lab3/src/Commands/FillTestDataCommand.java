package Lab3.src.Commands;

import java.util.ArrayList;
import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;

public class FillTestDataCommand extends AbstractCommand {
    public FillTestDataCommand() {
        super("test_fill", true);
        this.setDescription("Creates testing cinemas with halls & film sessions");
    }

    public void execute(ConsoleApp app) {
        System.out.println("Adding test data to system...");

        CinemaManager manager = app.getCinemaManager();

        Cinema zarya = manager.createCinema("Zarya");
        ArrayList<Integer> zaryaRowsOne = new ArrayList<Integer>();
        for(int i = 0; i < 3; i++) {
            zaryaRowsOne.add(5);
        }
        zarya.createHall(zaryaRowsOne);
        ArrayList<Integer> zaryaRowsTwo = new ArrayList<Integer>();
        zaryaRowsTwo.add(10);
        zaryaRowsTwo.add(9);
        zaryaRowsTwo.add(10);
        zaryaRowsTwo.add(9);
        zaryaRowsTwo.add(10);
        zarya.createHall(zaryaRowsTwo);
        
        Cinema plaza = manager.createCinema("Plaza");
        ArrayList<Integer> plazaRows = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++) {
            plazaRows.add(10);
        }
        plaza.createHall(plazaRows);
        
        System.out.println("Done!");
    }
}
