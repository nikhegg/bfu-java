package Lab3.src.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.Film;
import Lab3.src.Cinema.FilmSession;

public class BuyTicketCommand extends AbstractCommand {
    public BuyTicketCommand() {
        super("buy", false);
        this.setDescription("Command to buy a ticket for a film session");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
        
        ArrayList<Cinema> cinemas = manager.getCinemas();
        if(cinemas.size() == 0) {
            System.out.println("No cinemas are available now");
            return;
        }
        ArrayList<Film> films = manager.getFilms();
        if(films.size() == 0) {
            System.out.println("No films are available now");
            return;
        }

        System.out.println("In which film are you interested?");
        StringBuilder titles = new StringBuilder();
        titles.append("Available titles: ");
        for(int i = 0; i < films.size(); i++) {
            titles.append(films.get(i).getName());
            if(i != films.size() - 1) titles.append(", ");
        }
        System.out.println(titles.toString());
        String filmName = app.input();
        Film film = manager.findFilm(filmName);
        if(film == null) {
            System.out.println("No film with name " + filmName + " found. Try again");
            return;
        }
        ArrayList<FilmSession> sessions = manager.getFilmSessions(filmName);
        if(sessions.size() == 0) {
            System.out.println("No sessions for film " + filmName + " found");
            return;
        }
        System.out.println("Available sessions:");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for(int i = 0; i < sessions.size(); i++) {
            FilmSession session = sessions.get(i);
            System.out.println("\tSession " + (i+1));
            System.out.println("\tHall " + (session.getHall().getID() + 1) + " at " + session.getHall().getCinema().getName() + " cinema");
            System.out.println("\tFilm starts " + format.format(session.getStartDate()));
            System.out.println("\tFilm ends " + format.format(session.getEndDate()));
            System.out.println("\tSeats left: " + session.seatsLeft());
            System.out.println("\tHall plan (X - booked, O - available):");
            session.printSeatsPlan();
            System.out.println();
        }

        System.out.println("Write session number to buy a ticket");
        int sID = app.inputInt() - 1;
        if(sID >= sessions.size() || sID < 0) {
            System.out.println("No session with this number");
            return;
        }
        System.out.println("Type row that you want to sit in");
        FilmSession session = sessions.get(sID);
        int row = app.inputInt() - 1;
        int hallRows = session.getHall().getRowsAmount();
        if(row >= hallRows) {
            System.out.println("This hall has only " + hallRows);
        }
        int seats = session.getHall().getRowSeatsAmount(row);
        System.out.println("Type seat number (from 1 to " + seats + ")");
        int selectedSeat = app.inputInt() - 1;
        if(selectedSeat < 0 || selectedSeat >= seats) {
            System.out.println("This row has only " + seats + " seats");
            return;
        }
        try {
            session.claimSeat(row, selectedSeat);
            System.out.println("Successfully booked " + (selectedSeat+1) + " in row #" + (row+1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
