package com.ForWork.lab10;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;
import com.ForWork.lab9.LinkedConteiner;

import java.io.IOException;
import java.util.Scanner;

import static com.ForWork.lab9.Main.deserializeData;

/**
 * Main class that start the work with classes
 *
 * @author Milena Davydova
 * Data 10.12.2017
 * */
public class Main {
    private static LinkedConteiner<ClientInfo> conteiner = null;
    private static String arrayOfCommand[] = {"-seeData", "-addData", "-removeData", "-stop", "-serializeData",
            "-deserializeData", "-createContein","-contain", "-sort"};// array of command

    public static void main(String[] args) {
        ConsoleWork work = new ConsoleWork();// class for working with console
        boolean mAutoFlag = false; // flag for single/auto work
        boolean flag = true;// flag for stop method
        Scanner in;// read data fom console

        if (args.length != 0) {
            if (args[0].equals("-auto"))
                mAutoFlag = true;
        }
        do {
            String n;
            if (!mAutoFlag) {
                System.out.println("Please, input the command(input \'-help\' for help)");
                in = new Scanner(System.in);
                n = in.next();
            }else
                n = arrayOfCommand[(int) (0 + Math.random()*arrayOfCommand.length)];
            switch (n) {
                case "-seeData":
                    System.out.println("------SeeData------");
                    com.ForWork.lab9.Main.printData(conteiner);
                    break;
                case "-addData":
                    System.out.println("------AddData------");
                    if (!mAutoFlag) {
                        conteiner = com.ForWork.lab9.Main.addData(conteiner);
                        com.ForWork.lab9.Main.serializeData(getWaySerializeble(!mAutoFlag), conteiner);
                    } else {
                        if (conteiner == null)
                            conteiner = new LinkedConteiner<>();
                        conteiner.add(getClientRandom());
                        com.ForWork.lab9.Main.serializeData(getWaySerializeble(mAutoFlag), conteiner);
                    }
                    break;
                case "-removeData":
                    System.out.println("------RemoveData------");
                    if (!mAutoFlag)
                        conteiner = com.ForWork.lab9.Main.removeData(conteiner);
                    else
                        conteiner = removeDataWithFlag(conteiner);
                    break;
                case "-serializeData":
                    System.out.println("------SerializeData------");
                    if (conteiner != null && conteiner.size() != 0)
                        com.ForWork.lab9.Main.serializeData(getWaySerializeble(mAutoFlag), conteiner);
                    break;
                case "-deserializeData":
                    System.out.println("------DeserializeData------");
                    try {
                        conteiner = com.ForWork.lab9.Main.deserializeData(getWaySerializeble(mAutoFlag));
                    } catch (IOException e) {
                        System.err.println("Can`t find file");
                    }
                    break;
                case "-createContein":
                    System.out.println("------CreateContein------");
                    conteiner = com.ForWork.lab9.Main.createContein(conteiner);
                    break;
                case "-contain":
                    System.out.println("------Contain------");
                    if (conteiner != null && conteiner.size() != 0) {
                        if (!mAutoFlag){
                            try {
                                if (conteiner.contains(work.addClientFromConsole()))
                                    System.out.println("Have same objects");
                                else
                                    System.out.println("Don`t have same objects");
                            } catch (Exception e) {
                                System.out.println("Something wrong");
                            }
                        }else{
                            if (conteiner.contains(getClientRandom()))
                                System.out.println("Have same objects");
                            else
                                System.out.println("Don`t have same objects");
                        }
                    } else
                        System.out.println("Container is null or has 0 elements");
                    break;
                case "-auto":
                    System.out.println("------Auto------");
                    if (mAutoFlag) {
                        System.out.println("Changed to single work");
                        mAutoFlag = false;
                    } else {
                        System.out.println("Changed to auto work");
                        mAutoFlag = true;
                    }
                    break;
                case "-sort":
                    System.out.println("------Sort------");
                    if (conteiner != null && conteiner.size() != 0)
                        chooseSort(mAutoFlag);
                    else
                        System.out.println("Container is null");
                    break;
                case "-help":
                    System.out.println("------Help------");
                    help();
                    break;
                case "-stop":
                    System.out.println("------Stop------");
                    flag = false;
                    break;
                default:
                    System.err.println("Incorrect command!!!");
                    System.err.println("Try again");
                    break;
            }
        } while (flag);
    }

