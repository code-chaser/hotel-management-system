package com.hms.persons;

import java.util.*;
import java.io.*;

abstract class Person {
    protected Integer id;
    protected String name;
    protected Integer age;
    protected Character gender;
    protected String mobNumber;
    protected com.hms.persons.Address add;
    protected String cat;

    public Person() {
        id = -1;
    }

    public Person(Integer id, String name, Integer age, Character gender, String mobNumber, com.hms.persons.Address add,
            String cat) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobNumber = mobNumber;
        this.add = add;
        this.cat = cat;
    }

    public void addPerson(Integer minAge, Integer maxAge) {
        Scanner cin = new Scanner(System.in);
        String inp;
        System.out.print("\nEnter name:\n");
        inp = cin.next();
        inp += cin.nextLine();
        name = inp;
        System.out.print("\nEnter age:\n");
        boolean flag = true;
        while (flag) {
            inp = cin.next();
            inp += cin.nextLine();
            try {
                age = Integer.parseInt(inp);
                if (age <= 0)
                    System.out.print("Please enter a valid age:\n");
                else if (age < minAge) {
                    System.out.print("\nSorry, person should be atleast " + minAge
                            + " years old to be registered as a new " + cat + ".\n");
                    this.id = -2;
                    return;
                } else if (age > maxAge) {
                    System.out.print(
                            "\nSorry, we can't register a person older than " + maxAge + " years as a " + cat + ".\n");
                    this.id = -2;
                    return;
                } else
                    flag = false;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer age:\n");
            }
        }
        System.out.print("\nGender (M = Male || F = Female):\n");
        inp = "X";
        while (!inp.equals("M") && !inp.equals("F")) {
            inp = cin.next();
            inp += cin.nextLine();
            gender = inp.charAt(0);
            if (!inp.equals("M") && !inp.equals("F"))
                System.out.print("M or F?\n");
        }
        System.out.print("\nEnter mobile number (with country code):\n");
        inp = cin.next();
        inp += cin.nextLine();
        mobNumber = inp;
        add = new com.hms.persons.Address();
        add.takeInput();
        return;
    }

    public void printDetails() {
        if (id == -1)
            return;
        System.out.print("ID              : " + id + "\n");
        System.out.print("Name            : " + name + "\n");
        System.out.print("Gender          : " + gender + "\n");
        System.out.print("Age             : " + age + "\n");
        System.out.print("Mobile          : " + mobNumber + "\n");
        System.out.print("Address         : ");
        add.print();
        return;
    }

    public void assign(Person p) {
        id = p.id;
        name = p.name;
        age = p.age;
        gender = p.gender;
        mobNumber = p.mobNumber;
        add = p.add;
        cat = p.cat;
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

    public void setMobileNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Character getGender() {
        return gender;
    }

    public String getMobileNumber() {
        return mobNumber;
    }

    public Address getAddress() {
        return add;
    }

    public String getCategory() {
        return cat;
    }

    public abstract void getDetails();
}
