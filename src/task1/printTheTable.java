package task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import task1.LiteraturePrize;
import task1.Laureate;

public class printTheTable {

    private ArrayList<LiteraturePrize> prizes;

    public printTheTable(ArrayList<LiteraturePrize> prizes) {
        this.prizes = prizes;
    }

// start the program 
    public void start() {
        Scanner sc = new Scanner(System.in);

// get to into the loop to get the user's choice #prototype 
        while (true) {
            printTable();
            System.out.print("Enter choice > ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        listLaureate(sc);
                        break;
                    case 2:
                        selectLaureate(sc);
                        break;
                    case 3:
                        searchLaureate(sc);
                        break;
                    case 0:
                        System.out.println("Exiting.");
                        System.exit(0);
                        sc.close();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        start();
                        break;
                }
            } else if (!sc.equals("[0-9]")) {
                System.out.println("Invalid choice. Please try again.");
                sc.next();
            } else {
                System.out.println("Enter a number of your choice. ");
                sc.next();
            }

        }
    }

// create the method for the menu 
    public void printTable() {
        String menu = "\n----------------------\n"
                + "Literature prize menu\n"
                + "----------------------\n"
                + "List ................1\n"
                + "Select ..............2\n"
                + "Search ..............3\n"
                + "Exit ................0\n"
                + "----------------------\n";
        System.out.println(menu);
    }

    public void listLaureate(Scanner sc) {

        int startYear = 0;
        int endYear = 0;

        while (true) {
            System.out.print("Enter the start year > ");
            try {
                startYear = sc.nextInt();
                sc.nextLine();
                if (startYear >= 1901 && startYear <= 2022) {
                    break;
                } else {
                    System.out.println("That is not a valid year. Please enter a year between 1901 and 2022. ");
                }
            } catch (InputMismatchException err) {
                System.out.println("That is not a valid year. Please enter a valid integer year between 1901 and 2022: ");
                sc.next(); // if invalid input appear
            }
        }

        while (true) {
            System.out.print("Enter the end year > ");
            try {
                endYear = sc.nextInt();
                sc.nextLine();
                if (endYear >= 1901 && endYear <= 2022 && endYear >= startYear) {
                    break;
                } else {
                    System.out.println("That is not a valid year. Please enter a year between " + startYear + " and 2022. ");
                }
            } catch (InputMismatchException err) {
                System.out.println("That is not a valid year. Please enter a valid integer year between 1901 and 2022: ");
                sc.next(); // if is not integer 
            }
        }
        // printing the formatted details for the list option 
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-70s |\n", "Year", "Prize [and associated nations]");
        System.out.println("---------------------------------------------------------------------------------");
        for (LiteraturePrize prize : prizes) {
            int year = prize.getYear();
            if (year >= startYear && year <= endYear) {
                ArrayList<Laureate> laureates = prize.getWinners();
                if (!laureates.isEmpty()) {
                    System.out.format("| %-4d | %-70s |\n", year, laureates.get(0).getName() + " [" + laureates.get(0).getNations() + "]");
                    for (int i = 1; i < laureates.size(); i++) {
                        System.out.format("|      | %-70s |\n", laureates.get(i).getName() + " [" + laureates.get(i).getNations() + "]");
                    }// end of inner for loop 
                } else {
                    System.out.printf("| %-4d | %-70s |\n", year, "Not awarded");
                }
                System.out.println("---------------------------------------------------------------------------------");
            } // end if-else statement
        }// end of outter for each loop
        start();
    }

    public void selectLaureate(Scanner sc) {

        System.out.print("Enter the year of the prize > ");

        int yearInput = 0;

        try {
            yearInput = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException err) {
            System.out.println("That is not a valid year. Please enter a valid integer year. ");
            sc.next();
            return;
        }// end try-catch 

        //NOTE! Create a variable that holds a reference to LiteraturePrize
        LiteraturePrize selectedPrize = null;
        for (LiteraturePrize prize : prizes) {
            int year = prize.getYear();
            if (year == yearInput) {
                selectedPrize = prize;
                break;
            }// end of if 
        }// end of for each loop

        if (selectedPrize == null) {
            System.out.println("No prize found for the presented year " + yearInput);
            return;
        } // end if of checking the prize is empty 

        // printing the formatted details for the select option from the menu 
        System.out.println("\n----------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-24s | %-6s | %-6s | %-27s | %-27s |\n", "Winner(s)", "Born", "Died", "Language(s)", "Genre(s)");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        for (Laureate laureate : selectedPrize.getWinners()) {

            String died = laureate.getDeath().isEmpty() ? "----" : laureate.getDeath(); // missing implementation
            System.out.printf("| %-24s | %-6s | %-6s | %-27s | %-27s |\n", laureate.getName(), laureate.getBirth(), died, String.join(", ", laureate.getLanguages()), String.join(", ", laureate.getGenres()));
            System.out.println("----------------------------------------------------------------------------------------------------------");

            // call the printCitation method
            printCitation(laureate.getCitation());
        }// end outter for each
        start();
    }

    public void printCitation(String citation) {
        System.out.println("|                                                Citation:                                               |");

        int sizeOfTheLine = 102;

        if (citation.length() > sizeOfTheLine) {
            String remaining = citation;// the remaining of the present citation
            // if the remaining of the citaion is greater than the size of the line
            while (remaining.length() > sizeOfTheLine) {
                String line = remaining.substring(0, sizeOfTheLine);// substring the line with the same size 
                remaining = remaining.substring(sizeOfTheLine).trim();
                System.out.printf("| %-100s |%n", line);
            }// end inner while loop 
            // calculate padding for the last line
            int padding = (sizeOfTheLine - remaining.trim().length()) / 2;
            System.out.printf("| %" + (padding + remaining.trim().length()) + "s %" + padding + "s |%n", remaining.trim(), "");
        } else {
            System.out.printf("| %-100s |%n", citation.trim());
        }// end if-else
        System.out.println("----------------------------------------------------------------------------------------------------------");

    }

    public void searchLaureate(Scanner sc) {
        String searchGenres = "";
        boolean validInput = false;// set a flag 

        while (!validInput) {
            System.out.print("Enter search term for writing genre > ");
            try {
                searchGenres = sc.nextLine().trim().toLowerCase();

                if (searchGenres.isEmpty() || !searchGenres.matches("[a-zA-Z ]+")) {
                    System.out.println("Error: Invalid input. Please try again.");
                } else {
                    validInput = true;
                }
            } catch (NoSuchElementException err) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        final String search = searchGenres;
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-70s | %-5s |\n", "Name", "Genres", "Year");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        boolean match = false;
        // print the list with the prizes using lambda expression and stream interface 
        for (LiteraturePrize prize : prizes) {
            for (Laureate laureate : prize.getWinners()) {
                List<String> hightlightedGenres = laureate.getGenres().stream()
                        .map(genre -> genre.toLowerCase().contains(search) ? genre.toUpperCase() : genre)
                        .collect(Collectors.toList());
                if (hightlightedGenres.stream().anyMatch(genre -> genre.toUpperCase().contains(search.toUpperCase()))) {
                    System.out.printf("| %-30s | %-70s | %-5s |\n",
                            laureate.getName(),
                            String.join(",", hightlightedGenres),
                            prize.getYear());
                    System.out.println("-------------------------------------------------------------------------------------------------------------------");
                    match = true;
                }
            }// end inner for each
        }// end outter for each

        if (!match) {
            System.out.println("The genre " + search + " does not exist. Try again!");
        }
        start();
    }
}
