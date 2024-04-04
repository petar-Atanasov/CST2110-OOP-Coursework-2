package task1;

import java.util.ArrayList;
import task1.data.DataLoader;

public class ApplicationRunner {

    public static void main(String[] args) {
        DataLoader print = new DataLoader();
        print.start();
        
        ArrayList<LiteraturePrize> prizes = print.getPrize();
        
//        System.out.println("Pizes loaded: " + prizes.size() + " The prizes are " + print.getPrize());        
        printTheTable table = new printTheTable(prizes);
        table.start();

        for (LiteraturePrize prize : prizes) {
            System.out.println(String.format("\nYear: %d", prize.getYear()));
            ArrayList<Laureate> laureates = prize.getWinners();
            for (Laureate laureate : laureates) {
                System.out.println(String.format("Name: %s", laureate.getName()));
                System.out.println(String.format("Birth: %s", laureate.getBirth()));
                System.out.println(String.format("Death: %s", laureate.getDeath()));
                System.out.println(String.format("Nations: %s", laureate.getNations()));
                System.out.println(String.format("Languages: %s", laureate.getLanguages()));
                System.out.println(String.format("Genres: %s", laureate.getGenres()));
                System.out.println(String.format("Citations: %s", laureate.getCitation()));
//                System.out.println("\n-------------------------------------");
            } // end of for each for laureate
            System.out.println("");
        }// end for each literature prize
    }
}
