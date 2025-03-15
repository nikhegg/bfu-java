package Lab3.src.Cinema;

import java.util.ArrayList;

public class Hall {
    private ArrayList<Integer> rowSizes; // [10 мест, 5 мест, 12 мест] и т.д.

    public Hall(ArrayList<Integer> rowSizes) {
        this.rowSizes = rowSizes;
    }

    public Integer getRowsAmount() {
        return this.rowSizes.size();
    }

    public Integer getRowSeatsAmount(int row) {
        if(row > this.rowSizes.size()) return 0;
        return this.rowSizes.get(row);
    }
    
    public void printHallPlan() {
        for(int i = 0; i < this.rowSizes.size(); i++) {
            int seats = this.rowSizes.get(i);
            for(int j = 0; j < seats; j++) {
                System.out.print("+ ");
            }
            System.out.println();
        }
    }

    public int getCapacity() {
        int capacity = 0;
        for(int i = 0; i < this.rowSizes.size(); i++) {
            capacity += this.rowSizes.get(i);
        }
        return capacity;
    }
}
