package com.hms.fileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.hms.Hotel;
import com.hms.persons.Address;
import com.hms.persons.Guest;
import com.hms.persons.Staff;
import com.hms.rooms.Room;

public class FileHandling {
    /**
     * Debug function
     */
    public static void main(String[] args) {
        Room roomtemp = new Room(true,false,4,"good room",5,69);
        Hotel.roomsList.put(69,roomtemp);


        Address addtemp = new Address();
        Staff stafftemp = new Staff(123,"Ayush",19,'M',"123456789",addtemp,"cat","type","salary",23,"ayush0402","password");
        Hotel.staffList.put(123,stafftemp);
        Vector<Integer> roomVect = new Vector<Integer>();
        roomVect.add(123);
        roomVect.add(123);
        Guest guesttemp = new Guest(123,"Ayush",19,'M',"123456789",addtemp,"cat","9234850",roomVect);
        Hotel.guestsList.put(123,guesttemp);
        writeToCSV();
 
        Hotel.roomsList.clear();
        Hotel.staffList.clear();
        Hotel.guestsList.clear();
 
        readFromCSV();
 
        Hotel.printRoomDetails();
        Hotel.printStaffDetails();
        Hotel.printGuestDetails();
        return;
    }
    /**
     * Function to overwrite CSV files with data stored in Maps.
     */
    public static void writeToCSV() {
        // Rooms
        try (FileWriter clearFile = new FileWriter("resources/rooms.csv")) {
            clearFile.write("");
            clearFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Room> entry : Hotel.roomsList.entrySet()) {
            Room _room = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/rooms.csv",true)) {
                /**
                 * Structure of rooms.csv : 
                 * roomNumber | capacity | desc | roomSize | available
                 */
                StringBuilder s = new StringBuilder();
                s.append(_room.getRoomNumber().toString() + ',');
                s.append(_room.getCapacity().toString() + ',');
                s.append(_room.getDesc().toString() + ',');
                s.append(_room.getRoomSize().toString() + ',');

                if (_room.isAvailable()) {
                    s.append("Y");
                } else
                    s.append("N");

                s.append('\n');

                writer.append(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Staff
        try (FileWriter clearFile = new FileWriter("resources/staff.csv")) {
            clearFile.write("");
            clearFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Staff> entry : Hotel.staffList.entrySet()) {

            Staff _staff = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/staff.csv",true)) {
                /**
                 * Structure of staff.csv : 
                 * id | name | age | gender | mobileNumber | address | category | type | salary | workingDays | LoginID | Password
                 */
                StringBuilder s = new StringBuilder();
                s.append(_staff.getId().toString() + ',');
                s.append(_staff.getName() + ',');
                s.append(_staff.getAge().toString() + ',');
                s.append(_staff.getGender().toString() + ',');
                s.append(_staff.getMobileNumber() + ',');
                s.append(_staff.getAddress().addToStr() + ',');
                s.append(_staff.getCategory() + ',');
                s.append(_staff.getType() + ',');
                s.append(_staff.getSalary() + ',');
                s.append(_staff.getWorkingDays().toString() + ',');
                s.append(_staff.getLoginId() + ',');
                s.append(_staff.getPassword());

                s.append('\n');

                writer.append(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        // Guest
        try (FileWriter clearFile = new FileWriter("resources/guests.csv")) {
            clearFile.write("");
            clearFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Guest> entry : Hotel.guestsList.entrySet()) {
            Guest _guest = entry.getValue();

            try (FileWriter writer = new FileWriter("resources/guests.csv",true)) {
                /**
                 * Structure of staff.csv : 
                 * id | name | age | gender | mobileNumber | address | category | aadharNumber | Rooms Vector...
                 */
                StringBuilder s = new StringBuilder();
                s.append(_guest.getId().toString() + ',');
                s.append(_guest.getName() + ',');
                s.append(_guest.getAge().toString() + ',');
                s.append(_guest.getGender().toString() + ',');
                s.append(_guest.getMobileNumber() + ',');
                s.append(_guest.getAddress().addToStr() + ',');
                s.append(_guest.getCategory() + ',');
                s.append(_guest.getAadharNumber() + ',');

                Vector<Integer> roomVector = _guest.getRoomNumbers();

                for (Integer i = 0; i < roomVector.size() - 1; i++) {
                    s.append(roomVector.get(i).toString() + ',');
                }
                s.append(roomVector.get(roomVector.size() - 1).toString());
                s.append('\n');

                writer.append(s.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    /**
     * Function to overwrite data into the Maps from CSV files.
     */
    public static void readFromCSV() {
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

        // Filling staffList
        line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("resources/staff.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] staffArray = line.split(","); // use comma as separator
                Address _add = new Address();
                _add.strToAdd(staffArray[5]);
                Staff _staff = new Staff(Integer.parseInt(staffArray[0]), staffArray[1],
                        Integer.parseInt(staffArray[2]), staffArray[3].charAt(0), staffArray[4], _add, staffArray[6],
                        staffArray[7], staffArray[8], Integer.parseInt(staffArray[9]), staffArray[10], staffArray[11]);

                Hotel.staffList.put(Integer.parseInt(staffArray[0]), _staff);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Filling guestsList
        line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("resources/guests.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] guestsArray = line.split(","); // use comma as separator
                Address _add1 = new Address();
                _add1.strToAdd(guestsArray[5]);
                Vector<Integer> roomNumVect = new Vector<Integer>();
                for (int i = 8; i < guestsArray.length; i++) {
                    roomNumVect.add(Integer.parseInt(guestsArray[i]));
                }
                Guest _guest = new Guest(Integer.parseInt(guestsArray[0]), guestsArray[1],
                        Integer.parseInt(guestsArray[2]), guestsArray[3].charAt(0), guestsArray[4], _add1,
                        guestsArray[6], guestsArray[7], roomNumVect);

                Hotel.guestsList.put(Integer.parseInt(guestsArray[0]), _guest);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}