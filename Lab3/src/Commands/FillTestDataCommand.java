package Lab3.src.Commands;

import java.util.ArrayList;
import java.util.Date;

import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.Film;
import Lab3.src.Cinema.FilmSession;
import Lab3.src.Cinema.Hall;

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
        Hall zaryaOne = zarya.createHall(zaryaRowsOne);
        ArrayList<Integer> zaryaRowsTwo = new ArrayList<Integer>();
        zaryaRowsTwo.add(10);
        zaryaRowsTwo.add(9);
        zaryaRowsTwo.add(10);
        zaryaRowsTwo.add(9);
        zaryaRowsTwo.add(10);
        Hall zaryaTwo = zarya.createHall(zaryaRowsTwo);
        
        Cinema plaza = manager.createCinema("Plaza");
        ArrayList<Integer> plazaRows = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++) {
            plazaRows.add(10);
        }
        Hall plazaHall = plaza.createHall(plazaRows);
        
        Film shrek = manager.registerFilm("Shrek", 89);
        Film teot = manager.registerFilm("The Edge of Tomorrow", 113);

        try {
            FilmSession session = manager.addFilmSession(shrek, new Date(), plazaHall);
            session.claimSeat(9, 4);
            session.claimSeat(9, 5);
            session.claimSeat(5, 2);
            session.claimSeat(2,9);
            session.claimSeat(7, 6);
            session.claimSeat(1, 1);
        } catch (Exception e) {
            System.out.println("[!] Failed to add Shrek film");
        }

        try {
            FilmSession teotSession = manager.addFilmSession(teot, new Date(), zaryaTwo);
            teotSession.claimSeat(4, 7);
        } catch (Exception e) {
            System.out.println("[!] Failed to add TEoT film @ Zarya-2");
        }

        try {
            FilmSession shrekZarya = manager.addFilmSession(shrek, new Date(), zaryaOne);
            for(int i = 0; i < zaryaOne.getRowSeatsAmount(0); i++) {
                shrekZarya.claimSeat(0, i);
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to add Shrek film @ Zarya-1");
        }
        
        try {
            FilmSession bugSession = manager.addFilmSession(shrek, new Date(), plazaHall);
            bugSession.claimSeat(0, 0);
        } catch (Exception e) {
            System.out.println("[!] Successfully failed to add second Shrek film - it intersects with the first session");
        }

        System.out.println("Done!");
    }
}
