package com.ForWork.lab15;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static com.ForWork.lab10.Main.getWaySerializeble;

public class Main {

    private static String cArrayOfCommand[] = {"-add","-remove","-print","-sort",
            "-serialize","-deserialize","-encode","-decode","-stop"};// arrray of command

    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();// class console work
        boolean mAutoFlag = false;// flag for auto-single work
        boolean flag = true;// flag for main loop for getting console comand
        String mStr;// name of comand
        Scanner in;// scaner for read from console
        ArrayList<ClientInfo> list = new ArrayList<>();// list of clients

        if (args.length != 0) {
            if (args[0].equals("-auto"))
                mAutoFlag = true;
        }

        do {
            System.out.println("Пожалуйста, введите команду(введите \'-help\' для помощи)");
            if (!mAutoFlag) {
                in = new Scanner(System.in);
                mStr = in.next();
            } else {
                mStr = cArrayOfCommand[(int) (0 + Math.random() * cArrayOfCommand.length)];
            }
            switch (mStr) {
                case "-print":
                    System.out.println("------See Data------");
                    printData(list);
                    break;
                case "-add":
                    System.out.println("------Add Data------");
                    if (list == null)
                        break;
                    if (!mAutoFlag){
                        try {
                            list.add(work.addClientFromConsole());
                            serializeData(getWaySerializeble(true), list);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try{
                            list.add(getClientRandom());
                        }catch (NullPointerException ex){
                            System.out.println("Невозможно получить данные из autogenerate.dat");
                        }
                    }

                    break;
                case "-remove":
                    System.out.println("------Remove Data------");
                    if (list == null && list.size() < 1)
                        break;
                    if (!mAutoFlag){
                        in = new Scanner(System.in);
                        System.out.print("Введите индекс элемента ");
                        int index = in.nextInt();
                        try{
                            list.remove(index);
                        }catch (IndexOutOfBoundsException ex){
                            System.out.println("Невозможно удалить этот элемент");
                        }
                    } else {
                        try{
                            list.remove((int)(0 + Math.random()*(list.size()-1)));
                        }catch (IndexOutOfBoundsException ex){
                            System.out.println("Невозможно удалить этот элемент");
                        }
                    }
                    break;
                case "-sort":
                    System.out.println("------Sort Data------");
                    if (list != null && list.size() > 1)
                        list.sort(new myCompare());
                    else
                        System.out.println("Невозможно отсортировать");
                    break;
                case "-serialize":
                    System.out.println("------Serialize Data------");
                    if (list != null && list.size() != 0)
                        serializeData(getWaySerializeble(mAutoFlag), list);
                    else
                        System.out.println("Список пуст");
                    break;
                case "-deserialize":
                    System.out.println("------Deserialize Data------");
                    list = deserializeData(getWaySerializeble(mAutoFlag));
                    break;
                case "-encode":
                    System.out.println("------Encode------");
                    String str1 = getWayEncode(mAutoFlag);
                    if (str1 == null){
                        System.out.println("Повторите попытку позже");
                        break;
                    }
                    if (list != null && list.size() > 0)
                        encodeData(str1,list);
                    else
                        System.out.println("Список пуст");
                    break;
                case "-decode":
                    System.out.println("------Decode------");
                    String str2 = getWayEncode(mAutoFlag);
                    if (str2 == null){
                        System.out.println("Повторите попытку позже");
                        break;
                    }
                    list = decodeData(str2);
                    break;
                case "-auto":
                    System.out.println("------Auto/Single Mode------");
                    if (mAutoFlag) {
                        System.err.println("Смена на роботу с пользователем");
                        mAutoFlag = false;
                    } else {
                        System.err.println("Смена на автоматическую работу");
                        mAutoFlag = true;
                    }
                    break;
                case "-help":
                    help();
                    break;
                case "-stop":
                    System.out.println("------Stop Process------");
                    flag = false;
                    break;
                default:
                    System.out.println("Неправильно введена команда!!!");
                    System.out.println("Потвторите снова");
                    break;
            }
        }while (flag) ;
    }

