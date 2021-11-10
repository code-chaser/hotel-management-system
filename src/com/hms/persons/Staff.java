package com.hms.persons;

import java.util.*;
import java.io.*;

public class Staff extends Person {
    public String type;
    public String salary;
    public int workingDays;

    public Staff() {
        id = -1;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(18, 60);
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter type of staff:\n");
        inp = cin.nextLine();
        type = inp;
        System.out.print("\nEnter salary of staff:\n");
        inp = cin.nextLine();
        salary = inp;
        workingDays = 0;
        if (com.hms.Hotel.staffList.entrySet().size() > 0)
            com.hms.Hotel.staffList.put(com.hms.Hotel.guestsList.lastEntry().getKey() + 1, this);
        else com.hms.Hotel.staffList.put(1,this);
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        super.printDetails();
        System.out.print("Type            :" + type + "\n");
        System.out.print("Salary          :" + salary + "\n");
        System.out.print("Working Days    :" + workingDays + "\n");
        return;
    }

    public void getDetails() {
    }
}