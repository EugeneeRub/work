package com.ForWork;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class created for console work,
 * use print and get methods for work with array
 *
 * @author Milena Davydova
 * Data 11.11.2017
 * */
public class ConsoleWork {

    /**
     * Method that create list of hobbies
     *
     * @return ClientInfo - object that contain all info about client
     * */
    public ClientInfo addClientFromConsole() {
        ClientInfo info = new ClientInfo();
        Scanner in = new Scanner(System.in);
        {
            System.out.print(" ИФО: ");
            info.setmName(in.nextLine());
            System.out.print(" День рождния: ");
            info.setmDateOfBirth(in.next());
            System.out.print(" Рост: ");
            info.setmGrowth(in.nextFloat());
            System.out.print(" Цвет глаз: ");
            info.setmColorEyes(in.next());
            System.out.print(" Пол: ");
            info.setmSex(in.next());
        }
        in = new Scanner(System.in);
        System.out.print(" Регистрационный номер: ");
        info.setmRegNumber(in.nextLine());
        System.out.print(" Дата регистрации: ");
        info.setmDateOfRegistr(in.next());
        System.out.println(" Хобби: (Введите -stop для прекращения записи)");
        info.setHobbi(getHobbiFromConsole());
        System.out.println(" Требования к кандидату: ");
        info.setmDemandsHuman(pretendToCandidat());
        return info;
    }

    /**
     * Method that create list of hobbies
     *
     * @return LinkedList - list of hobbies
     * */
    public static LinkedList<String> getHobbiFromConsole(){
        LinkedList<String> list = new LinkedList<>();
        Scanner in2 = new Scanner(System.in);
        boolean flag = true;
        do {
            String text = in2.nextLine();
            if(text.equals("-stop")){
                flag = false;
            }else
                list.add(text);
        } while (flag);
        return list;
    }

    /**
     * Method that print hobbies of person
     *
     * @param _list list of hobbies
     * */
    public static void printHobbies(LinkedList<String> _list){
        System.out.println();
        System.out.println("Хобби:");
        for (int j = 0; j < _list.size(); j++) {
            System.out.println((j + 1) + ") " + _list.get(j));
        }
    }

    /**
     * Method that create object that contain information about person
     *
     * @return Human - object that contain information about person
     * */
    public Human pretendToCandidat(){
        Human human = new Human();
        System.out.println("Если нет требований к критерию, ставьте \'-\' ");
        Scanner in = new Scanner(System.in);
        System.out.print(" ИФО: ");
        human.setmName(in.nextLine());
        System.out.print(" День рождния: ");
        human.setmDateOfBirth(in.next());

        System.out.print(" Рост: ");
        String str = in.next();
        if (str.equals("-"))
            human.setmGrowth(0.0f);
        else
            human.setmGrowth(Float.parseFloat(str));
        System.out.print(" Цвет глаз: ");
        human.setmColorEyes(in.next());
        System.out.print(" Пол: ");
        human.setmSex(in.next());
        System.out.println(" Хобби: (Введите -stop для прекращения записи)");
        human.setHobbi(getHobbiFromConsole());
        return human;
    }

    /**
     * Method that call printClient() method for printing objects
     * and check that this array are empty or not,
     * and check array on empty
     *
     * @param cardBoard array of objects
     * */
    public void printArray(Bureau cardBoard) {
        if (cardBoard == null || cardBoard.getClients() == null){
            System.out.println("Array empty");
            return;
        }
        ClientInfo[] prisoner = cardBoard.getClients();

        if (prisoner.length != 0) {
            for (int i = 0; i < prisoner.length; i++) {
                System.out.printf("%30s\n","Клиент №"+1);
                printClient(prisoner[i]);
            }
        }else {
            System.out.println("Nothing to show");
        }
    }

    /**
     * Method that print to console information about client
     * has used special function that print correctly data on console
     * by counting space and lengh of previous text
     *
     * @param client object that will be printed
     * */
    public void printClient(ClientInfo client){
        CorrectPrint print = new CorrectPrint(new int[]{30,22,11,19});
        System.out.printf("%s%40s%13s%16s%13s\n","Имя","День рождения","Рост","Цвет глаз","Пол");
        print.printLine(client.getmName());
        print.printLine(client.getmDateOfBirth());
        print.printLine(client.getmGrowth());
        print.printLine(client.getmColorEyes());
        print.printLine(client.getmSex());
        System.out.println();

        print = new CorrectPrint(new int[]{30});
        System.out.printf("%s%27s\n", "Регистрационный код", "Дата регистрации");
        print.printLine(client.getmRegNumber());
        print.printLine(client.getmDateOfRegistr());
        printHobbies(client.getHobbi());

        print = new CorrectPrint(new int[]{30,22,11,19});
        System.out.printf("%30s\n","Требования к партенёру");
        System.out.printf("%s%40s%13s%16s%13s\n","Имя","День рождения","Рост","Цвет глаз","Пол");
        print.printLine(client.getmDemandsHuman().getmName());
        print.printLine(client.getmDemandsHuman().getmDateOfBirth());
        float f = client.getmDemandsHuman().getmGrowth();
        print.printLine(f == 0.0 ? "-" : f);
        print.printLine(client.getmDemandsHuman().getmColorEyes());
        print.printLine(client.getmDemandsHuman().getmSex());
        printHobbies(client.getmDemandsHuman().getHobbi());
    }
}
