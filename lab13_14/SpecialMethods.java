package com.ForWork.lab13_14;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;

import java.util.ArrayList;

/**
 * Class with methods for work with list
 *
 * @author Milena Davydova
 * Data 15.12.2017
 * */
public class SpecialMethods {
    private ArrayList<ClientInfo> list;// list with prisoners

    public SpecialMethods(ArrayList<ClientInfo> list){
        this.list = list;
    }

    /**
     * Method that search minimal element in list
     * and then print it
     * */
    public synchronized void findMinElement(Thread t, int timemls){
        ClientInfo client = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {

                try {
                    Thread.sleep(timemls);
                } catch (InterruptedException e) {
                    t.interrupt();
                }

                if (!Thread.interrupted()) {
                    ClientInfo client2 = list.get(j);
                    if (client.getMGrowth() > client2.getMGrowth()) {
                        client = client2;
                    }
                } else {
                    System.out.println("Сработала экстренная остановка!!!");
                    System.out.println("Вывод текущего результата после остановки работы");
                    ConsoleWork.printClient(client);
                    return;
                }
            }
        }
        System.out.println("Найден минимальный элемент");
        ConsoleWork.printClient(client);
    }

    /**
     * Method that search maximal element in list
     * and then print it
     * */
    public synchronized void findMaxElement(Thread t, int timemls){
        ClientInfo client = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                try {
                    Thread.sleep(timemls);
                } catch (InterruptedException e) {
                    t.interrupt();
                }
                if (!Thread.interrupted()){
                    ClientInfo client2 = list.get(j);
                    if (client.getMGrowth() < client2.getMGrowth()){
                        client = client2;
                    }
                } else {
                    System.out.println("Сработала экстренная остановка!!!");
                    System.out.println("Вывод текущего результата при работе");
                    ConsoleWork.printClient(client);
                    return;
                }
            }
        }
        System.out.println("Найден максимальный элемент");
        ConsoleWork.printClient(client);
    }

    /**
     * Method that count counting elements in list by special category,
     * then print elements from list
     * */
    public synchronized void printGoodElements(Thread t, int timemls){
        System.out.println("Результат выполнения ф-ции и вывод данных");
        for (ClientInfo client : list) {
            try {
                Thread.sleep(timemls);
            } catch (InterruptedException e) {
                t.interrupt();
            }
            if (!Thread.interrupted()){
                String colorOfEyes = client.getMColorEyes();
                if (colorOfEyes.matches("[Гг]олубые|[Гг]олубой|[Кк]арие|[Кк]арий"))
                    ConsoleWork.printClient(client);
            }else {
                System.out.println("Сработала экстренная остановка!!!");
                return;
            }
        }
    }
}
