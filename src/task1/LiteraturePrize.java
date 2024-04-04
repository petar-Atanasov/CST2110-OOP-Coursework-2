package task1;

import java.util.ArrayList;

public class LiteraturePrize {

    private int year;
    private ArrayList<Laureate> winners = new ArrayList<Laureate>();

    public LiteraturePrize(int year) {
        this.year = year;
        
    }

    public void addWinners(Laureate winner){
       winners.add(winner);
    }
    
    public int getYear() {
        return year;
    }

    public ArrayList<Laureate> getWinners() {
        return winners;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nYear: ").append(year);
        sb.append("\nWinners: ").append(winners.toString());
        sb.append("\n");
        return sb.toString();
    }
}
