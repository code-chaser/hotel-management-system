package com.hms.rooms;

import java.util.*;
import java.io.*;

public class Room {
    public static void main(String[] args) {
        Room room1 = new Room();
        room1.checkIn();
        room1.checkOut();
    }
    private boolean available;
	private boolean occupied;
	private Integer capacity;
	private String desc;
	private Integer roomSize;
	private Integer roomNumber;

	public Room(boolean available, boolean occupied, Integer capacity, String desc, Integer roomSize, Integer roomNumber) {
		this.available = available;
		this.occupied = occupied;
		this.capacity = capacity;
		this.desc = desc;
		this.roomSize = roomSize;
		this.roomNumber = roomNumber;
	}

    public Room(){
        this.available = true;
        this.occupied = false;
        this.capacity = 2;
        this.desc = "";
        this.roomSize = 14;
        this.roomNumber = 101;
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

	public void book() {
		if (this.isAvailable()) {
            setAvailable(false);
        }
        else{
            System.out.println("Room is not available");
        }
    }

    public void checkIn() {
        if(!isAvailable() && !isOccupied()) {
            setOccupied(true);
        }
        else if(isAvailable()) {
            System.out.println("Room is not booked");
        }
        else {
            System.out.println("Room is already occupied");
        }
    }

    public void checkOut() {
        if(isOccupied()){
            setAvailable(true);
            setOccupied(false);
            System.out.println(generateBill());
        }
        else {
            System.out.println("Room was not occupied");
        }
    }

    public String generateBill() {
        int baseCost = 100*roomSize;
        float tax = (float) (baseCost*0.18);
        float totalCost = baseCost + tax;
        String bill;
        bill = "Room Number: " + roomNumber + "\n" +
                "Room Size: " + roomSize + "\n" +
                "Base Cost: " + baseCost + "\n" +
                "Tax: " + tax + "\n" +
                "Total Cost: " + totalCost;
        return bill;
    }
}