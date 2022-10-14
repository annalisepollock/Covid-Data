import java.io.*;
import java.util.ArrayList;

/**
 * Initialize a Minimum Priority Queue and Binary Search Tree to store data read in from a file
 */
public class Driver{

    /**
     * Reads through covid cases by country and day in a file, adds data from each country into a minimum priority queue by calling data class and the min pq class
     * Takes three largest values within the queue and adds to binary search tree by calling binarysearchtree class
     * Scans through BST from smallest to largest calling write in order method, writing data into a new file
     * @param args
     * @return: NA
     * @throws: FileNotFoundException, in case file cannot be found
     */
    public static MinPQ country = new MinPQ(3);
    public static BinarySearchTree covid_data;

    //initialize binary search tree passing in file name. Try catch to ensure the file name is not null
    static {
        try {
            covid_data = new BinarySearchTree("232Program1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //read in input file
        BufferedReader reader = new BufferedReader(new FileReader("/Users/annalise/Desktop/owid-covid-data.csv"));
        reader.readLine();

        //assign first line of data to the appropriate variables
        String Line = reader.readLine();
        String [] line = Line.split(",");
        String cont = line[0];
        String ctry = line[1];
        String date = line[2];
        long total = Long.parseLong(line[3]);
        long new_cases = Long.parseLong(line[4]);
        long pop = Long.parseLong(line[5]);
        int counter = 0;

        //initialize strings to keep track of the current and previous countries to check when a new country has been reached
        String prev_name = ctry;
        String current_name = ctry;

        while(Line != null){
            //do this every time except the first time through the loop
            //assign data variables to data from next line
            if(counter != 0){
                line = Line.split(",");
                cont = line[0];
                ctry = line[1];
                date = line[2];
                total = Long.parseLong(line[3]);
                new_cases = Long.parseLong(line[4]);
                pop = Long.parseLong(line[5]);
                current_name = ctry; //mark current country as the one about to be enqueued
            }
            //if the current name and previous name are not equal, add minPQ to BST and clear
            if(!prev_name.equals(current_name)){
                addToTree(country);
                country.clear();
            }
            //create new instance of Data class and enqueue
            country.enqueue(new Data(cont, ctry, date, total, new_cases, pop));
            counter ++;
            prev_name = ctry; //mark previous name as country that just got enqueued
            Line = reader.readLine(); //move to next line

        }
        //add last country to queue
        addToTree(country);
        //write to file
        covid_data.writeInOrder();
    }
    //Add all the data items in MINPQ to BST
    public static void addToTree(MinPQ country){
        //create list of queue items
        ArrayList<Data> country_info = country.getAll();
        //loop through array size and add list item to BST
        for(int i = 0; i < country.size(); i++){
            covid_data.put(country_info.get(i), 0);
        }
    }
}
