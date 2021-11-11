package com.hms.persons;

import java.util.*;
import java.io.*;

public class Address {
    protected String line1, line2;
    protected String city;
    protected String state;
    protected String pinCode;
    protected String country;

    public Address() {
        line1 = line2 = city = state = pinCode = country = "";
    }

    public void takeInput() {
        Scanner cin = new Scanner(System.in);
        System.out.print("\nEnter Address:\n");
        System.out.print("\nEnter Line 1:\n");
        line1 = cin.next();
        line1 += cin.nextLine();
        System.out.print("\nEnter Line 2:\n");
        line2 = cin.nextLine();
        System.out.print("\nEnter City:\n");
        city = cin.next();
        city += cin.nextLine();
        System.out.print("\nEnter State:\n");
        state = cin.next();
        state += cin.nextLine();
        System.out.print("\nEnter Pincode:\n");
        pinCode = cin.next();
        pinCode += cin.nextLine();
        System.out.print("\nEnter Country:\n");
        country = cin.next();
        country += cin.nextLine();
        return;
    }

    public void print() {
        System.out.print("Line 1: " + line1 + "\n");
        if (line2 != "")
            System.out.print("                  Line 2: " + line2 + "\n");
        System.out.print("                  City: " + city + "\n");
        System.out.print("                  State: " + state + "\n");
        System.out.print("                  Pincode: " + pinCode + "\n");
        System.out.print("                  Country: " + country + "\n");
        return;
    }

    public synchronized void strToAdd(String add) {
        String[] arr = new String[6];
        String temp = "";
        Integer count = 0;
        for (int i = 0; i < add.length(); i++) {
            if (add.charAt(i) == '`') {
                arr[count] = temp;
                temp = "";
                count++;
            } else
                temp += add.charAt(i);
        }
        line1 = arr[0];
        if (line1 != null)
            for (Integer i = 0; i < line1.length(); i++)
                if (line1.charAt(i) == '^')
                    line1 = line1.substring(0, i) + ',' + line1.substring(i + 1);
        line2 = arr[1];
        if (line2 != null)
            for (Integer i = 0; i < line2.length(); i++)
                if (line2.charAt(i) == '^')
                    line2 = line2.substring(0, i) + ',' + line2.substring(i + 1);
        city = arr[2];
        state = arr[3];
        pinCode = arr[4];
        country = arr[5];
        return;
    }

    public synchronized String addToStr() {
        String add = "";
        add += line1 + '`';
        add += line2 + '`';
        add += city + '`';
        add += state + '`';
        add += pinCode + '`';
        add += country + '`';
        for (Integer i = 0; i < add.length(); i++)
            if (add.charAt(i) == ',')
                add = add.substring(0, i) + '^' + add.substring(i + 1);
        return add;
    }

}