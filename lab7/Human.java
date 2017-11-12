package com.ForWork;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.util.LinkedList;

/**
 * JavaBean class for work with xml serialization
 *
 * @author Milena Davydova
 * Data 11.11.2017
 * */
public class Human {
    private String mName;// name of client
    private float mGrowth;// growth of client
    private String mColorEyes;// color eyes
    private String mSex;// sex of client
    private String mDateOfBirth;// birthday
    private LinkedList<String> hobbi;// list with hobbies

    /**
     * Simple constructor
     * initialize all data to null/0
     * */
    public Human() {
        this.mName = null;
        this.mGrowth = 0;
        this.mColorEyes = null;
        this.hobbi = null;
        this.mDateOfBirth = null;
        this.mSex = null;
    }

    /**
     * get method that return name of client
     *
     * @return String - name of client
     * */
    synchronized public String getmName() {
        return mName;
    }

    /**
     * set-method that set name of client
     *
     * @param mName - name of client
     * */
    synchronized public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * get method that return growth of client
     *
     * @return Float - growth of client
     * */
    synchronized public float getmGrowth() {
        return mGrowth;
    }

    /**
     * set-method that set growth of client
     *
     * @param mGrowth - growth of client
     * */
    synchronized public void setmGrowth(float mGrowth) {
        this.mGrowth = mGrowth;
    }

    /**
     * get-method that return color eyes of client
     *
     * @return String - color eyes of client
     * */
    synchronized public String getmColorEyes() {
        return mColorEyes;
    }

    /**
     * set-method that set color eyes of client
     *
     * @param mColorEyes - color eyes of client
     * */
    synchronized public void setmColorEyes(String mColorEyes) {
        this.mColorEyes = mColorEyes;
    }

    /**
     * get-method that return sex of client
     *
     * @return String - sex of client
     * */
    synchronized public String getmSex() {
        return mSex;
    }

    /**
     * set-method that set sex of client
     *
     * @param mSex - sex of client
     * */
    synchronized public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    /**
     * get-method that return birthday of client
     *
     * @return String - birthday of client
     * */
    synchronized public String getmDateOfBirth() {
        return mDateOfBirth;
    }

    /**
     * set-method that set birthday of client
     *
     * @param dateOfBirth - birthday of client
     * */
    synchronized public void setmDateOfBirth(String dateOfBirth) {
        this.mDateOfBirth = dateOfBirth;
    }

    /**
     * get-method that return hobbies of client
     *
     * @return LinkedList - hobbies of client
     * */
    synchronized public LinkedList<String> getHobbi() {
        return hobbi;
    }

    /**
     * set-method that set hobbies of client
     *
     * @param hobbi - hobbies of client
     * */
    synchronized public void setHobbi(LinkedList<String> hobbi) {
        this.hobbi = hobbi;
    }

}
