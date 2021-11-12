package com.hms;

import java.util.*;
import com.hms.fileHandling.FileHandling;
import com.hms.Hotel;
import com.hms.rooms.Room;
import com.hms.persons.Address;
import com.hms.persons.Guest;
import com.hms.persons.Staff;

import java.io.*;

class ReaderThread implements Runnable {
    @Override
    public void run() {
        try {
            FileHandling.readFromCSV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // creating a thread to read from CSV files;
        ReaderThread readerThread = new ReaderThread();
        Thread thread = new Thread(readerThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ---------------------------

        // main(); Body
        Scanner cin = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            String inp = "";
            Integer choice;
            System.out.print("\n\n---------------------------");
            System.out.print("\n\n- HOTEL MANAGEMENT SYSTEM -");
            System.out.print("\n\n---------------------------\n");
            System.out.print("\n[01] Login (Staff Only)");
            System.out.print("\n[02] See Available Rooms");
            System.out.print("\n[03] Book a Room");
            System.out.print("\n[04] Check-Out");
            System.out.print("\n\n[00] Exit");
            System.out.print("\n\n---------------------------");
            System.out.print("\n\nEnter your choice:\n");
            inp = cin.next();
            inp += cin.nextLine();
            try {
                choice = Integer.parseInt(inp);
            } catch (Exception e) {
                System.out.print("\n\nInvalid Input!\n");
                continue;
            }
            System.out.print("\n---------------------------");

            switch (choice) {
            case 1:
                System.out.print("\n\n---------- LOGIN ----------");
                System.out.print("\n\n---------------------------\n");
                // ask for username and password
                System.out.print("\nEnter your username: ");
                inp = cin.next();
                inp += cin.nextLine();
                String username = inp;
                System.out.print("Enter your password: ");
                inp = cin.next();
                inp += cin.nextLine();
                String password = inp;
                System.out.print("\n---------------------------\n");
                Staff staff = new Staff();
                if (staff.login(username, password)) {
                    System.out.print("\n---- Logged In as " + staff.getType() + " ----\n");
                    System.out.print("\n\n---------------------------\n");
                    staff.printDetails();
                    System.out.print("\n---------------------------\n");
                    if (staff.getType().equals("ADMIN")) {
                        System.out.println("[01] Show all Staff Details");
                        System.out.println("[02] Add a new Staff Member");
                        System.out.println("[03] Change Staff Details");
                        System.out.println("[04] Remove a Staff Member");
                        System.out.println("[05] Show all Guest Details");
                        System.out.print("\n---------------------------\n");
                        System.out.print("Your Input: ");
                        int ch;
                        ch = Integer.parseInt(cin.next());
                        System.out.print("\n---------------------------\n");
                        switch (ch) {
                        case 1: {
                            System.out.println("All staff details: ");
                            Hotel.printStaffDetails();
                            break;
                        }
                        case 2: {
                            Staff temp_staff = new Staff();
                            temp_staff.addPerson(0, 200);
                            break;
                        }
                        case 3: {
                            Staff temp_staff = new Staff();
                            temp_staff.getDetails();
                            if (temp_staff.getId().equals(-1))
                                break;
                            System.out.print("\n---------------------------\n");
                            System.out.print("\nEnter the field you want to change: ");
                            System.out.println("[01] Staff Type");
                            System.out.println("[02] Salary");
                            System.out.println("[03] Password");
                            System.out.print("\n---------------------------\n");
                            System.out.print("Your Input: ");

                            int ch1 = Integer.parseInt(cin.next());
                            System.out.print("\n---------------------------\n");
                            switch (ch1) {
                            case 2: {
                                System.out.print("Enter new salary: ");
                                String salary = cin.next();
                                temp_staff.setSalary(salary);
                                Hotel.staffList.get(temp_staff.getId()).assign(temp_staff);
                                break;
                            }
                            case 3: {
                                System.out.print("Enter new password: ");
                                String password1 = cin.next();
                                temp_staff.setPassword(password1);
                                Hotel.staffList.get(temp_staff.getId()).assign(temp_staff);
                                break;
                            }
                            case 1: {
                                System.out.print("Enter new type: ");
                                String name = cin.next();
                                temp_staff.setType(name);
                                Hotel.staffList.get(temp_staff.getId()).assign(temp_staff);
                                break;
                            }
                            default: {
                                System.out.println("Invalid Input!");
                                break;
                            }
                            }
                            break;
                        }
                        case 4: {
                            Staff temp_staff = new Staff();
                            temp_staff.getDetails();
                            if (temp_staff.getId().equals(-1))
                                break;
                            Hotel.staffList.remove(temp_staff.getId());
                        }
                        case 5: {
                            System.out.println("See all guest details");
                            Hotel.printGuestDetails();
                        }
                        }
                    } else if (staff.getType().equals("Receptionist") || staff.getType().equals("Manager")) {
                        System.out.print("\n---------------------------\n");
                        System.out.println("[01] Edit Own Details ");
                        System.out.println("[02] See All Guest Details ");
                        System.out.print("\n---------------------------\n");
                        System.out.print("Your Input: ");

                        int ch1 = Integer.parseInt(cin.next());
                        switch (ch1) {
                        case 1: {
                            System.out.print("\n---------------------------\n");
                            System.out.println("[01] Change Phone Number ");
                            System.out.println("[02] Change Password ");
                            System.out.print("\n---------------------------\n");
                            System.out.print("[Your Input:  ");

                            int ch = Integer.parseInt(cin.next());
                            switch (ch) {
                            case 1: {
                                System.out.println("Enter new phone number: ");
                                String phoneno = cin.next();
                                staff.setMobileNumber(phoneno);
                                Hotel.staffList.get(staff.getId()).assign(staff);
                                break;
                            }
                            case 2: {
                                System.out.println("Enter new password: ");
                                String password1 = cin.next();
                                staff.setPassword(password1);
                                Hotel.staffList.get(staff.getId()).assign(staff);
                                break;
                            }
                            }
                            break;
                        }
                        case 2: {
                            Hotel.printGuestDetails();
                            break;
                        }
                        }
                    } else {
                        System.out.print("\n---------------------------\n");
                        System.out.println("----EDIT OWN DETAILS----");
                        System.out.println("[01] Change Phone Number ");
                        System.out.println("[02] Change Password ");
                        System.out.print("\n---------------------------\n");
                        System.out.print("[Your Input:  ");
                        int ch = Integer.parseInt(cin.next());
                        switch (ch) {
                        case 1: {
                            System.out.println("Enter new phone number: ");
                            String phoneno = cin.next();
                            staff.setMobileNumber(phoneno);
                            break;
                        }
                        case 2: {
                            System.out.println("Enter new password: ");
                            String password1 = cin.next();
                            staff.setPassword(password1);
                        }
                        }
                        break;
                    }
                } else {
                    System.out.print("Login failed!\nInvalid username or password.");
                }

                System.out.print("\n\n---------------------------");
                break;
            case 2:
                // Room Details
                System.out.println("\n\n---------------------------");
                Hotel.printRoomDetails();
                System.out.println("\n\n---------------------------");

                break;
            case 3:
                // Book a Room
                System.out.print("\n\n---------------------------\n");
                Hotel.printRoomDetails();
                System.out.println("Room Number: ");
                int roomNum = cin.nextInt();
                if (!Hotel.roomsList.containsKey(roomNum)) {
                    System.out.println("Room not Found");
                    break;
                }
                if (!Hotel.roomsList.get(roomNum).isAvailable()) {
                    System.out.println("Room Unavailable");
                    break;
                }
                Guest guest = new Guest();
                guest.inputDetails();
                for (var entry : Hotel.guestsList.entrySet()) {
                    int tempID = entry.getValue().getId();
                    entry.getValue().setID(-1);
                    if (guest.equals(entry.getValue())) {
                        entry.getValue().addRoomNumber(roomNum);
                        entry.getValue().setID(tempID);
                        System.out.println("Room booked successfully!");
                        break;
                    }
                    entry.getValue().setID(tempID);
                }
                guest.addRoomNumber(roomNum);
                Hotel.roomsList.get(roomNum).book();
                guest.setID(Hotel.guestsList.lastEntry().getKey() + 1);
                Hotel.guestsList.put(guest.getId(), new Guest(guest));
                break;
            case 0:
                System.out.print("\n\nExiting...");
                done = true;
                System.out.print("\n\n---------------------------");
                break;
            case 4:
                System.out.print("\n\n---------------------------");
                System.out.print("\n\nCheck-Out");
                System.out.print("\n---------------------------\n");
                System.out.print("\nEnter your Phone No. : ");
                inp = cin.next();
                inp += cin.nextLine();
                String phoneNo = inp;
                System.out.print("\n---------------------------\n");
                for (var entry : Hotel.guestsList.entrySet()) {
                    if (entry.getValue().getMobileNumber().equals(phoneNo)) {
                        System.out.print("\n\nGuest Details");
                        System.out.print("\n---------------------------\n");
                        entry.getValue().printDetails();
                        System.out.print("\n---------------------------\n");
                        System.out.print("\nEnter the Room Number: ");
                        inp = cin.next();
                        inp += cin.nextLine();
                        int roomNum2 = Integer.parseInt(inp);
                        if ((!Hotel.roomsList.containsKey(roomNum2))) {
                            System.out.println("Room not Found");
                            break;
                        }
                        if (Hotel.roomsList.get(roomNum2).isAvailable()) {
                            System.out.println("Room not Booked");
                            break;
                        }
                        if (!entry.getValue().getRoomNumbers().contains(roomNum2)) {
                            System.out.println("This isn't a room Booked by you.");

                            break;
                        }
                        entry.getValue().removeRoomNumber(roomNum2);
                        Hotel.roomsList.get(roomNum2).checkOut();
                        System.out.println("Check-Out Successful!");
                        if (entry.getValue().getRoomNumbers().size() == 0) {
                            Hotel.guestsList.remove(entry.getKey());
                        }
                        Hotel.roomsList.get(roomNum2).generateBill();
                        break;
                    }
                    else {
                        System.out.println("Invalid Input");
                        break;
                    }
                }
                break;

            default:
                System.out.print("\n\nInvalid choice!");
                System.out.print("\n\n---------------------------");
                break;
            }

        }

        FileHandling.writeToCSV();
        return;
    }

}