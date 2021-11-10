package com.hms;

import java.util.*;

import com.hms.fileHandling.FileHandling;

import java.io.*;

class ReaderThread implements Runnable {
    @Override
    public void run() {
        try {
            FileHandling.readFromCSV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Thread(new ReaderThread()).start();

        //Main Body
        
        FileHandling.writeToCSV();
    }
}