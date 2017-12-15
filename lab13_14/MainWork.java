package com.ForWork.lab13_14;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;
import com.ForWork.lab7.CorrectPrint;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that has work with new threads
 *
 * @author Milena Davydova
 * Data 15.12.2017
 * */
public class MainWork {
    private ArrayList<ClientInfo> list;// list of prisoners
    public static ConcurrentHashMap<String,Double> map = new ConcurrentHashMap<>();
    private double resultTime;// result of time work in current thread
    private myThread threads[] = new myThread[3];// all threads that will be created

    /**
     * Constructor
     * @param list of clients
     * */
    public MainWork(ArrayList<ClientInfo> list) {
        this.list = list;
    }

    /**
     * Method that start work with threads
     * and control their life or stop program
     * */
    public void work() {
        int commands[] = new int[3];// commands for threads
        boolean flag = true;// flag for stop the program
        boolean threadFlag = false;
        int timeSleep = 0;// time sleeping for this thread
        int timeSleepForNThread = 0;// time sleep for other threads
        Scanner in = new Scanner(System.in);// reader from console

        do {
            System.out.println("Input the command(-help for help)");
            threadFlag = false;
            switch (in.next()) {
                case "-work":
                    commands = new int[3];
                    System.out.println("Введите время сна главного потока в млс");
                    timeSleep = in.nextInt();
                    System.out.println("Введите время сна побочных потоков в млс"+
                            "(Будет домножено в зависимости от кол-ва объектов)");
                    timeSleepForNThread = in.nextInt();
                    for (int i = 0; i < 3; i++) {
                        commands[i] = i;
                    }
                    threadFlag = true;
                    break;
                case "-print":
                    for (ClientInfo client : list){
                        ConsoleWork.printClient(client);
                    }
                    break;
                case "-stop":
                    flag = false;
                case "-help":
                    help();
                default:
                    System.out.println("Не введена нормально комманда");
            }
            if (threadFlag){
                for (int i = 0; i < 3; i++) {
                    threads[i] = new myThread(commands[i],timeSleepForNThread,new SpecialMethods(list));
                }
                for (int i = 0; i < 3; i++) {
                    threads[i].setName("Thread #" + (i+1));
                    threads[i].setT(threads[i]);
                }
                for (int i = 0; i < 3; i++) {
                    threads[i].start();
                }

                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 3; i++) {
                    if(threads[i].isAlive()){
                        threads[i].interrupt();
                    }
                }

                System.out.println("Запуск последовательной обработки");
                startWorkInCurrThread(timeSleepForNThread);
                System.out.printf("\n%s%22s%28s\n","Поток","Время сна(mls)","Время выполнения(sec)");

                CorrectPrint print = new CorrectPrint(new int[]{18,21});
                Class classs = Main.class;
                print.printLine(classs.getSimpleName());
                print.printLine(timeSleepForNThread);
                print.printLine(resultTime);
                System.out.println();

                double sum = 0;
                for (Map.Entry<String,Double> entry : map.entrySet()) {
                    print = new CorrectPrint(new int[]{18,21});
                    print.printLine(entry.getKey());
                    print.printLine(timeSleepForNThread);
                    print.printLine(entry.getValue());
                    sum += entry.getValue();
                    System.out.println();
                }
                ArrayList<Double> list = new ArrayList<>();
                for (Map.Entry<String,Double> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }

                System.out.println("Скорость паралельной работы больше последовательной в " +
                        resultTime/getMaxFromList(list) + " раза");
            }
        } while (flag);
    }

    /**
     * Mehtod for consistently work for compare the threads
     *
     * @param timeSleepForNThread time for sleep
     * */
    private void startWorkInCurrThread(int timeSleepForNThread) {
        long timeCurentThread = System.nanoTime();
        SpecialMethods spec = new SpecialMethods(list);
        System.out.println("Работа последовательной обработки");
        for (int command = 0; command < 3; command++) {
            switch (command) {
                case 0:
                    spec.findMinElement(new Thread(), timeSleepForNThread);
                    break;
                case 1:
                    spec.findMaxElement(new Thread(), timeSleepForNThread);
                    break;
                case 2:
                    spec.printGoodElements(new Thread(),timeSleepForNThread);
                    break;
            }
        }
        resultTime = (System.nanoTime() - timeCurentThread) / 1000000000d;
    }

    /**
     * Method that print help information about command that
     * contain in this programme
     * */
    private void help() {
        System.out.println("Команда -print - печатает все элементы списка");
        System.out.println("Команда -work - запускает отдельные потоки");
        System.out.println("Команда -stop - прекращение работы");
    }

    /**
     * Method that find max element in list
     * @param list list of elements
     *             @return result - when will be found max element
     * */
    public Double getMaxFromList(ArrayList<Double> list) {
        Double result = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Double next = list.get(j);
                if (result < next) {
                    result = next;
                }
            }
        }
        return result;
    }
}
