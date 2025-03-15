package Lab3.src.Cinema;

import java.util.ArrayList;

public class CinemaManager {
    protected ArrayList<Cinema> cinemas;
    protected ArrayList<FilmSession> sessions;

    public CinemaManager() {
        this.cinemas = new ArrayList<Cinema>();
        this.sessions = new ArrayList<FilmSession>();
    }
    
    public Cinema findCinema(String cinemaName) {
        for(int i = 0; i < this.cinemas.size(); i++) {
            Cinema cinema = this.cinemas.get(i);
            if(cinemaName.equals(cinema.getName())) return cinema;
        }
        return null;
    }

    public Cinema createCinema(String name) {
        if(this.findCinema(name) != null) return null;
        Cinema cinema = new Cinema(name);
        this.cinemas.add(cinema);
        return cinema;
    }
    
    public ArrayList<Cinema> getCinemas() {
        return this.cinemas;
    }

    public ArrayList<FilmSession> getFilmSessions(String filmName) {
        ArrayList<FilmSession> sessions = new ArrayList<FilmSession>();
        for(int i = 0; i < this.sessions.size(); i++) {
            FilmSession filmSession = this.sessions.get(i);
            if(filmName.equals(filmSession.getFilmName())) sessions.add(filmSession);
        }
        return sessions;
    }

    public FilmSession addFilmSession(String filmName, Hall hall) throws Exception {
        ArrayList<FilmSession> filmSessions = this.getFilmSessions(filmName);
        for(int i = 0; i < filmSessions.size(); i++) {
            Hall sessionHall = filmSessions.get(i).getHall();
            if(hall.equals(sessionHall)) throw new Exception("Film already planned");
        }

        FilmSession session = new FilmSession(filmName, hall);
        this.sessions.add(session);
        return session;
    }
}
