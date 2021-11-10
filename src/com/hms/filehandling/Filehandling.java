package com.hms.filehandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public static void WriteToCSV() {
        // Rooms
        for (Map.Entry<Integer, Room> entry : Hotel.roomsList.entrySet()) {
            Room _room = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/rooms.csv")) {
                /**
                 * Structure of rooms.csv : roomNumber | capacity | desc | roomSize | available
                 */
                StringBuilder s = new StringBuilder();
                s.append(_room.getRoomNumber().toString() + ',');
                s.append(_room.getCapacity().toString() + ',');
                s.append(_room.getDesc().toString() + ',');
                s.append(_room.getRoomSize().toString() + ',');

                if (_room.isAvailable()) {
                    s.append('Y');
                } else
                    s.append('N');

                s.append('\n');

                writer.write(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Staff
        for (Map.Entry<Integer, Staff> entry : Hotel.staffList.entrySet()) {

            Staff _staff = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/staff.csv")) {
                /**
                 * Structure of staff.csv : id | name | age | gender | mobileNumber | address |
                 * category | type | salary | workingDays
                 */
                StringBuilder s = new StringBuilder();

                s.append('\n');

                writer.write(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        // Guest
        for (Map.Entry<Integer, Guest> entry : Hotel.guestsList.entrySet()) {
            Guest _guest = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/guests.csv")) {
                /**
                 * Structure of staff.csv : id | name | age | gender | mobileNumber | address |
                 * category | aadharNumber | Rooms Vector...
                 */
                StringBuilder s = new StringBuilder();

                s.append('\n');

                writer.write(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    /**
     * Function to overwrite data into the Maps from CSV files.
     */
    public void readFromCSV() {
        Hotel.roomsList.clear();
        Hotel.guestsList.clear();
        Hotel.staffList.clear();

        // Filling roomList
        String line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("resources/rooms.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] roomArray = line.split(","); // use comma as separator
                boolean avail;
                if (roomArray[4] == "Y")
                    avail = true;
                else
                    avail = false;
                Room _room = new Room(avail, !avail, Integer.parseInt(roomArray[1]), roomArray[2],
                        Integer.parseInt(roomArray[3]), Integer.parseInt(roomArray[0]));
                Hotel.roomsList.put(Integer.parseInt(roomArray[0]), _room);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Filling staffList
        line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("resources/staff.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] staffArray = line.split(","); // use comma as separator

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Filling guestsList
        line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("resources/guests.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] guestsArray = line.split(","); // use comma as separator

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
