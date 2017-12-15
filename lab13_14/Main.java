package com.ForWork.lab13_14;

import com.ForWork.lab11.NotMatchData;
import com.ForWork.lab11.ReadWithRegex;
import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab9.LinkedConteiner;

import java.io.File;
import java.util.ArrayList;

/**
 * Main class that call special class for work with threads
 *
 * @author Milena Davydova
 * Data 15.12.2017
 * */
public class Main {
    private static ArrayList<ClientInfo> list;// list of clients

    public static void main(String[] args) {
        createList();// call this method to load data from .json file
        MainWork workClass = new MainWork(list);
        workClass.work();
    }

    /**
     * Method that create new list from file
     * this method call special reader that get
     * information from .json file
     * */
    private static void createList(){
        try {
            LinkedConteiner<ClientInfo> _list = ReadWithRegex.readFromFile(new File("D:\\nclients.json"));
            list = new ArrayList<>();
            if (_list != null)
                list.addAll(_list);
        } catch (NotMatchData exeption) {
            exeption.printError();
            list = null;
        }
    }
}
