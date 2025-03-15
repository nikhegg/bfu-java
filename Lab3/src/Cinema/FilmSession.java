package Lab3.src.Cinema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FilmSession {
    private Film film;
    private Date date;
    private Hall hall;
    private long id;
    private ArrayList<Boolean[]> seats;

    public FilmSession(Film film, Date date, Hall hall) {
        this.film = film;
        this.date = date;
        this.hall = hall;

        // Copy seats plan to 2D array
        this.seats = new ArrayList<Boolean[]>();
        int rows = this.hall.getRowsAmount();
        for(int i = 0; i < rows; i++) {
            int seatsCount = this.hall.getRowSeatsAmount(i);
            Boolean[] seats = new Boolean[seatsCount];
            Arrays.fill(seats, false);
            this.seats.add(seats);
        }
    }

    public long getID() {
        return this.id;
    }
    public void setID(long id) {
        this.id = id;
    }
    
    public Film getFilm() {
        return this.film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getFilmName() {
        return this.film.getName();
    }

    public Date getStartDate() {
        return this.date;
    }

    public Date getEndDate() {
        long timestamp = this.date.getTime();
        long filmMilliDuration = this.film.getDurationMillis(); // *60 secs *1000 millis
        return new Date(timestamp + filmMilliDuration);
    }

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Cinema getCinema() {
        return this.hall.getCinema();
    }

    private void switchSeatState(int row, int index, boolean state) throws Exception {
        if(row >= this.seats.size()) throw new Exception("The hall has only " + this.seats.size() + " rows");
        Boolean[] rowSeats = this.seats.get(row);
        if(index >= rowSeats.length) throw new Exception("The hall's row " + row + " has only " + rowSeats.length + " seats");
        if(rowSeats[index]) throw new Exception("This seat is already taken");
        this.seats.get(row)[index] = state;
    }
    public void claimSeat(int row, int index) throws Exception {
        try {
            this.switchSeatState(row, index, true);
        } catch (Exception e) {
            throw e;
        }
    }
    public void unclaimSeat(int row, int index) throws Exception {
        try {
            this.switchSeatState(row, index, false);
        } catch (Exception e) {
            throw e;
        }
    }

    public int seatsLeft() {
        int seatsLeft = 0;
        for(int i = 0; i < this.seats.size(); i++) {
            Boolean[] rowSeats = this.seats.get(i);
            for(int j = 0; j < rowSeats.length; j++) {
                if(!rowSeats[j]) seatsLeft++;
            }
        }
        return seatsLeft;
    }

    public void printSeatsPlan() {
        for(int i = 0; i < this.seats.size(); i++) {
            Boolean[] rowSeats = this.seats.get(i);
            for(int j = 0; j < rowSeats.length; j++) {
                System.out.print(rowSeats[j] ? "X " : "O ");
            }
            System.out.println();
        }
    }
}
