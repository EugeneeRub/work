package com.ForWork.lab11;

/**
 * Class-exception
 *
 * Created for special regex exeption
 *
 * @author Milena Davydova
 * Data 10.12.2017
 * */
public class NotMatchData extends Exception {
    private String text = "";//text
    NotMatchData(String text){
        this.text = text;
    }

    public void printError(){
        System.err.println("Data that must match, doesn`t match in this text =======>  " + "\"" + text + "\"");
    }
}
