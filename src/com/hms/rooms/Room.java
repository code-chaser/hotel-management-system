package com.hms.rooms;

import java.util.*;
import java.io.*;
import com.hms.*;

public class Room {

    protected Integer roomNumber;
    protected Integer capacity;
    protected String desc;
    protected Integer roomSize = 3;
    // 1 : Classic | 2 : Deluxe | 3 : Suite
    protected boolean available;
    protected boolean occupied;

    public Room(boolean available, boolean occupied, Integer capacity, String desc, Integer roomSize,
            Integer roomNumber) {
        this.available = available;
        this.occupied = occupied;
        this.capacity = capacity;
        this.desc = desc;
        this.roomSize = roomSize;
        this.roomNumber = roomNumber;
    }

    public Room() {
        this.available = true;
        this.occupied = false;
        this.capacity = 2;
        this.desc = "";
        this.roomSize = 14;
        this.roomNumber = -1;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void getDetails() {
        Scanner cin = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            int opt = 0;
            String inp;
            System.out
                    .print("\nPlease select what kind of room are you looking for:\n1. Classic \n2. Deluxe \n3.Suite ");
            inp = cin.next();
            inp += cin.nextLine();
            opt = Integer.parseInt(inp);
            while (opt < 1 || opt > 3) {
                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                inp = cin.next();
                inp += cin.nextLine();
                opt = Integer.parseInt(inp);
            }
            switch (opt) {

            case 1:
                for (var entry : Hotel.roomsList.entrySet()) {
                    if (entry.getValue().getRoomSize() == 1 && entry.getValue().isAvailable()) {
                        System.out.println("Classic Room is available.");
                        done = true;
                        break;
                    } else {
                        System.out.print("\nSorry! Currently there is no Classic room avialble.\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }
                }

            case 2:
                for (var entry : Hotel.roomsList.entrySet()) {
                    if (entry.getValue().getRoomSize() == 2 && entry.getValue().isAvailable()) {
                        System.out.println("Deluxe Room is available.");
                        done = true;
                        break;
                    } else {
                        System.out.print("\nSorry! Currently there is no Deluxe room avialble.\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }
                }
                break;
            case 3:
                for (var entry : Hotel.roomsList.entrySet()) {
                    if (entry.getValue().getRoomSize() == 3 && entry.getValue().isAvailable()) {
                        System.out.println("Suite is available.");
                        done = true;
                        break;
                    } else {
                        System.out.print("\nSorry! Currently there is no Suite avialble.\n");
                        System.out.print("\nTry again? (Y = Yes | N = No)\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        while (!inp.equals("Y") && !inp.equals("N")) {
                            System.out.print("\nInvalid Choice!\nEnter again:\n ");
                            inp = cin.next();
                            inp += cin.nextLine();
                        }
                        if (inp.equals("N")) {
                            done = true;
                            break;
                        }
                    }
                }
                break;

            default:
                System.out.println("Invalid Choice");
                break;
            }
        }
        return;
    }

    public void book() {
        if (this.isAvailable()) {
            setAvailable(false);
        } else {
            System.out.println("Room is not available\n");
        }
        return;
    }

    public void checkIn() {
        if (!isAvailable() && !isOccupied()) {
            setOccupied(true);
        } else if (isAvailable()) {
            System.out.println("Room is not booked\n");
        } else {
            System.out.println("Room is already occupied\n");
        }
        return;
    }

    public void checkOut() {
        if (isOccupied()) {
            setAvailable(true);
            setOccupied(false);
            System.out.println(generateBill());
        } else {
            System.out.println("Room was not occupied\n");
        }
        return;
    }

    public String printRoomDetails() {
        String roomDetails;
        roomDetails = "\nRoom Details:\nRoom Number     : " + roomNumber + "\n" + "Room Type       : ";
        roomDetails += (roomSize == 1 ? "Classic" : (roomSize == 2 ? "Deluxe" : "Suite"));
        roomDetails += "\n" + "Room Description: " + desc + "\n" + "Room Capacity    : " + capacity + "\n"
                + "Room Availability: " + (available ? "Yes" : "No") + "\n";
        return roomDetails;
    }

    public String generateBill() {
        int baseCost = 1000 * roomSize;
        double tax = (double) baseCost * 0.18;
        double totalCost = baseCost + tax;
        String bill = printRoomDetails();
        bill += "Cost Details:\n";
        bill += "\n" + "Base Cost       : " + baseCost + "\n" + "Tax             : " + tax + "\n" + "Total Cost      : " + totalCost + "\n";
        return bill;
    }
}