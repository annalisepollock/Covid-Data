/**
 * Stores the data for a single day of covid cases for a singe country
 */
public class Data implements Comparable<Data> {
    private String continent;
    private String country;
    private String date;
    private long total_cases;
    private long current_cases;
    private long pop;

    /**
     *Initializes an individual instance of the data cla
     * @param cnt: A String containing the continent
     * @param ctry: A String containing the country
     * @param d: A String containing the date
     * @param TC: An int containing the total cases
     * @param CC: An int containing the current new cases
     * @param p: An int containing the population
     * @throws: NA
     */
    public Data(String cnt, String ctry, String d, long TC, long CC, long p){
        //initialize data being passed in
        continent = cnt;
        country = ctry;
        date = d;
        total_cases = TC;
        current_cases = CC;
        pop = p;
    }

    /**
     * returns the continent to the user
     * @return continent: A String containing the continent name
     * @throws: NA
     */
    public String getContinent(){return continent;}

    /**
     * returns the country to the user
     * @return country: A String containing the country
     * @throws: NA
     */
    public String getCountry(){return country;}

    /**
     * return the date to the user
     * @return date: An int containing the date
     * @throws: NA
     */
    public String getDate(){return date;}

    /**
     * return the total covid cases to the user
     * @return total_cases: an int containing the total covid cases
     * @throws: NA
     */
    public long getTotal(){return total_cases;}

    /**
     * return the current new covid cases to the user
     * @return current_cases: an int containing the new covid cases for that day
     * @throws: NA
     */
    public long getCurrent(){return current_cases;};

    /**
     * return the country population to the user
     * @return pop: an int containing the country's population
     * @throws: NA
     */
    public long getPop(){return pop;}

    /**
     * Compare the current cases from the current data set and Data x, another object passed in
     * @throws: NA
     * @param x: An instance of the data class that will be compared with the current one
     * @return: An integer representing the comparison between the current object and x. Returns -1 if current is less than x, 0 if they are the same, and 1 if x is greater
     */
    @Override
    public int compareTo(Data x) {
        int output;
        //check if current data is smaller larger or equal to data being tested
        if(this.current_cases > x.current_cases){
            output = 1;
        }
        else if (this.current_cases == x.current_cases){
            output = 0;
        }
        else{
            output = -1;
        }
        return output;
    }
    //concatenate data elements into a formatted string
    public String toString(){
        return "New Cases: " + current_cases + " at " + country + "/" + continent + " on " + date + " Total: " + total_cases + " Pop: " + pop + "\n";
    }


}
