package com.hms;

import java.util.*;
import java.io.*;

public class Address {
    private String line1, line2;
    private String city;
    private String state;
    private String pinCode;
    private String country;

    public Address() {
        line1 = line2 = city = state = pinCode = country = "";
    }

    public void takeInput() throws IOException{
      //  BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        Scanner in=new Scanner(System.in);
        System.out.print("\nEnter Address:\n");
        System.out.print("\nEnter Line 1:\n");
        line1=in.next();
        line1+=in.nextLine();
        System.out.print("\nEnter Line 2:\n");
        line2=in.nextLine();
        System.out.print("\nEnter City:\n");
        city=in.next();
        city+=in.nextLine();
        System.out.print("\nEnter State:\n");
        state=in.next();
        state+=in.nextLine();
        System.out.print("\nEnter Pincode:\n");
        Pincode=in.next();
        Pincode+=in.nextLine();
        System.out.print("\nEnter Country:\n");
        country=in.next();
        country+=in.nextLine();
        return;
    }

    public void print() {
        System.out.println("\nLine 1:"+" "+line1);
        System.out.println("Line 2: "+line2);
        System.out.println("City: "+city);
        System.out.println("State: "+state);
        System.out.println("Pincode: "+Pincode);
        System.out.println("Country: "+country);
        return;
    }

    public void strToAdd(String add) {
        String[]arr=add.split('`');
        line1=arr[0];
        line2=arr[1];
        city=arr[2];
        state=arr[3];
        Pincode=arr[4];
        country=arr[5];
        return;
    }

    public String addToStr() {
        String add = "";
        add+=line1;
        add+='`';
        add+=line2;
        add+='`';
        add+=city;
        add+='`';
        add+=state;
        add+='`';
        add+=Pincode;
        add+='`';
        add+=country;
        add+='`';
        for(int i=0;i<add.size();i++)
        {
            if(add.CharAt(i)==',')add.CharAt(i)='^';
        }
        return add;
    }

  

}