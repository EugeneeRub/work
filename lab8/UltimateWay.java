package com.ForWork.lab8;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that update the way of file
 *
 * @author Milena Davydova
 * Data 24.11.2017
 * */
class UltimateWay {

     /**
      * Made to don`t let initialize this class
      * */
    private UltimateWay(){ }

     /**
      * Method that generate way from console
      *
      * @param way way that input by user
      * @return String return way
      * */
    public static String getUltimateWay(String way) throws FileNotFoundException {
        File file = new File(way);
        Scanner in = new Scanner(System.in);
        if (file.isFile())
            return way;
        else if (!file.isDirectory())
            throw new FileNotFoundException();
        StringBuilder changebleWay = new StringBuilder(file.getPath());
        do {
            System.out.println("Текущий путь: " + changebleWay.toString());
            System.out.println("Список файлов и директорий:");
            String[] list = file.list();
            {
                //печатаем список файлов
                if (list != null) {
                    System.out.println("Список файлов:");
                    for (String s1 : list) {
                        System.out.println(s1);
                    }
                }
            }
            System.out.print("Папка или файл находящиеся в тек.папке: ");
            String str = in.nextLine();
            if (str.equals("..")) {
                file = file.getParentFile();
                changebleWay = new StringBuilder(file.getPath());
                continue;
            }
            changebleWay.append("\\").append(str);
            file = new File(changebleWay.toString());
            if (file.isFile()) {
                return file.getPath();
            } else if (file.isDirectory()) {
                file = new File(changebleWay.toString());
            } else {
                changebleWay.delete(changebleWay.length() - str.length(), changebleWay.length());
            }
        } while (true);
    }
}