    /**
     * chooseSort method.
     * Method that sort list by choosing the way of sorting
     * and way of sorting
     *
     * @param flag point us about single/auto work
     * */
    private static void chooseSort(boolean flag) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        TypeSorting ex = null;
        System.out.println("Please, choose the variant of sort");
        System.out.println("1 - sort by date registration");
        System.out.println("2 - sort by count of about me");
        System.out.println("3 - sort by count of pretend to partner");
        if (!flag)
            n = in.nextInt();
        else
            n = (int)(1 + Math.random() * 3);
        switch (n) {
            case 1:
                ex = MethodsSort::isEqualByRegistration;
                break;
            case 2:
                ex = MethodsSort::isEqualByCountOFAboutMe;
                break;
            case 3:
                ex = MethodsSort::isEqualByCountOFPretendToPartner;
                break;
            default:

        }
        boolean flagForChoose = false;
        System.out.println("Please, choose the side of sorting method");
        System.out.println("By default sorting will be from min to max");
        System.out.println("1 - from max to min");
        System.out.println("2 - from min to max");
        if (!flag)
            n = in.nextInt();
        else
            n = (int)(1 + Math.random() * 1);
        switch (n) {
            case 1:
                flagForChoose = true;
                break;
            case 2:
                flagForChoose = false;
                break;
        }
        sort(conteiner, ex, flagForChoose);
    }

    /**
     * sort method.
     * Method that sort container by special function
     *
     * @param conteiner list of objects
     * @param ex address of function that will be called in sorting time
     * @param flagForChoose point us about single/auto work
     * */
    private static <K extends ClientInfo> LinkedConteiner<K> sort(LinkedConteiner<K> conteiner, TypeSorting ex, boolean flagForChoose) {
        LinkedConteiner.Node firstwalker = conteiner.getNode();
        while (firstwalker.hasNext()) {
            LinkedConteiner.Node secondWalker = firstwalker.getNext();
            while (secondWalker != null) {
                if (ex.isEqual(firstwalker.getItem(), secondWalker.getItem(), flagForChoose)) {
                    LinkedConteiner.Node buffer = conteiner.newInstance();
                    buffer.setItem(firstwalker.getItem());
                    firstwalker.setItem(secondWalker.getItem());
                    secondWalker.setItem(buffer.getItem());
                }
                secondWalker = secondWalker.getNext();
            }
            firstwalker = firstwalker.getNext();
        }
        return conteiner;
    }

    /**
     * removeDataWithFlag method.
     * Method return the way from console or from static key
     *
     * @param conteiner list that will be update by deleting object
     * @return LinkConteiner - list that was update
     * */
    private static LinkedConteiner<ClientInfo> removeDataWithFlag(LinkedConteiner<ClientInfo> conteiner) {
        if (conteiner != null) {
            if (!(conteiner.size() == 0)){
                try {
                    conteiner.removeByIndex((int)(0 + Math.random() * (conteiner.size()-1)));
                } catch (Exception e) {
                    System.out.println("Something is happened");
                }
            }else
                System.out.println("Container is null or has 0 elements");
        }else
            System.out.println("Container is null");
        return conteiner;
    }

    /**
     * getWaySerializeble method.
     * Method return the way from console or from static key
     *
     * @param mAutoFlag flag that point us about single-work or auto-work
     * @return String - way to file
     * */
    public static String getWaySerializeble(boolean mAutoFlag) {
        String str;
        if (!mAutoFlag) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please input the way for file");
            str = in.next();
        } else {
            str = "D:\\autogenerate.dat";
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
    public static ClientInfo getClientRandom() {
        LinkedConteiner<ClientInfo> list = null;
        try {
            list = deserializeData(getWaySerializeble(true));
        } catch (IOException e) {
            return null;
        }
        int index = (int) (0 + Math.random() * (list.size()-1));
        return list.get(index);
    }

    /**
     * Method help to print information about command
     */
    private static void help() {
        System.out.println("Команда -seeData - показывает данные контейнера");
        System.out.println("Команда -addData - добавление элемента");
        System.out.println("Команда -removeData - удаление элемента");
        System.out.println("Команда -serializeData - запись в файл(Сериализация)");
        System.out.println("Команда -deserializeData - чтение в файл(Десериализация)");
        System.out.println("Команда -createContein - создание контейнера");
        System.out.println("Команда -contain - проверка элемента на присутствие в массиве");
        System.out.println("Команда -sort - для сортировки");
        System.out.println("Команда -auto - включает авто работу");
        System.out.println("Команда -stop - завершение програмы");

    }
}