package com.ForWork;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Main class that work with domain objects
 *
 * @author Milena Davydova
 * Data 11.11.2017
 * */
public class Main {
    /**
     * started method
     * */
    public static void main(String[] args) {
        boolean flag = true;
        /**
         * set utf-8 encoding in writing/reading to/from console
         * */
        String str = System.getProperty("console.Encoding", "utf-8");
        if (str != null) {
            try {
                System.setOut(new PrintStream(System.out, true, str));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Bureau cardBoard = new Bureau();
        Scanner in = new Scanner(System.in);
        ConsoleWork work = new ConsoleWork();

        do{
            System.out.println("Введите комманду:");
            String command = in.next();
            switch (command){
                case "-add":
                    cardBoard.addClient(work.addClientFromConsole());
                    break;
                case "-remove":
                    in = new Scanner(System.in);
                    System.out.print("Введите номер заключенного: ");
                    cardBoard.removeClient(in.nextInt());
                    break;
                case "-print":
                    work.printArray(cardBoard);
                    break;
                case "-getByIndex":
                    in = new Scanner(System.in);
                    work.printClient(cardBoard.get(in.nextInt()));
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command");
                    System.out.println("Try again");
                    break;
            }
        }while(flag);
    }
}
