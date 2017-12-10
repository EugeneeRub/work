package com.ForWork.lab7;

/**
 * Class that make special print
 *
 * @author Milena Davydova
 * Data 12.11.2017
 * */
class CorrectPrint{
    private int arr[] = null;// array of width of objects
    private int index = 0;// index that say that number we must take from arr[]
    private String str = null;// string for get size and decrement lenght
    private boolean flag = true;// flag for first element

    /**
     * Constructor that set array of padding
     * */
    CorrectPrint(int []array){
        arr = array;
    }

    /**
     * Method that print in line words that are equals by width
     *
     * @param elem object that will be input in line
     * */
    public void printLine(Object elem) {
        if (index == arr.length) {
            index = 0;
            flag = true;
        }
        if (flag) {
            System.out.print(elem);
            str = elem.toString();
            flag = false;
        } else {
            int len = arr[index] - str.length();
            for (int i = 0; i < len; i++) {
                System.out.print(" ");
            }
            str = elem.toString();
            System.out.print(elem);
            index++;
        }
    }
}