package com.hms.persons;

import java.util.*;

import com.hms.Hotel;

import java.io.*;

public class Guest extends Person {
    String aadharNumber = "";
    Vector<Integer> roomNumbers = new Vector<Integer>(1);

    public Guest() {
        id = -1;
    }

    public static void main(String Args[]) {
        Guest g = new Guest();
        g.addPerson(18, 100);
        // g.addPerson(18, 100);

        g.getDetails();
    }

    public void addPerson(Integer minAge, Integer maxAge) {
        super.addPerson(0, 200);
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter aadhar number:\n");
        inp = cin.next();
        inp += cin.nextLine();
        this.aadharNumber = inp;
        if (com.hms.Hotel.guestsList.entrySet().size() > 0){
            this.id = com.hms.Hotel.guestsList.entrySet().size() + 1;
            com.hms.Hotel.guestsList.put(com.hms.Hotel.guestsList.lastEntry().getKey() + 1, this);
        }
        else{ 
            this.id = 1;
            com.hms.Hotel.guestsList.put(1, this);
        }
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        super.printDetails();
        if (aadharNumber != "")
            System.out.print("Aadhar Number   : " + aadharNumber + "\n");
        System.out.print("Rooms Booked    : " + roomNumbers + "\n");
        return;
    }

    

    public void getDetails() {
        Guest tempGuest = new Guest();
        boolean over = false;
        while (!over) {
            System.out.print("\nSearch using: (select one of the following options)\n1. ID\n2. Name\n3. Mobile Number\n4. Aadhar Number\n");
            Scanner cin = new Scanner(System.in);
            String inp;
            inp = cin.next();
            // inp += cin.nextLine();
            Integer choice = Integer.parseInt(inp);
            String identification;
            identification = cin.next();
            // identification += cin.nextLine();
            switch (choice) {
            case 1: {
                if (com.hms.Hotel.guestsList.containsKey(Integer.parseInt(identification))) {
                    tempGuest = com.hms.Hotel.guestsList.get(Integer.parseInt(identification));
                    break;
                } else {
                    System.out.print("\nNo such guest exists.\n");
                    continue;
                }
            }
            case 2: {
                Vector<Guest> matchingRecords = new Vector<>();
                for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet()) {
                    if (entry.getValue().name.equals(identification)) {
                        matchingRecords.add(entry.getValue());
                        break;
                    }
                }
                if (matchingRecords.size() == 0) {
                    System.out.print("\nNo such guest exists.\n");
                    continue;
                } else if (matchingRecords.size() == 1) {
                    tempGuest = matchingRecords.get(0);
                    break;
                } else {
                    System.out.print("\nMultiple matching records found.\n");
                    for (int i = 0; i < matchingRecords.size(); i++) {
                        System.out.print("\n" + (i + 1) + ". ");
                        matchingRecords.get(i).printDetails();
                    }
                    Integer ch = -1;
                    while (ch > matchingRecords.size() || ch < 1) {
                        System.out.print("\nEnter the number of the record you want to choose: ");
                        ch = Integer.parseInt(cin.nextLine());
                        continue;
                    }
                    tempGuest = matchingRecords.get(ch - 1);
                    break;
                }
            }
            case 3: {
                Vector<Guest> matchingRecords = new Vector<>();
                for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet()) {
                    if (entry.getValue().mobNumber.equals(identification)) {
                        matchingRecords.add(entry.getValue());
                        break;
                    }
                }
                if (matchingRecords.size() == 0) {
                    System.out.print("\nNo such guest exists.\n");
                    continue;
                } else if (matchingRecords.size() == 1) {
                    tempGuest = matchingRecords.get(0);
                    break;
                } else {
                    System.out.print("\nMultiple matching records found.\n");
                    for (int i = 0; i < matchingRecords.size(); i++) {
                        System.out.print("\n" + (i + 1) + ". ");
                        matchingRecords.get(i).printDetails();
                    }
                    Integer ch = -1;
                    while (ch > matchingRecords.size() || ch < 1) {
                        System.out.print("\nEnter the number of the record you want to choose: ");
                        ch = Integer.parseInt(cin.nextLine());
                        continue;
                    }
                    tempGuest = matchingRecords.get(ch - 1);
                    break;
                }
            }
            case 4: {
                for (Map.Entry<Integer, Guest> entry : com.hms.Hotel.guestsList.entrySet()) {
                    if (entry.getValue().aadharNumber.equals(identification)) {
                        tempGuest = entry.getValue();
                        break;
                    }
                }
            }
            }
            over = true;
        }

        if (tempGuest.id != -1) {
            this.id = tempGuest.id;
            this.aadharNumber = tempGuest.aadharNumber;
            this.age = tempGuest.age;
            this.cat = tempGuest.cat;
            this.gender = tempGuest.gender;
            this.mobNumber = tempGuest.mobNumber;
            this.name = tempGuest.name;
            this.roomNumbers = tempGuest.roomNumbers;
        } else
            return;
    }

}