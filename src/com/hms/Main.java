package com.hms;

import java.util.*;

import com.hms.fileHandling.FileHandling;
import com.hms.Hotel;
import com.hms.rooms.Room;
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
        Staff s = new Staff();
        s.addPerson(10, 200);
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
            System.out.print("\n\n[04] Exit");
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
                    System.out.print("\n-------- LOGGED IN --------");
                    System.out.print("\n\n---------------------------\n");
                    staff.printDetails();
                    System.out.print("\n---------------------------\n");
                    // staff menu
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
                System.out.print("\n\n---------------------------");
                Hotel.printRoomDetails();
                System.out.println("Room Number: ");
                int roomNum = cin.nextInt();
                if(!Hotel.roomsList.containsKey(roomNum)){
                    System.out.println("N/A");
                    break;
                }
                if(!Hotel.roomsList.get(roomNum).isAvailable()){
                    System.out.println("Room N/A");
                    break;
                }
                Guest guest = new Guest();
                guest.addPerson(10, 100);
                guest.addRoomNumber(roomNum);
                Hotel.roomsList.get(roomNum).book();
                break;
            case 4:
                System.out.print("\n\nExiting...");
                done = true;
                System.out.print("\n\n---------------------------");
                break;
            case 5:
                System.out.print("\n\n---------------------------");

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