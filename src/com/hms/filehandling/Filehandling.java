package com.hms.filehandling;

import java.io.FileWriter;
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
    public static void WriteToCSV(){
         //Rooms
        for(Map.Entry<Integer,Room> entry : Hotel.roomsList.entrySet()){
            Room _room = entry.getValue();

            try(FileWriter writer = new FileWriter("resources/rooms.csv")){
            /**
             * Structure of CSV
             * S.No. | roomNumber | capacity | desc | roomSize | available
             *       |            |          |      |          |          
             */
            StringBuilder s = new StringBuilder();
            s.append(entry.getKey().toString()+',');
            s.append(_room.getRoomNumber().toString()+',');
            s.append(_room.getCapacity().toString()+',');
            s.append(_room.getDesc().toString()+',');
            s.append(_room.getRoomSize().toString()+',');
            
            
            if(_room.isAvailable()){
                s.append('Y');
            }
            else s.append('N');

            s.append('\n');
    
            writer.write(s.toString());
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
          }
        }

        //Staff
        for(Map.Entry<Integer,Staff> entry : Hotel.staffList.entrySet()){
          
            Staff _staff = entry.getValue();

            try(FileWriter writer = new FileWriter("resources/staff.csv")){
            /**
             * Structure of staff.csv
             *  id | name | age | gender | mobileNumber | address | category | type | salary | workingDays
             *     |      |     |        |              |         |          |      |        |
             */
                StringBuilder s = new StringBuilder();
    
                s.append('\n');
        
                writer.write(s.toString());
                writer.close();
            }
            catch (Exception e) {
                System.out.println(e);
              }

        }

        //Guest
        for(Map.Entry<Integer,Guest> entry : Hotel.guestsList.entrySet()){
            Guest _guest = entry.getValue();

            try(FileWriter writer = new FileWriter("resources/guests.csv")){
            /**
             * Structure of staff.csv
             *  id | name | age | gender | mobileNumber | address | category | aadharNumber | Rooms Vector... 
             *     |      |     |        |              |         |          |              |
             */
                    StringBuilder s = new StringBuilder();
        
                    s.append('\n');
            
                    writer.write(s.toString());
                    writer.close();
                }
                catch (Exception e) {
                    System.out.println(e);
                  }

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
