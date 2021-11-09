package com.hms.persons;

import java.util.*;
import java.io.*;

public class Guest extends Person {
    String aadharNumber = "";
    Vector<Integer> roomNumbers;

    public Guest() {
        id = -1;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(0, 200);
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter aadhar number:\n");
        inp = cin.nextLine();
        aadharNumber = inp;
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        System.out.print("Aadhar Number   : " + aadharNumber + "\n");
        System.out.print("Rooms Booked    : ");

        return;
    }

    public void printDetailsFromHistory() {
        if (id == -1)
            return;
        System.out.print("\nHistory Details:\n");
        System.out.print("Name            : " + name + "\n");
        System.out.print("Gender          : " + gender + "\n");
        System.out.print("Age             : " + age + "\n");
        System.out.print("Mobile          : " + mobNumber + "\n");
        System.out.print("Address         : ");
        add.print();
        return;
    }

    public void getDetails(int id) {
    }

    public void getDetailsFromHistory() {
    }
}