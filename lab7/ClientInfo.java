package com.ForWork.lab7;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

import java.io.Serializable;

/**
 * JavaBean class for work with xml serialization
 *
 * @author Milena Davydova
 * Data 11.11.2017
 * */
public class ClientInfo extends Human implements Serializable {
    private String mRegNumber;// registration number
    private String mDateOfRegistr;// day of registration
    private Human mDemandsHuman;// demand human

    /**
     * Simple constructor
     * initialize all data to null/0
     * */
    public ClientInfo(){
        super();
        mRegNumber = null;
        mDateOfRegistr = null;
        mDemandsHuman = null;
    }

    /**
     * get method that return mRegNumber of client
     *
     * @return String - mRegNumber of client
     * */
    synchronized public String getMRegNumber() {
        return mRegNumber;
    }

    /**
     * set-method that set reg number of client
     *
     * @param mRegNumber - reg number of client
     * */
    synchronized public void setMRegNumber(String mRegNumber) {
        this.mRegNumber = mRegNumber;
    }

    /**
     * get method that return mDateOfRegistr of client
     *
     * @return String - mDateOfRegistr of client
     * */
    synchronized public String getMDateOfRegistr() {
        return mDateOfRegistr;
    }

    /**
     * set-method that set date of register of client
     *
     * @param mDateOfRegistr - reg number of client
     * */
    synchronized public void setMDateOfRegistr(String mDateOfRegistr) {
        this.mDateOfRegistr = mDateOfRegistr;
    }

    /**
     * get method that return mDemandsHuman of client
     *
     * @return String - mDemandsHuman of client
     * */
    synchronized public Human getMDemandsHuman() {
        return mDemandsHuman;
    }

    /**
     * set-method that set demands of client
     *
     * @param mDemands - demands of client
     * */
    synchronized public void setMDemandsHuman(Human mDemands) {
        this.mDemandsHuman = mDemands;
    }
}
