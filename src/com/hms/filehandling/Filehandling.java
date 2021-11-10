package com.hms.filehandling;

import java.util.Map;
import com.hms.Hotel;
import com.hms.persons.Guest;
import com.hms.persons.Staff;
import com.hms.rooms.Room;
/**
 * Work In Progress , Do not merge yet.
 */
public class Filehandling {
    /**
     * Function to overwrite CSV files with data stored in Maps.
     */
    public void WriteToCSV(){
        for(Map.Entry<Integer,Room> entry : Hotel.roomsList.entrySet()){

        }
        for(Map.Entry<Integer,Staff> entry : Hotel.staffList.entrySet()){
            
        }
        for(Map.Entry<Integer,Guest> entry : Hotel.guestsList.entrySet()){
            
        }
    }
    /**
     * Function to overwrite data into the Maps from CSV files.
     */
    public void readFromCSV(){
        Hotel.roomsList.clear();
        Hotel.guestsList.clear();
        Hotel.staffList.clear();
    }
}
