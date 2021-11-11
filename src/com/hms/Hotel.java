package com.hms;

import com.hms.rooms.*;
import com.hms.persons.*;
import java.util.*;
import java.io.*;

public class Hotel {
    public static TreeMap<Integer, Room> roomsList = new TreeMap<>();
    public static TreeMap<Integer, Staff> staffList = new TreeMap<>();
    public static TreeMap<Integer, Guest> guestsList = new TreeMap<>();

    public static void main(String[] args) {
        Room room = new Room();
        room.setDesc("Room...");
        roomsList.put(101, room);
        roomsList.put(1401, new Room(false, false, 2, "Room", 14, 1401));
        printRoomDetails();
    }

    public static void printRoomDetails() {
        for (var room : roomsList.entrySet()) {
            System.out.print(room.getKey() + ": ");
            if (room.getKey() / 1000 == 0)
                System.out.print(" ");
            Room currentRoom = room.getValue();
            System.out.println("  Available: " + ((currentRoom.isAvailable()) ? "Yes" : "No"));
            System.out.println("      Price: " + 100 * currentRoom.getRoomSize());
            System.out.println("      Room Type: " + currentRoom.getDesc());
            System.out.println("      Capacity: " + currentRoom.getCapacity());
            System.out.println("      Room Size: " + currentRoom.getRoomSize());
            System.out.println();
        }
    }

    public static void printStaffDetails() {
        for (var staff : staffList.entrySet()) {
            staff.getValue().printDetails();
            System.out.print("\n");
        }
    }

    public static void printGuestDetails() {
        for (var guest : guestsList.entrySet()) {
            guest.getValue().printDetails();
            System.out.print("\n");
        }
    }
}
