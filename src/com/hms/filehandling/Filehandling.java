package com.hms.filehandling;

import java.util.Map;
import com.hms.hotel.Hotel;
import com.hms.persons.Staff;
import com.hms.rooms.Room;

public class Filehandling {

    public void WriteToCSV(){
        for(Map.Entry<Integer,Room> entry : Hotel.rooms.entrySet()){

        }
        for(Map.Entry<Integer,Staff> entry : Hotel.personnel.entrySet()){
            
        }
    }

    public void readFromCSV(){

    }
}
