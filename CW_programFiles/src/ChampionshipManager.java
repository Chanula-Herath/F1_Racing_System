import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public interface ChampionshipManager {

    //Add driver method
    void addNewDriver(ArrayList<Formula1Driver> driverList);

    //Method to delete a driver and the team
    void removeDriver(ArrayList<Formula1Driver> driverList);

    //Method to change a driver from a team
    void changeDriver(ArrayList<Formula1Driver> driverList);

    //Method to display a driver's statistics
    void displayStats(ArrayList<Formula1Driver> driverList);

    //Display formula 1 diver table
    void formula1DriverTable(ArrayList<Formula1Driver> driverList);

    //Method to add a race
    void addRace(ArrayList<Formula1Driver> driverList) throws ParseException;

    //Method to save data into a file
    void saveFile(ArrayList<Formula1Driver> driverList) throws IOException;

    //Method to recover the saved data
    void recoverFile(ArrayList<Formula1Driver> driverList) throws IOException, ClassNotFoundException;

}
