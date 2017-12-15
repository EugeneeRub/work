package com.ForWork.lab13_14;

/**
 * Class-Thread creating for call methods from another class
 *
 * @author Milena Davydova
 * Data 15.12.2017
 */
public class myThread extends Thread {
    private int command;// special command that will be started in new thread
    private int timemls;// time for sleeping
    private SpecialMethods spec;// object that has methods for work
    private Thread curThread;// thread that was created from main thread

    /**
     * Constructor that save command
     *
     * @param command command for current thread
     * @param specialMethods object with methods
     * @param timeSleepForNThread time sleep for method
     * */
    public myThread(int command, int timeSleepForNThread, SpecialMethods specialMethods) {
        this.command = command;
        this.timemls = timeSleepForNThread;
        this.spec = specialMethods;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Работа потока " + getName());
        long timeCurentThread = System.nanoTime();
        switch (command) {
            case 0:
                if (!Thread.interrupted())
                    spec.findMinElement(curThread, timemls);
                break;
            case 1:
                if (!Thread.interrupted())
                    spec.findMaxElement(curThread, timemls);
                break;
            case 2:
                if (!Thread.interrupted())
                    spec.printGoodElements(curThread, timemls);
                break;
        }
        double time = (System.nanoTime() - timeCurentThread) / 1000000000d;
        MainWork.map.put(curThread.getName(), time);
    }

    /**
     * Setter for thread
     * @param thread thread that will be used
     * */
    public void setT(myThread thread) {
        this.curThread = thread;
    }
}
