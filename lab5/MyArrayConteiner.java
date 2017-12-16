package com.ForWork.lab5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class collection created for processing
 *
 * @author Milena Davydova
 * @version 1.0
 */

public class MyArrayConteiner implements Serializable, Collection {
    private Object[] array; // array of elements
    private int mSize; // size of array
    private int cursor; // cursor of element

    /**
     * Constructor
     * initialize all element by null or zero
     */
    public MyArrayConteiner() {
        array = new Object[0];
        mSize = 0;
        cursor = -1;
    }

    /**
     * Method of sorting
     */
    public void sort() {
        if (array == null) return;
        Arrays.sort(array);
    }

    /**
     * Method that translate array to string
     * use StringBuilder for creating string with elements
     *
     * @return builder - ready string with elements
     */
    public String toString() {
        if (array == null) return "";
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < mSize; i++) {
            Object obj = array[i];
            if (i == mSize - 1) {
                builder.append(obj);// last word will be without (, )
            } else {
                builder.append(obj + ", ");
            }
        }
        builder.append("]");// close the string by this symbol
        return builder.toString();
    }

    /**
     * Return the element by index
     *
     * @param index - of element in array
     * @return array[index] -
     */
    public Object get(int index) {
        if (index >= 0 && index < mSize)
            return array[index];
        return null;
    }

    /**
     * return size of array
     *
     * @return mSize - size of array
     */
    @Override
    public int size() {
        return mSize;
    }

    /**
     * Check giving object with objects tha contained in array
     *
     * @param o - object for checking
     * @return true/false - when process are done or not
     */
    @Override
    public boolean contains(Object o) {
        if (array == null) return false;
        for (int i = 0; i < mSize; i++) {
            if (o.equals(array[i])) return true;
        }
        return false;
    }

    /**
     * return an array with element
     *
     * @return array - array of elements
     */
    @Override
    public Object[] toArray() {
        return array;
    }

    /**
     * Add an object in container
     * go throw the cycle for add in time-array
     * then give link on main array
     *
     * @param obj object for adding
     * @return true/false - when process are done or not
     */
    @Override
    public boolean add(Object obj) {
        if (obj == null || array == null) return false;
        Object[] newArrayOfElems = new Object[++mSize];
        for (int i = 0; i < mSize; i++) {
            if (i == mSize - 1)
                newArrayOfElems[i] = obj;
            else
                newArrayOfElems[i] = array[i];
        }
        array = newArrayOfElems;
        return true;
    }

    /**
     * Clean element by object
     * go throw the cycle for checking object that equals with object in array
     * if true, skip record, else not.
     *
     * @param o - array element will be deleted by this object
     * @return true/false - when process are done or not
     */
    @Override
    public boolean remove(Object o) {
        if (o == null || array == null) return false;
        boolean flagForExeption = true;// flag
        Object[] newArrayOfElems = new Object[mSize - 1];
        int j = 0;// counter for second loop
        for (int i = 0; i < mSize; i++) {
            if (o.equals(array[i])) {
                flagForExeption = false;
            } else {
                newArrayOfElems[j++] = array[i];
            }
        }
        if (flagForExeption) return false; // if flagForExeption equals true
        array = newArrayOfElems; // cope in main array
        mSize--;// reduce mSize
        return true;
    }

    /**
     * Clean element by index
     * go throw the cycle for checking index that equals with index of loop
     * then skip record, or not.
     *
     * @param index - array element will be deleted by this index
     * @return true/false - when process are done or not
     */
    public boolean remove(int index) {
        if (index < 0 || index > mSize || array == null) return false;
        boolean flagForExeption = true;
        Object[] newArrayOfElems = new Object[mSize - 1];
        int j = 0;
        for (int i = 0; i < mSize; i++) {
            if (index == i) {
                flagForExeption = false;
            } else {
                newArrayOfElems[j++] = array[i];
            }
        }
        if (flagForExeption) return false;
        array = newArrayOfElems;
        mSize--;
        return true;
    }

    /**
     * Check all elements with received collection in arguments
     *
     * @param c - collection of received
     * @return true/false - when check an array by collection
     */
    @Override
    public boolean containsAll(Collection c) {
        if (array == null || c == null) return false;
        if (mSize != c.size()) return false;
        Iterator<?> it = c.iterator();
        for (int i = 0; i < mSize; i++) {
            if (!(it.next()).equals(array[i])) return false;
        }
        return true;
    }

    /**
     * Like cleaning array, but we just clean the link
     */
    @Override
    public void clear() {
        array = null;
        array = new Object[0];
        mSize = 0;
        cursor = -1;
    }

    @Override
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Method returned a new object of iterator
     *
     * @return iterator - by calling this method
     */
    public Iterator iterator() {
        return new MyIterator();
    }

    /**
     * Class iterator for work with cycle for-each
     */
    public class MyIterator implements Iterator {

        /**
         * Method returned a new object of iterator
         *
         * @return false/true - by depending on, if the cursor equals mSize
         * or if element don`t equals null
         */
        @Override
        public boolean hasNext() {
            int elem = cursor + 1;// element that will be checked
            if (elem == mSize) {
                cursor = -1;
                return false;
            } else
                return (array[elem] != null);
        }

        /**
         * Method returned an object from array if that don`t equals null
         *
         * @return  array[++cursor] - object that transformed to T
         */
        @Override
        public Object next() {
            if (array == null) return null;
            return array[++cursor];// bad way, but can be
        }

        /**
         * Method remove element from array by calling remove(cursor)
         * that take an index for delete object
         */
        @Override
        public void remove() {
            if (array == null || cursor == -1 || cursor > mSize) return;
            try {
                MyArrayConteiner.this.remove(cursor);
                --cursor;
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }

        }
    }


    /**
     * At the bottom unusable code
     * -------------------------------------------------------------------------------------
     * They are stub(ZAGLUSHKI)
     * **/


    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    /**
     * At the bottom unusable code
     * -------------------------------------------------------------------------------------
     * They are stub(ZAGLUSHKI)
     * **/

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    /**
     * ZAGLUSHKA
     */
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

}
