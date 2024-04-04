package task1.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import task1.LiteraturePrize;
import task1.Laureate;

public class DataLoader {

    // pass an array list from LaureatePrize to present the prize
    private ArrayList<LiteraturePrize> prize = new ArrayList<LiteraturePrize>();
    private String fileLocation = System.getProperty("user.dir");
    private String dataPath = fileLocation + File.separator + "literature-prizes.txt";

    public void start() {
        try {
            File myObj = new File(dataPath);
            Scanner lineReader = new Scanner(myObj);

            //NOTE! Create a variable that holds a reference to LiteraturePrize
            LiteraturePrize currentPrize = null;
            // create a loop for reading the data from the file 
            while (lineReader.hasNextLine()) {
                String dataReader = lineReader.nextLine().trim();
                if (dataReader.isEmpty()) {
                    continue;
                } //checking for year regex
                else if (dataReader.matches("\\d{4}")) {
                    if (currentPrize != null) {
                        prize.add(currentPrize); // adds the prize to the list
                    }
                    // parse the year and get a new literature prize
                    int year = Integer.parseInt(dataReader);
                    currentPrize = new LiteraturePrize(year);

                    //if there is still data to prompt which is no delimiter
                } else if (dataReader.equals("-----")) {
                    // after that add the literature prize to the list and reset the prize 
                    if (currentPrize != null) {
                        prize.add(currentPrize);
                        currentPrize = null;
                    }
                } else {
                    // separate the laureate parts 
                    if (currentPrize != null) {
                        String[] parts = dataReader.split("\\|");
                        // assume that the laureate parts are only three and parse each of it 
                        if (parts.length < 3) {
                            continue;
                        }
                        // splitter is to split the name with birth and death which had "(" ")" within
                        String[] splitter = parts[0].split("\\(");
                        // get the name 
                        String name = splitter[0].trim();

                        String birth = null;
                        String death = "----"; // this is set to be displayed if a person is alive 

                        try {
                            if (splitter.length > 1) {
                                String yearsSeparator = splitter[1].replace(")", "").trim();
                                String[] years = yearsSeparator.split("-");
                                birth = years[0].trim().replace("b. ", "");
                                if (years.length > 1 && !years[1].trim().isEmpty()){
                                    death = years[1].trim();
                                }
                            } else {
                                System.out.println("The birth and death information is missing for " + name);
                            }
                        } catch (ArrayIndexOutOfBoundsException err) {
                            System.out.println("Error: The line does not containts enought elements. " + err);
                        }

                        String nations = parts[1].trim();

                        List<String> languages = null;
                        try {
                            languages = Arrays.asList(parts[2].trim());
                        } catch (ArrayIndexOutOfBoundsException err) {
                            System.out.println("Error: The line does not contains enought elements. " + err);
                        }

                        String citation;
                        if (!lineReader.hasNextLine()) {
                            citation = "";
                        } else {
                            citation = lineReader.nextLine().trim();
                        }

                        String hasGenres;
                        if (lineReader.hasNextLine()) {
                            hasGenres = lineReader.nextLine().trim();
                        } else {
                            hasGenres = "";
                        }

                        List<String> genres;
                        if (!hasGenres.isEmpty()) {
                            // add the genres to the arraylist and split them
                            genres = new ArrayList<>(Arrays.asList(hasGenres.split(",\\s*")));
                        } else {
                            genres = Collections.emptyList();
                        }
                        //add a laureate obj and parse each obj details
                        Laureate laureate = new Laureate(name, birth, death, nations, languages, genres, citation);
                        // add the laureate to the literature prize 
                        currentPrize.addWinners(laureate);
                    }//end of if for laureate parts 
                } // end of else for laureate 
            }// end while 
            lineReader.close();

        } catch (FileNotFoundException err) {
            System.out.println("An error occured. " + err);
            err.printStackTrace();
        }
    }

    public ArrayList<LiteraturePrize> getPrize() {
        return prize;
    }

}
