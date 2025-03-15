package Lab3.src.Cinema;

import java.util.ArrayList;

public class Cinema {
    private String name;
    private ArrayList<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<Hall>();
    }

    public Hall createHall(ArrayList<Integer> rows) {
        Hall hall = new Hall(this.halls.size(), this, rows);
        this.halls.add(hall);
        return hall;
    }

    public ArrayList<Hall> getHalls() {
        return this.halls;
    }
    
    public String getName() {
        return this.name;
    }
}
