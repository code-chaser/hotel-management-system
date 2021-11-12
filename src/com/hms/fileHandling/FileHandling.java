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

class ThreadForRoomsRead implements Runnable {
    @Override
    public void run() {
        Hotel.roomsList.clear();
        String line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br1 = new BufferedReader(new FileReader("../resources/rooms.csv"));
            while ((line = br1.readLine()) != null) // returns a Boolean value
            {
                String[] roomArray = line.split(","); // use comma as separator
                boolean avail;
                if (roomArray[4].equals("Y"))
                    avail = true;
                else
                    avail = false;
                Room _room = new Room(avail, !avail, Integer.parseInt(roomArray[1]), roomArray[2],
                        Integer.parseInt(roomArray[3]), Integer.parseInt(roomArray[0]));
                Hotel.roomsList.put(Integer.parseInt(roomArray[0]), _room);
            }
            br1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadForStaffRead implements Runnable {
    @Override
    public void run() {
        Hotel.staffList.clear();
        String line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br2 = new BufferedReader(new FileReader("../resources/staff.csv"));
            while ((line = br2.readLine()) != null) // returns a Boolean value
            {
                String[] staffArray = line.split(","); // use comma as separator
                Address _add = new Address();
                _add.strToAdd(staffArray[5]);
                Staff _staff = new Staff(Integer.parseInt(staffArray[0]), staffArray[1],
                        Integer.parseInt(staffArray[2]), staffArray[3].charAt(0), staffArray[4], _add, staffArray[6],
                        staffArray[7], staffArray[8], Integer.parseInt(staffArray[9]), staffArray[10], staffArray[11]);

                Hotel.staffList.put(Integer.parseInt(staffArray[0]), _staff);
            }
            br2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadForGuestsRead implements Runnable {
    @Override
    public void run() {
        Hotel.guestsList.clear();
        String line = "";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br3 = new BufferedReader(new FileReader("../resources/guests.csv"));
            while ((line = br3.readLine()) != null) // returns a Boolean value
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
            br3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadForRoomsWrite implements Runnable {
    @Override
    public void run() {
        try {
            FileWriter clearFile1 = new FileWriter("../resources/rooms.csv");
            clearFile1.write("");
            clearFile1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Room> entry : Hotel.roomsList.entrySet()) {
            Room _room = entry.getValue();

            try {
                FileWriter writer1 = new FileWriter("../resources/rooms.csv", true);
                /**
                 * Structure of rooms.csv : roomNumber | capacity | desc | roomSize | available
                 */
                StringBuilder s1 = new StringBuilder();
                s1.append(_room.getRoomNumber().toString() + ',');
                s1.append(_room.getCapacity().toString() + ',');
                s1.append(_room.getDesc().toString() + ',');
                s1.append(_room.getRoomSize().toString() + ',');

                if (_room.isAvailable()) {
                    s1.append('Y');
                } else
                    s1.append('N');

                s1.append('\n');

                writer1.append(s1.toString());
                writer1.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class ThreadForStaffWrite implements Runnable {
    @Override
    public void run() {
        try {
            FileWriter clearFile2 = new FileWriter("../resources/staff.csv");
            clearFile2.write("");
            clearFile2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Staff> entry : Hotel.staffList.entrySet()) {

            Staff _staff = entry.getValue();

            try {
                FileWriter writer2 = new FileWriter("../resources/staff.csv", true);
                /**
                 * Structure of staff.csv : id | name | age | gender | mobileNumber | address |
                 * category | type | salary | workingDays | LoginID | Password
                 */
                StringBuilder s2 = new StringBuilder();
                s2.append(_staff.getId().toString() + ',');
                s2.append(_staff.getName() + ',');
                s2.append(_staff.getAge().toString() + ',');
                s2.append(_staff.getGender().toString() + ',');
                s2.append(_staff.getMobileNumber() + ',');
                s2.append(_staff.getAddress().addToStr() + ',');
                s2.append(_staff.getCategory() + ',');
                s2.append(_staff.getType() + ',');
                s2.append(_staff.getSalary() + ',');
                s2.append(_staff.getWorkingDays().toString() + ',');
                s2.append(_staff.getLoginId() + ',');
                s2.append(_staff.getPassword());

                s2.append('\n');

                writer2.append(s2.toString());
                writer2.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}

class ThreadForGuestsWrite implements Runnable {
    @Override
    public void run() {
        try {
            FileWriter clearFile3 = new FileWriter("../resources/guests.csv");
            clearFile3.write("");
            clearFile3.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (Map.Entry<Integer, Guest> entry : Hotel.guestsList.entrySet()) {
            Guest _guest = entry.getValue();

            try {
                FileWriter writer3 = new FileWriter("../resources/guests.csv", true);
                /**
                 * Structure of staff.csv : id | name | age | gender | mobileNumber | address |
                 * category | aadharNumber | Rooms Vector...
                 */
                StringBuilder s3 = new StringBuilder();
                s3.append(_guest.getId().toString() + ',');
                s3.append(_guest.getName() + ',');
                s3.append(_guest.getAge().toString() + ',');
                s3.append(_guest.getGender().toString() + ',');
                s3.append(_guest.getMobileNumber() + ',');
                s3.append(_guest.getAddress().addToStr() + ',');
                s3.append(_guest.getCategory() + ',');
                s3.append(_guest.getAadharNumber() + ',');

                Vector<Integer> roomVector = _guest.getRoomNumbers();

                for (Integer i = 0; i < roomVector.size() - 1; i++) {
                    s3.append(roomVector.get(i).toString() + ',');
                }
                s3.append(roomVector.get(roomVector.size() - 1).toString());
                s3.append('\n');

                writer3.append(s3.toString());
                writer3.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}

public class FileHandling {

    /**
     * Function to overwrite data into the Maps from CSV files.
     */
    public static void readFromCSV() {
        // Filling roomsList
        ThreadForRoomsRead threadForRoomsRead = new ThreadForRoomsRead();
        Thread t1 = new Thread(threadForRoomsRead);
        t1.start();

        // Filling staffList
        ThreadForStaffRead threadForStaffRead = new ThreadForStaffRead();
        Thread t2 = new Thread(threadForStaffRead);
        t2.start();

        // Filling guestsList
        ThreadForGuestsRead threadForGuestsRead = new ThreadForGuestsRead();
        Thread t3 = new Thread(threadForGuestsRead);
        t3.start();

        // Now joining threads to make sure that all the data from CSV files are loaded
        // into the Maps before returning back the control;
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return;
    }

    /**
     * Function to overwrite CSV files with data stored in Maps.
     */
    public static void writeToCSV() {
        // Filling rooms.csv
        ThreadForRoomsWrite threadForRoomsWrite = new ThreadForRoomsWrite();
        Thread t1 = new Thread(threadForRoomsWrite);
        t1.start();

        // Filling staff.csv
        ThreadForStaffWrite threadForStaffWrite = new ThreadForStaffWrite();
        Thread t2 = new Thread(threadForStaffWrite);
        t2.start();

        // Filling guests.csv
        ThreadForGuestsWrite threadForGuestsWrite = new ThreadForGuestsWrite();
        Thread t3 = new Thread(threadForGuestsWrite);
        t3.start();

        // Now joining threads to make sure that all data is written into CSV files
        // before returning back the control;
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        return;
    }

}