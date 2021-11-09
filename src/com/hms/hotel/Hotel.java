package com.hms.hotel;

import com.hms.rooms.*;
import com.hms.persons.*;
import java.util.HashMap;

public class Hotel {
    static HashMap<Integer, Room> rooms = new HashMap<>();
    static HashMap<Integer, Staff> personnel = new HashMap<>();

    public static void main(String[] args) {
        Room room = new Room();
        room.setDesc("Room...");
        rooms.put(101, room);
        rooms.put(1401, new Room(false, false, 2, "Room hai bro", 14, 1401));
        printRoomDetails();
    }

    static void printRoomDetails() {
        for (var room : rooms.entrySet()) {
            System.out.print(room.getKey() + ": ");
            if(room.getKey()/1000 == 0) System.out.print(" ");
            Room currentRoom = room.getValue();
            System.out.println("Available: " + ((currentRoom.isAvailable()) ? "Yes" : "No"));
            System.out.println("      Price: " + 100*currentRoom.getRoomSize());
            System.out.println("      "+currentRoom.getDesc());
            System.out.println("      Capacity: " + currentRoom.getCapacity());
            System.out.println("      Room Size: " + currentRoom.getRoomSize());
            System.out.println();
        }
    }

    void printStaffDetails() {
    }
}