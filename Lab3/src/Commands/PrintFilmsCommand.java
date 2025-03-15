package Lab3.src.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.Film;
import Lab3.src.Cinema.FilmSession;

public class PrintFilmsCommand extends AbstractCommand {
    public PrintFilmsCommand() {
        super("films", false);
        this.setDescription("Shows the list of films and their sessions");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
        ArrayList<Film> films = manager.getFilms();
        if(films.size() == 0) {
            System.out.println("No films info added now :(");
            return;
        }
        films.forEach((film) -> {
            System.out.println("Name: " + film.getName());
            System.out.println("Duration: " + film.getDuration() + "min.");
            System.out.print("Sessions:");
            ArrayList<FilmSession> sessions = manager.getFilmSessions(film.getName());
            System.out.println(sessions.size() == 0 ? " -" : "");
            int filmCount = 1;
            for(int i = 0; i < sessions.size(); i++) {
                FilmSession session = sessions.get(i);
                if(session.seatsLeft() == 0) continue;
                System.out.println("\t" + (filmCount) + ". At " + session.getCinema().getName() + " cinema (Hall " + (session.getHall().getID() + 1) + ")");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                System.out.println("\tDate and time: " + format.format(session.getStartDate()));
                System.out.println("\tSeats left: " + session.seatsLeft());
                System.out.println();
                filmCount++;
            }
        });
    }
}