    /**
     * Method that print data from list
     * */
    private static void printData(List<ClientInfo> list) {
        if (list == null || list.size() == 0)
            return;
        for (ClientInfo p : list){
            ConsoleWork.printClient(p);
        }
    }

    /**
     * Method tha load data to file
     * with serializzation
     *
     * @param list list of prisoners
     * @param way way to file
     * */
    private static void serializeData(String way, List<ClientInfo> list){
        if (list == null)
            return;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method tha load data from file
     * with serializzation
     *
     * @param way way to file
     * @return ArrayList - list of prisoners
     **/
    public static ArrayList<ClientInfo> deserializeData(String way){
        ArrayList<ClientInfo> conteiner = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(way));
            conteiner = (ArrayList<ClientInfo>) is.readObject();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conteiner;
    }

    /**
     * Method that decode list from xml file
     *
     * @param way way to .xml file
     * @return conteiner - list of ready clients
     */
    public static ArrayList<ClientInfo> decodeData(String way) {
        ArrayList<ClientInfo> conteiner = new ArrayList<>();
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(way))) {
            conteiner = (ArrayList<ClientInfo>) decoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        System.out.println("Done!");
        return conteiner;
    }

    /**
     * Method that encode list to xml file
     * by using the loop for load all data to file
     *
     * @param way       way to .xml file
     * @param conteiner list of clients
     */
    public static void encodeData(String way, ArrayList<ClientInfo> conteiner) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(way));
            encoder.writeObject(conteiner);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }

    /**
     * getWayEncode method
     *
     * Method return the way from console or from static key
     *
     * @param mAutoFlag flag that point us about single-work or auto-work
     * @return String - way to file
     * */
    private static String getWayEncode(boolean mAutoFlag){
        String str;
        Scanner in;
        if (!mAutoFlag) {
            in = new Scanner(System.in);
            System.out.println("Please input the way for file(for \'.xml\' file)");
            str = in.next();
        } else {
            str = "D:\\autogenerate.xml";
        }
        return str;
    }

    /**
     * getClientRandom method.
     * Method get list from serializeble file and when call get mthod in list,
     * by random return the ClientInfo object
     *
     * @return ClientInfo - random object from list
     * */
    public static ClientInfo getClientRandom() throws NullPointerException {
        ArrayList<ClientInfo> list;
        list = deserializeData(getWaySerializeble(true));
        if (list == null)
            throw new NullPointerException();
        int index = (int) (0 + Math.random() * (list.size()-1));
        return list.get(index);
    }

    /**
     * Method help to print information about command
     */
    private static void help() {
        System.out.println("Команда -print - показывает данные контейнера");
        System.out.println("Команда -add - добавление элемента");
        System.out.println("Команда -remove - удаление элемента");
        System.out.println("Команда -encode - запись в файл нестандартной сериализацией");
        System.out.println("Команда -decode - чтение из файла нестандартной сериализацией");
        System.out.println("Команда -serialize - запись в файл(Сериализация)");
        System.out.println("Команда -deserialize - чтение в файл(Десериализация)");
        System.out.println("Команда -sort - для сортировки");
        System.out.println("Команда -auto - включает авто работу");
        System.out.println("Команда -stop - завершение програмы");

    }

    /**
     * Class-Comparator that used to compare objects
     * for sorting list
     *
     * @author Rubezhin Evgeniy
     * Data 07.12.2017
     * */
    public static class myCompare implements Comparator<ClientInfo> {
        @Override
        public int compare(ClientInfo o1, ClientInfo o2) {
            if (o1.getMGrowth() < o2.getMGrowth())
                return -1;
            else if (o1.getMGrowth() > o2.getMGrowth())
                return 1;
            return 0;
        }
    }
}
