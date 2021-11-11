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
        // creating a thread to read from CSV files;
        ReaderThread readerThread = new ReaderThread();
        Thread thread = new Thread(readerThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ---------------------------

        // main(); Body
        
        FileHandling.writeToCSV();
    }
}