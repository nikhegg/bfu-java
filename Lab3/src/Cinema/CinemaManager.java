package Lab3.src.Cinema;

import java.util.ArrayList;
import java.util.Date;

public class CinemaManager {
    protected ArrayList<Cinema> cinemas;
    protected ArrayList<FilmSession> sessions;
    protected ArrayList<Film> films;

    public CinemaManager() {
        this.cinemas = new ArrayList<Cinema>();
        this.sessions = new ArrayList<FilmSession>();
        this.films = new ArrayList<Film>();
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
    public ArrayList<FilmSession> getFilmSessions(Hall hall) {
        ArrayList<FilmSession> sessions = new ArrayList<FilmSession>();
        for(int i = 0; i < this.sessions.size(); i++) {
            FilmSession filmSession = this.sessions.get(i);
            if(hall.equals(filmSession.getHall())) sessions.add(filmSession);
        }
        return sessions;
    }

    public FilmSession addFilmSession(Film film, Date date, Hall hall) throws Exception {
        FilmSession session = new FilmSession(film, date, hall);
        long newStart = session.getStartDate().getTime();
        long newEnd = session.getEndDate().getTime();

        ArrayList<FilmSession> filmSessions = this.getFilmSessions(hall);
        for(int i = 0; i < filmSessions.size(); i++) {
            FilmSession curSession = filmSessions.get(i);
            if(hall.equals(curSession.getHall())) {
                // Check for time intersections
                long curStart = curSession.getStartDate().getTime();
                long curEnd = curSession.getEndDate().getTime();
                if(curStart > newEnd) continue;
                if(curEnd < newStart) continue;
                // So now we have a situation when sessions intersect
                throw new Exception("Provided session intersects with another film session with ID " + curSession.getID());
            }
        }

        this.sessions.add(session);
        session.setID(this.sessions.size());
        return session;
    }

    public Film findFilm(String filmName) {
        for(int i = 0; i < this.films.size(); i++) {
            Film film = this.films.get(i);
            if(filmName.equals(film.getName())) return film;
        }
        return null;
    }

    public ArrayList<Film> getFilms() {
        return this.films;
    }

    public Film registerFilm(String filmName, int durationIsMins) {
        if(findFilm(filmName) != null) return null;
        Film film = new Film(filmName, durationIsMins);
        this.films.add(film);
        return film;
    }
}
