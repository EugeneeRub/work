package com.ForWork.lab5;

import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static String text = null; // text from console
    private static StringBuilder builder[] = null; // result text
    private static MyArrayConteiner list = new MyArrayConteiner();// container

    public static void main(String[] args) {
        boolean flag = true; // flag for loop
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("input the command(-h or -help for help):");
            String name = in.next();
            switch (name) {
                case "input":
                    text = null;
                    builder = null;
                    list.clear();
                    StringHelper.clearData();
                    System.out.println("All data has been clean");
                    System.out.println("Input data");
                    inputMethod();
                    break;
                case "seeData":
                    System.out.println("See data");
                    seeDataMethod();
                    break;
                case "work":
                    System.out.println("Work process");
                    workMethod();
                    break;
                case "seeResult":
                    System.out.println("Display result");
                    seeResultMethod();
                    break;
                case "stop":
                    System.out.println("Stop the program");
                    flag = false;
                    break;
                case "delByObj":
                    System.out.println("Deleting new element from Container");
                    delElem();
                    break;
                case "addObj":
                    System.out.println("Adding new element to Container");
                    addElem();
                    break;
                case "toString":
                    System.out.println("Print data like toString");
                    System.out.println(list.toString());
                    break;
                case "getSize":
                    System.out.println("Getting size form Container");
                    System.out.println("Size of container = " + list.size());
                    break;
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
            }
        }while (flag);
    }

    /**
     * Method delete element by argument
     * argument may be anyone type of objects
     * be careful with this!!!!!!!!!!!!!!!!
     * */
    private static void delElem() {
        if (list.size() != 0){
            System.out.println("input the text that will be delete");
            Scanner in1 = new Scanner(System.in);
            String str = in1.nextLine();
            if (list.remove(str))
                System.out.println("Done");
            else
                System.out.println("Not done");
        } else {
            System.out.println("Can`t to delete object, list is empty");
        }
    }

    /**
     * Method that add new elment to list
     * */
    private static void addElem() {
        System.out.println("input the text that will be add");
        Scanner in1 = new Scanner(System.in);
        String str = in1.nextLine();
        if (list.add(str))
            System.out.println("Done");
        else
            System.out.println("Not done");
    }

    /**
     * Method checked text, then
     * get array of symbols from text.toCharArray(), then
     * call cutText() for cut text :), then
     * get ready text from createNewText
     * */
    private static void workMethod() {
        if (list.size() == 0){
            System.out.println("Texts are empty");
            return;
        }
        int counter = 0;
        builder = new StringBuilder[list.size()];
        for(Iterator<String> it = list.iterator(); it.hasNext(); ){
            char[] array = (it.next()).toCharArray();// array of words that was converted from text
            StringHelper.cutText(array);// call utility class for cut
            int len = 5;
            builder[counter] = StringHelper.createNewText(len);// get result
            counter++;
        }

    }

    /**
     * Method call cutText() for cut text :), then
     * get array of StringBuilders, then
     * checked by flag-mode, then
     * print data that contains in the arrayBuilder

     * */
    private static void seeDataMethod() {
        if (list.size() == 0) {
            System.out.println("Data is absent");
            return;
        }
        System.out.println("Data:");
        for (Object sb : list) {
            System.out.println(sb);
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
        for (Object sb : list) {
            System.out.println(sb);
        }
        System.out.println("Texts after work:");
        for (int i = 0; i < builder.length; i++) {
            System.out.println(builder[i].toString());
        }

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
        System.out.print("\"input\" - input text\n");
        System.out.print("\"seeData\" - see data\n");
        System.out.print("\"work\" - start handle method \n");
        System.out.print("\"seeResult\" - see result after handle method\n");
        System.out.print("\"stop\" - stop program\n");
        System.out.print("\"-h\" or \"-help\" - get help info\n");
        System.out.print("-------------- for container --------------------");
        System.out.print("\"delByObj\" - delete element from container\n");
        System.out.print("\"addObj\" - add element to container\n");
        System.out.print("\"toString\" - get String object from container\n");
        System.out.print("\"contains\" - check that method contain in container\n");
        System.out.print("\"getSize\" - get size of container\n");
    }
}
