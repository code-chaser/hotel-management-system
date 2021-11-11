package com.hms.persons;

import java.util.*;
import java.io.*;

public class Guest extends Person {
    protected String aadharNumber = "";
    protected Vector<Integer> roomNumbers = new Vector<Integer>(1);

    public static void main(String[] Args) {
        Guest g1 = new Guest();
        g1.addPerson(10, 200);
        g1.addPerson(10, 200);
        g1.getDetails();
    }

    public Guest() {
        id = -1;
        cat = "Guest";
    }

    public Guest(Guest g) {
        this.assign(g);
    }

    public Guest(Integer id, String name, Integer age, Character gender, String mobNumber, com.hms.persons.Address add,
            String cat, String aadharNumber, Vector<Integer> roomNumbers) {
        super(id, name, age, gender, mobNumber, add, cat);
        this.aadharNumber = aadharNumber;
        this.roomNumbers = roomNumbers;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public Vector<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(Vector<Integer> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public void addRoomNumber(int roomNumber) {
        roomNumbers.add(roomNumber);
    }

    public void removeRoomNumber(int roomNumber) {
        int index = Integer.MAX_VALUE;
        for (int i = 0; i < roomNumbers.size(); i++) {
            if (roomNumbers.get(i) == roomNumber) {
                index = i;
                break;
            }
        }
        if (index != Integer.MAX_VALUE)
            roomNumbers.remove(index);
    }

    public void assign(Guest g) {
        super.assign(g);
        this.aadharNumber = g.aadharNumber;
        this.roomNumbers = g.roomNumbers;
        return;
    }

    public void addPerson(int minAge, int maxAge) {
        super.addPerson(0, 200);
        if (this.id == -2)
            return;
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter aadhar number:\n");
        inp = cin.next();
        inp += cin.nextLine();
        aadharNumber = inp;
        cat = "Guest";
        if (com.hms.Hotel.guestsList.entrySet().size() > 0) {
            this.id = com.hms.Hotel.guestsList.lastEntry().getKey() + 1;
            com.hms.Hotel.guestsList.put(this.id, new Guest(this));
        } else {
            this.id = 1;
            com.hms.Hotel.guestsList.put(1, new Guest(this));
        }
        return;
    }

    public void inputDetails() {
        super.addPerson(0, 200);
        if (this.id == -2)
            return;
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter aadhar number:\n");
        inp = cin.next();
        inp += cin.nextLine();
        aadharNumber = inp;
        cat = "Guest";
    }

    public void printDetails() {
        if (id == -1)
            return;
        System.out.println("\nGuest Details:");
        super.printDetails();
        if (!aadharNumber.equals(""))
            System.out.print("Aadhar Number   : " + aadharNumber + "\n");
        System.out.print("Rooms Booked    : " + roomNumbers + "\n");
        return;
    }

    public void getDetails() {
        Scanner cin = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            int opt = 0;
            String inp;
            System.out.print(
                    "\nSearch using: (select one of the following options)\n1. ID\n2. Name\n3. Mobile Number\n4. Aadhar Number\n");
            inp = cin.next();
            inp += cin.nextLine();
            opt = Integer.parseInt(inp);
            while (opt < 1 || opt > 4) {
                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                inp = cin.next();
                inp += cin.nextLine();
                opt = Integer.parseInt(inp);
            }
            switch (opt) {

            case 1:
                int reqId = 0;
                System.out.print("\nEnter ID:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqId = Integer.parseInt(inp);
                if (com.hms.Hotel.guestsList.containsKey(reqId)) {
                    this.assign(com.hms.Hotel.guestsList.get(reqId));
                    done = true;
                    break;
                } else {
                    System.out.print("\nNo matching record found!\n");
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
                break;
            case 2:
                String reqName = "";
                System.out.print("\nEnter Name:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqName = inp;
                int found = 0;
                TreeMap<Integer, Guest> MatchingRecords = new TreeMap<Integer, Guest>();
                for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet())
                    if (entry.getValue().name.equals(reqName)) {
                        System.out.println(entry.getKey());
                        MatchingRecords.put(entry.getKey(), entry.getValue());
                        found++;
                    }
                if (found == 0) {
                    System.out.print("\nNo matching record found!\n");
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
                } else {
                    System.out.print("\nMatching records:\n");
                    for (Map.Entry<Integer, Guest> entry : MatchingRecords.entrySet()) {
                        entry.getValue().printDetails();
                        System.out.print("\n");
                    }
                    Boolean done1 = false;
                    while (!done1) {
                        System.out.print("\nEnter the ID of required guest from the list above:\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        id = Integer.parseInt(inp);
                        if (!MatchingRecords.containsKey(id)) {
                            System.out.print("\nEntered ID doesn't match ID of any guest from the list above!\n");
                            System.out.print("\nTry again? (Y = Yes | N = No)\n");
                            inp = cin.next();
                            inp += cin.nextLine();
                            while (!inp.equals("Y") && !inp.equals("N")) {
                                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                                inp = cin.next();
                                inp += cin.nextLine();
                            }
                            if (inp.equals("N")) {
                                done1 = true;
                                break;
                            }
                        } else {
                            this.assign(MatchingRecords.get(id));
                            done1 = done = true;
                            break;
                        }
                    }
                    if (done)
                        break;
                    else {
                        System.out.print("\nNo matching record found!\n");
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
                String reqMobNumber = "";
                System.out.print("\nEnter Mobile Number:\n");
                inp = cin.next();
                inp += cin.nextLine();
                reqMobNumber = inp;
                int found1 = 0;
                TreeMap<Integer, Guest> MatchingRecords1 = new TreeMap<Integer, Guest>();
                for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet())
                    if (entry.getValue().mobNumber.equals(reqMobNumber)) {
                        MatchingRecords1.put(entry.getKey(), entry.getValue());
                        found1++;
                    }
                if (found1 == 0) {
                    System.out.print("\nNo matching record found!\n");
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
                } else {
                    System.out.print("\nMatching records:\n");
                    for (Map.Entry<Integer, Guest> entry : MatchingRecords1.entrySet()) {
                        entry.getValue().printDetails();
                        System.out.print("\n");
                    }
                    Boolean done2 = false;
                    while (!done2) {
                        System.out.print("\nEnter the ID of required guest from the list above:\n");
                        inp = cin.next();
                        inp += cin.nextLine();
                        id = Integer.parseInt(inp);
                        if (!MatchingRecords1.containsKey(id)) {
                            System.out.print("\nEntered ID doesn't match ID of any guest from the list above!\n");
                            System.out.print("\nTry again? (Y = Yes | N = No)\n");
                            inp = cin.next();
                            inp += cin.nextLine();
                            while (!inp.equals("Y") && !inp.equals("N")) {
                                System.out.print("\nInvalid Choice!\nEnter again:\n ");
                                inp = cin.next();
                                inp += cin.nextLine();
                            }
                            if (inp.equals("N")) {
                                done2 = true;
                                break;
                            }
                        } else {
                            this.assign(MatchingRecords1.get(id));
                            done2 = done = true;
                            break;
                        }
                    }
                }
                break;
            case 4:
                System.out.print("\nEnter Aadhar Number:\n");
                inp = cin.next();
                inp += cin.nextLine();
                if (com.hms.Hotel.guestsList.containsValue(inp)) {
                    for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet()) {
                        if (entry.getValue().aadharNumber.equals(inp)) {
                            this.assign(entry.getValue());
                            done = true;
                            break;
                        }
                    }
                } else {
                    System.out.print("\nNo matching record found!\n");
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
                break;
            }
        }
        return;
    }
}