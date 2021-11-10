package com.hms.persons;

import java.util.*;
import java.io.*;

public class Guest extends Person {
    String aadharNumber = "";
    Vector<Integer> roomNumbers = new Vector<Integer>(1);

    public Guest() {
        id = -1;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(0, 200);
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter aadhar number:\n");
        inp = cin.next();
        inp += cin.nextLine();
        aadharNumber = inp;
        if (com.hms.Hotel.guestsList.entrySet().size() > 0)
            com.hms.Hotel.guestsList.put(com.hms.Hotel.guestsList.lastEntry().getKey() + 1, this);
        else com.hms.Hotel.guestsList.put(1,this);
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        if (aadharNumber != "")
            System.out.print("Aadhar Number   : " + aadharNumber + "\n");
        System.out.print("Rooms Booked    : " + roomNumbers + "\n");
        return;
    }

    public void getDetails(int id) {
        System.out.print("\nSearch using: (select one of the following options)\n1. ID\n2. Name\n3.Mobile Number\n4.Aadhar Number\n)");
    }
}