package Lab3.src.Cinema;

import java.util.ArrayList;

public class CinemaManager {
    protected ArrayList<Cinema> cinemas;

    public CinemaManager() {
        this.cinemas = new ArrayList<Cinema>();
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
}
