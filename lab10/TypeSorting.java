package com.ForWork.lab10;

import com.ForWork.lab7.ClientInfo;

/**
 * Interface, that program use for sort object in container
 *
 * @author Rubezhin Evgeniy
 * Data 09.11.2017
 * */
public interface TypeSorting {

    /**
     * isEqual method.
     * Method return the way from console or from static key
     *
     * @param v1 object that will be compare
     * @param v2 second object that will be compare
     * @param direction direction of sorting
     * @return boolean - return compare of objects
     * */
    boolean isEqual(ClientInfo v1, ClientInfo v2, boolean direction);

}
