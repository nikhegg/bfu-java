package Lab3.src.Cinema;

import java.util.ArrayList;
import java.util.Arrays;

public class FilmSession {
    private String filmName;
    private Hall hall;
    private ArrayList<Boolean[]> seats;

    public FilmSession(String filmName, Hall hall) {
        this.filmName = filmName;
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
    
    public String getFilmName() {
        return this.filmName;
    }

    public Hall getHall() {
        return this.hall;
    }

    public Cinema getCinema() {
        return this.hall.getCinema();
    }

    public void printSeatsPlan() {
        for(int i = 0; i < this.seats.size(); i++) {
            Boolean[] rowSeats = this.seats.get(i);
            for(int j = 0; j < rowSeats.length; j++) {
                if(rowSeats[j]) System.out.print("X ");
                else System.out.print("O ");
            }
            System.out.println();
        }
    }
}
