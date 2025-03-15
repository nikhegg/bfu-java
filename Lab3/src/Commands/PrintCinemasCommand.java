package Lab3.src.Commands;

import java.util.ArrayList;
import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.Hall;

public class PrintCinemasCommand extends AbstractCommand {
    public PrintCinemasCommand() {
        super("cinemas", true);
        this.setDescription("Shows information about all cinemas");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
        ArrayList<Cinema> cinemas = manager.getCinemas();
        if(cinemas.size() == 0) {
            System.out.println("No cinemas added now. Add them using create_cinema command!");
            return;
        }
        System.out.println("All cinemas information:");
        for(int i = 0; i < cinemas.size(); i++) {
            Cinema cinema = cinemas.get(i);
            ArrayList<Hall> halls = cinema.getHalls();
            System.out.println("\nCinema name: " + cinema.getName());
            System.out.println("Halls total: " + halls.size());
            for(int j = 0; j < halls.size(); j++) {
                Hall hall = halls.get(j);
                System.out.println("Hall " + (j+1) + " plan (total "+ hall.getCapacity() + " seats):");
                hall.printHallPlan();
            }
        }
    }
}
