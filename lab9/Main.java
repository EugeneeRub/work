package com.ForWork.lab9;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;

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
public class Main {

    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();
        LinkedConteiner<ClientInfo> conteiner = null;
        Object[] array = null;
        Scanner in = null;
        boolean flag = true;

        do {
            System.out.println("Please, input the command(input \'-help\' for help)");
            in = new Scanner(System.in);
            String n = in.next();
            switch (n) {
                case "-seeData":
                    printData(conteiner);
                    break;
                case "-addData":
                    conteiner = addData(conteiner);
                    break;
                case "-removeData":
                    conteiner = removeData(conteiner);
                    break;
                case "-serializeData":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file");
                    serializeData(in.next(), conteiner);
                    break;
                case "-deserializeData":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file");
                    try {
                        conteiner = deserializeData(in.next());
                    } catch (IOException e) {
                        System.err.println("Can`t find file");
                    }
                    break;
                case "-xmlEncode":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file(for \'.xml\' file)");
                    encodeData(in.next(), conteiner);
                    break;
                case "-xmlDecode":
                    in = new Scanner(System.in);
                    System.out.println("Please input the way for file(for \'.xml\' file)");
                    conteiner = decodeData(in.next());
                    break;
                case "-createContein":
                    conteiner = createContein(conteiner);
                    break;
                case "-toString":
                    if (conteiner != null || conteiner.size() == 0)
                        System.out.println(conteiner.toString());
                    else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-toArray":
                    array = conteiner.toArray();
                    break;
                case "-contain":
                    if (conteiner != null) {
                        try {
                            if (conteiner.contains(work.addClientFromConsole())) {
                                System.out.println("Found same clients");
                            } else
                                System.out.println("Don`t have same objects");
                        } catch (Exception ex) {
                            System.err.println("Something wrong");
                        }
                    } else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-containAll":
                    if (conteiner != null)
                        conteiner.containsAll(createListOfNewElements());
                    else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-help":
                    help();
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect command!!!");
                    System.out.println("Try again");
                    break;
            }
        } while (flag);
    }

    /**
     * Method that create new fresh list without
     * elements and saved last data to special file
     *
     * @param conteiner in this list will be added new element
     * @return conteiner list of elements
     **/
    public static LinkedConteiner<ClientInfo> createContein(LinkedConteiner<ClientInfo> conteiner) {
        System.err.println("Warning!!!");
        System.err.println("If you have elements in container, it will be lost");
        System.err.println("So programme saved your last data, on \'D:\\saved.dat\'");
        if (conteiner != null)
            serializeData("D:\\saved.dat", conteiner);
        conteiner = new LinkedConteiner<>();
        System.out.println("Conteiner are created!");
        return conteiner;
    }

    /**
     * Method that remove elements by index
     *
     * @param conteiner in this list will be added new element
     * @return conteiner list of elements
     **/
    public static LinkedConteiner<ClientInfo> removeData(LinkedConteiner<ClientInfo> conteiner) {
        Scanner in;
        if (conteiner != null) {
            if (conteiner.size() != 0) {
                in = new Scanner(System.in);
                System.out.println("Input the index:");
                try {
                    conteiner.removeByIndex(in.nextInt());
                } catch (Exception ex) {
                    System.err.println("Something wrong");
                }
                System.out.println("Done!");
            } else
                System.err.println("Container has 0 elements");
        } else
            System.err.println("Container is null");
        return conteiner;
    }

    /**
     * Method that add new element
     * and return new/fresh list of elemtns
     *
     * @param conteiner in this list will be added new element
     **/
    public static LinkedConteiner<ClientInfo> addData(LinkedConteiner<ClientInfo> conteiner) {
        ConsoleWork work = new ConsoleWork();
        if (conteiner == null)
            conteiner = new LinkedConteiner<>();
        try {
            conteiner.add(work.addClientFromConsole());
        } catch (Exception ex) {
            System.err.println("Something wrong");
        }

        return conteiner;
    }

    /**
     * Method that create new list of clients
     *
     * @return list of new elements
     */
    public static LinkedConteiner<ClientInfo> createListOfNewElements() {
        LinkedConteiner<ClientInfo> list = new LinkedConteiner<>();// new conteiner
        ConsoleWork work = new ConsoleWork();// object to print on console
        Scanner in = new Scanner(System.in);// scanner to get data from console
        boolean flag = true;// flag to continue the work in loop

        do {
            switch (in.next()) {
                case "-add":
                    try {
                        list.add(work.addClientFromConsole());
                    } catch (Exception ex) {
                        System.err.println("Incorrect data");
                    }
                    break;
                case "-stop":
                    flag = false;
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
        } while (flag);
        return list;
    }

    /**
     * Method that print clients to console
     * by special class
     *
     * @param conteiner list of clients
     */
    public static void printData(LinkedConteiner<ClientInfo> conteiner) {
        ConsoleWork work = new ConsoleWork();
        if (conteiner == null || conteiner.size() == 0) {
            System.out.println("Conteiner are empty");
            return;
        }
        for (ClientInfo p : conteiner) {
            work.printClient(p);
        }
    }

    /**
     * Method that serialize list to .dat file
     *
     * @param way       way to .dat file
     * @param conteiner list of clients
     */
    public static void serializeData(String way, LinkedConteiner<ClientInfo> conteiner) {
        if (conteiner == null)
            return;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(conteiner);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }

    /**
     * Method that encode list to xml file
     * by using the loop for load all data to file
     *
     * @param way       way to .xml file
     * @param conteiner list of clients
     */
    public static void encodeData(String way, LinkedConteiner<ClientInfo> conteiner) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(way));
            for (ClientInfo info : conteiner)
                encoder.writeObject(info);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }

    /**
     * Method that deserialize data from file to list
     *
     * @param way way to .dat file
     * @return conteiner - list of ready clients
     */
    public static LinkedConteiner<ClientInfo> deserializeData(String way) throws IOException {
        LinkedConteiner<ClientInfo> conteiner = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(way));
            conteiner = (LinkedConteiner<ClientInfo>) is.readObject();
            is.close();
        } catch (IOException e) {
            throw new IOException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        return conteiner;
    }

    /**
     * Method that decode list from xml file
     *
     * @param way way to .xml file
     * @return conteiner - list of ready clients
     */
    public static LinkedConteiner<ClientInfo> decodeData(String way) {
        LinkedConteiner<ClientInfo> conteiner = new LinkedConteiner<>();
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(way))) {
            while (true) {
                ClientInfo info = (ClientInfo) decoder.readObject();
                conteiner.add(info);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        System.out.println("Done!");
        return conteiner;
    }

    /**
     * Method help to print information about command
     */
    public static void help() {
        System.out.println("Команда -seeData - показывает данные контейнера");
        System.out.println("Команда -addData - добавление элемента");
        System.out.println("Команда -removeData - удаление элемента");
        System.out.println("Команда -serializeData - запись в файл(Сериализация)");
        System.out.println("Команда -deserializeData - чтение в файл(Десериализация)");
        System.out.println("Команда -xmlEncode - запись в xml файл");
        System.out.println("Команда -xmlDecode - чтение из xml файла");
        System.out.println("Команда -createContein - создание контейнера");
        System.out.println("Команда -toString - перевод в стринг строку");
        System.out.println("Команда -toArray - перевод в массив");
        System.out.println("Команда -contain - проверка элемента на присутствие в массиве");
        System.out.println("Команда -containAll - проверка на єлементы");
    }
}
