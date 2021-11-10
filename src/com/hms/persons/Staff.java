package com.hms.persons;

import java.util.*;
import java.io.*;

public class Staff extends Person {

    public Staff() {
        id = -1;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(18,60);
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        super.printDetails();
        System.out.print("Mobile          :" + mobNumber + "\n");
        return;
    }

    public void printDetailsFromHistory() {
        if (id == -1)
            return;
        super.printDetailsFromHistory();
        System.out.print("Mobile          :" + mobNumber + "\n");
        return;
    }

    public void getDetails() {
    }

    public void getDetailsFromHistory() {
    }
}