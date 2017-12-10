package com.ForWork.lab8;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import com.ForWork.lab7.*;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Scanner;

/**
 * Start class that start the work
 *
 * @author Milena Davydova
 * Data 24.11.2017
 * */
public class MainClass {

    public static void main(String[] args) {
        boolean flag = true;
        Bureau cardBoard = new Bureau();
        Scanner in = new Scanner(System.in);
        ConsoleWork work = new ConsoleWork();

        do{
            System.out.println("Введите комманду:");
            String command = in.next();
            switch (command){
                case "-add":
                    try {
                        cardBoard.addClient(work.addClientFromConsole());
                    } catch (Exception e) {
                        System.out.println("Something wrong");
                    }
                    break;
                case "-remove":
                    in = new Scanner(System.in);
                    System.out.print("Введите номер клиента: ");
                    cardBoard.removeClient(in.nextInt());
                    break;
                case "-print":
                    work.printArray(cardBoard);
                    break;
                case "-xmlto":
                    System.out.print("Введите путь для файла: ");
                    String str1 = getWay();
                    if (str1 == null){
                        System.out.println("Повторите попытку позже");
                        break;
                    }
                    setClientsToXml(cardBoard,str1);
                    System.out.println("Готово");
                    break;
                case "-xmlfrom":
                    System.out.print("Введите путь для файла: ");
                    String str = getWay();
                    if (str == null){
                        System.out.println("Повторите попытку позже");
                        break;
                    }
                    cardBoard = getClientsFromXml(str);
                    System.out.println("Готово");
                    work.printArray(cardBoard);
                    break;
                case "-help":
                    helpInfo();
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

    /**
     * Method print information about commands
     * */
    private static void helpInfo(){
        System.out.println("Команда -add - добавление клиента");
        System.out.println("Команда -remove - удаление клиента");
        System.out.println("Команда -print - вывод информации о клиентах");
        System.out.println("Команда -xmlto - запись данных в .xml файл");
        System.out.println("Команда -xmlfrom - чтение данных из .xml файла");
    }

    /**
     * Method that call method generating way from console
     *
     * @return string return way
     * */
    private static String getWay(){
        Scanner in = new Scanner(System.in);

        String command = in.next();
        try {
            return UltimateWay.getUltimateWay(command);
        }catch (FileNotFoundException e){
            System.out.println("Не введен нормально путь к файлу");

        }
        return null;
    }

    /**
     * Method that get object from xml file
     *
     * @param way way to load data
     * @return clients object that will be taken from file
     * */
    private static Bureau getClientsFromXml(String way)  {
        Bureau clients = null;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(way)));
            clients = (Bureau) decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Method that set object to xml file
     *
     * @param clients data that will be load to file
     * @param way way to file
     * */
    private static void setClientsToXml(Bureau clients, String way) {
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(way)));
            encoder.writeObject(clients);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
