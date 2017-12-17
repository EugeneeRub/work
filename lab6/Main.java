package com.ForWork.lab6;

import com.ForWork.lab5.MyArrayConteiner;
import com.ForWork.lab5.StringHelper;
import lib.RubezhinClass;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static String text = null; // text from console
    private static StringBuilder builder[] = null; // result text
    private static MyArrayConteiner list = new MyArrayConteiner();// container
    public static void main(String[] args) {
        boolean flag = true; // flag for loop
        Scanner in = new Scanner(System.in);
        String mWayToPath;
        do {
            System.out.println("input the command(-h or -help for help):");
            String name = in.next();
            switch (name) {
                case "-input":
                    text = null;
                    builder = null;
                    list.clear();
                    //StringHelper.clearData();
                    System.out.println("All data has been clean");
                    System.out.println("Input data");
                    inputMethod();
                    break;
                case "-seeData":
                    System.out.println("See data");
                    seeDataMethod();
                    break;
                case "-work":
                    System.out.println("Work process");
                    workMethod();
                    break;
                case "-updateList":
                    System.out.println("Work with container");
                    workWithList();
                    break;
                case "-serialize":
                    System.out.println("Serialize process");
                    System.out.println("Input the way to path(type of file must end by \'name.bin\')");
                    in = new Scanner(System.in);
                    mWayToPath = in.nextLine();
                    serializeData(list,mWayToPath);
                    break;
                case "-deserialize":
                    System.out.println("Deserialize process");
                    System.out.println("Input the way to path(type of file must end by \'name.bin\')");
                    in = new Scanner(System.in);
                    mWayToPath = in.nextLine();
                    list = deserializeData(mWayToPath);
                    break;
                case "-seeResult":
                    System.out.println("Display result");
                    seeResultMethod();
                    break;
                case "-friend":

                    RubezhinClass work = new RubezhinClass(list);
                    int []mArray = work.raspredelenieTextData();// РІС‹Р·РѕРІ С„-С†РёРё СЂР°СЃРїСЂРµРґРµР»РµРЅРёСЏ СЃС‚СЂРѕРє
                    String []mArrayOfSmallText = work.findMinTextsFromTextArray();// РІС‹Р·РѕРІ С„-С†РёРё РЅР°С…РѕР¶РґРµРЅРёСЏ РјРёРЅРёРјР°Р»СЊРЅС‹С… СЃС‚СЂРѕРє РІ РіСЂСѓРїРїР°С…
			        /* Р’С‹РІРѕРґ СЂРµР·СѓР»СЊС‚Р° РЅР° РєРѕРЅСЃРѕР»СЊ */
                    System.out.println("РЎР°РјС‹Рµ РјР°Р»РµРЅСЊРєРёРµ СЃС‚СЂРѕРєРё");
                    for (int i = 0; i < mArray.length; i++) {
                        if(mArray[i] != 0){
                            System.out.println(mArrayOfSmallText[i] + " = " + mArrayOfSmallText[i].length() + " РґР»РёРЅРЅР° СЃС‚СЂРѕРєРё");
                        }
                    }
                case "-h":
                    printHelpInfo();
                    break;
                case "-help":
                    printHelpInfo();
                    break;
                default:
                    System.out.println("Incorrectly entered command!");
                    System.out.println("Try again");
                    break;
                case "-stop":
                    System.out.println("Stop the program");
                    flag = false;
                    break;
            }
        }while (flag);
    }

    /**
     * Method create a console work only with a container
     * if choose -h or -help, will be print opportunity of this method
     * */
    private static void workWithList(){
        Scanner in = new Scanner(System.in);
        String command;
        goFromLoop:
        while (true){
            System.out.println("Input the command for list(-help or -h for help): ");
            command = in.next();
            String str;
            switch (command){
                case "-addObj":
                    System.out.println("Add Data");
                    in = new Scanner(System.in);
                    str = in.nextLine();
                    list.add(str);
                    break;
                case "-delByObj":
                    System.out.println("Remove Data");
                    in = new Scanner(System.in);
                    str = in.nextLine();
                    list.remove(str);
                    break;
                case "-sortData":
                    System.out.println("Sort Data");
                    list.sort();
                    break;
                case "-clearData":
                    System.out.println("Clear Data");
                    list.clear();
                    break;
                case "-toString":
                    System.out.println(list.toString());
                    break;
                case "-getSize":
                    System.out.println(list.size());
                    break;
                case "-seeData":
                    System.out.println("See Data");
                    int counter = 0;
                    for (Object sstring : list){
                        System.out.println(counter + ". " + sstring);
                        counter++;
                    }
                    break;
                case "-help":
                    printHelpInfoForList();
                    break;
                case "-h":
                    printHelpInfoForList();
                    break;
                case "-exit":
                    break goFromLoop;
            }
        }
    }

    /**
     * Method that deserialize data from file to container
     * by the way that was send when this method was called
     * if InputStream can`t find file by the way, it throw exeption
     *
     * @param way string that has a way to future file
     * @return container - ready container after reading file by the way
     * */
    private static MyArrayConteiner deserializeData(String way)  {
        MyArrayConteiner list = null;

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(way));
            list = (MyArrayConteiner) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Method can`t read data from file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Method that serialize data from container
     * by the way that was send when this method was called
     * if InputStream can`t find file by the way, it throw exeption
     * or return if list are null
     *
     * @param list list that will be serialized
     * @param way string that has a way to future file
     * */
    private static void serializeData(MyArrayConteiner list, String way){

        if (list == null || list.size() == 0){
            System.out.println("You can`t use serialize, because list are empty or has not created");
        }
        // .dat
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(way));
            outputStream.writeObject(list);
            outputStream.close();
            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("Method can`t write data to file");
        }
    }

    /**
     * Method checked text, then
     * get array of symbols from text.toCarArray(), then
     * call cutText() for cut text :), then
     * get ready text from createNewText
     * */
    private static void workMethod() {
        Scanner in = new Scanner(System.in);
        if (list.size() == 0){
            System.out.println("Texts are empty");
            return;
        }
        int counter = 0;
        builder = new StringBuilder[list.size()];
        for(Object str : list){
            System.out.print("Input the lenght of word: ");
            int len = in.nextInt();
            char[] array =  ((String)str).toCharArray();// array of words that was converted from text
            StringHelper.cutText(array);// call utility class for cut
            builder[counter] = StringHelper.createNewText(len);// get result
            counter++;
        }

    }

    /**
     * Method call cutText() for cut text :), then
     * get array of StringBuilders, then
     * checked by flag-mode, then
     * print data that contains in the arrayBuilder
     *
     * */
    private static void seeDataMethod() {
        if (list.size() == 0) {
            System.out.println("Data is absent");
            return;
        }
        int counter = 0;
        System.out.println("Data:");
        for (Object sb : list) {
            System.out.println(counter + ". " + sb);
            counter++;
        }
    }

    /**
     * Method create scanner, then
     * get text from console
     * that`s all
     * */
    private static void inputMethod() {
        Scanner newIn = new Scanner(System.in);
        System.out.println("Input the text(input -stop to stop the process)");
        boolean flag = true;
        do {
            text = newIn.nextLine();
            if (!text.equals("-stop")){
                list.add(text);
            }else {
                flag = false;
            }
        }while(flag);
    }

    /**
     * Method check list, then
     * print result text, after calling workMethod
     * */
    private static void seeResultMethod() {
        if (list.size() == 0 || builder == null){
            System.out.println("Nothing to show");
            return;
        }
        System.out.println("Texts form console:");
        int counter = 1;
        for (Object sb : list) {
            System.out.println(counter + ". " + sb);
        }
        System.out.println("Texts after work:");
        for (int i = 0; i < builder.length; i++) {
            System.out.println(i + ". " + builder[i].toString());
        }

    }

    /**
     * Method that output in console info about container command by input -h or -help
     **/
    private static void printHelpInfoForList() {
        System.out.print("-------------- for container --------------------\n");
        System.out.print("\"-delByObj\" - delete element from container\n");
        System.out.print("\"-addObj\" - add element to container\n");
        System.out.print("\"-sortData\" - sort data\n");
        System.out.print("\"-clearData\" - clear all data from container\n");
        System.out.print("\"-seeData\" - see data\n");
        System.out.print("\"-toString\" - get String object from container\n");
        System.out.print("\"-contains\" - check that method contain in container\n");
        System.out.print("\"-getSize\" - get size of container\n");
    }

    /**
     * Method that output in console info by input -h or -help
     **/
    private static void printHelpInfo() {
        System.out.println("Author of program: Milena Davydova");
        System.out.println("Group: KIT-16b");
        System.out.print("Individual work:\nEnter text. ");
        System.out.print("From the text, delete all words of a ");
        System.out.print("given length beginning with an accented letter. ");
        System.out.print("Output the original text and result.\n");
        System.out.println("Accurate description that contain program");
        System.out.print("\"-input\" - input text\n");
        System.out.print("\"-seeData\" - see data\n");
        System.out.print("\"-work\" - start handle method \n");
        System.out.print("\"-updateList\" - update data in container \n");
        System.out.print("\"-seeResult\" - see result after handle method\n");
        System.out.println("\"-serialize\" - serialize data from list");
        System.out.println("\"-deserialize\" - deserialize data from file to list");
        System.out.print("\"-stop\" - stop program\n");
        System.out.print("\"-h\" or \"-help\" - get help info\n");
    }
}
