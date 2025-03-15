package Lab3.src.Cinema;

import java.util.ArrayList;

public class Cinema {
    private String name;
    private ArrayList<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<Hall>();
    }

    public void createHall(ArrayList<Integer> rows) {
        this.halls.add(new Hall(this, rows));
    }

    public ArrayList<Hall> getHalls() {
        return this.halls;
    }
    
    public String getName() {
        return this.name;
    }
}
