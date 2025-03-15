package Lab3.src.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Lab3.src.ConsoleApp;
import Lab3.src.Cinema.Cinema;
import Lab3.src.Cinema.CinemaManager;
import Lab3.src.Cinema.Film;
import Lab3.src.Cinema.FilmSession;
import Lab3.src.Cinema.Hall;

public class CreateSessionCommand extends AbstractCommand {
    public CreateSessionCommand() {
        super("create_session", true);
        this.setDescription("Creates a new film session");
    }

    public void execute(ConsoleApp app) {
        CinemaManager manager = app.getCinemaManager();
    
        ArrayList<Cinema> cinemas = manager.getCinemas();
        if(cinemas.size() == 0) {
            System.out.println("No cinemas are stored now. Create a new one with create_cinema command!");
            return;
        }
        ArrayList<Film> films = manager.getFilms();
        if(films.size() == 0) {
            System.out.println("No films are stored now. Create a new one with add_film command!");
            return;
        }
        // Film selection
        System.out.println("Select a film title to show");
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
            System.out.println("No film with name " + filmName + " found. Add one or try again");
            return;
        }

        // Time selection
        System.out.println("Type a date when the film will be shown (Using dd.MM.yyyy HH:mm format)");
        String dateStr = app.input();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date startDate;
        try {
            startDate = format.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Incorrect date. Choose another date and try again");
            return;
        }
        long startTimestamp = startDate.getTime();
        Date endDate = new Date(startTimestamp + film.getDurationMillis());

        // Cinema selection
        System.out.println("Type a cinema name where the film session will be held");
        StringBuilder cinemasList = new StringBuilder();
        cinemasList.append("Available cinemas: ");
        for(int i = 0; i < cinemas.size(); i++) {
            Cinema cinema = cinemas.get(i);
            cinemasList.append(cinema.getName());
            if(i != cinemas.size() - 1) cinemasList.append(", ");
        }
        System.out.println(cinemasList.toString());

        String cinemaName = app.input();
        Cinema cinema = manager.findCinema(cinemaName);
        if(cinema == null) {
            System.out.println("No cinema with name " + cinemaName + " found. Create one or try again");
            return;
        }

        // Hall selection
        ArrayList<Hall> unfilteredHalls = cinema.getHalls();
        if(unfilteredHalls.size() == 0) {
            System.out.println("No halls in selected cinema are stored now. Create a new one with add_hall command!");
        }
        // Filter halls
        ArrayList<Hall> halls = new ArrayList<Hall>();
        for(int i = 0; i < unfilteredHalls.size(); i++) {
            Hall curHall = unfilteredHalls.get(i);
            ArrayList<FilmSession> curSessions = manager.getFilmSessions(curHall);
            boolean intersects = false;
            for(int j = 0; j < curSessions.size(); j++) {
                FilmSession curSession = curSessions.get(i);
                long curStart = curSession.getStartDate().getTime();
                long curEnd = curSession.getEndDate().getTime();
                if(curStart > endDate.getTime()) continue;
                if(curEnd < startTimestamp) continue;
                intersects = true;
            }
            if(intersects) continue;
            halls.add(curHall);
        }
        System.out.println("Select a hall where the film will be shown\nAvailable halls:");
        for(int i = 0; i < halls.size(); i++) {
            Hall curHall = halls.get(i);
            System.out.println((i+1) + ". Hall with " + curHall.getCapacity() + " seats");
            System.out.println("Hall plan:");
            curHall.printHallPlan();
        }
        System.out.println("Type a hall number where the film session will be held");
        int hallIndex = app.inputInt() - 1;
        if(hallIndex > halls.size()) {
            System.out.println("There is no hall with a hall number " + hallIndex);
            return;
        }
        Hall hall = halls.get(hallIndex);

        try {
            manager.addFilmSession(film, startDate, hall);
            System.out.println("Succesfully added new film session!");
        } catch (Exception e) {
            System.out.println("Something went wrong during film session creation!");
        }
    }
}
