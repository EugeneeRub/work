package com.ForWork.lab9;

import com.ForWork.lab7.ClientInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * List container
 *
 * @author Milena Davydova
 * Data 24.11.2017
 * */
public class LinkedConteiner<Type extends ClientInfo> implements Serializable,Collection<Type> {
    private int mSize;// size of liked list
    private Node<Type> client;// client with information and next client

    public LinkedConteiner(){
        mSize = 0;
        client = null ;
    }

    public Node<Type> getNode(){
        return client;
    }

    public Node newInstance(){
        return new Node();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<Type> walker = client;
        builder.append("[");
        while (walker != null){
            builder.append((walker.item).getMName()).append(", ");
            builder.append((walker.item).getMDateOfBirth()).append(", ");
            builder.append((walker.item).getMGrowth()).append(", ");
            builder.append((walker.item).getMColorEyes()).append(", ");
            builder.append((walker.item).getMSex()).append(", ");
            builder.append((walker.item).getMRegNumber()).append(", ");
            builder.append((walker.item).getMDateOfRegistr()).append(", ");
            builder.append("\n");
            walker = walker.next;
        }
        builder.delete(builder.length()-3,builder.length());
        builder.append("]");
        return builder.toString();
    }

    public Type get(int index) {
        if (index < 0 || index >= mSize)
            return null;
        Node<Type> walker = client;
        int counter = 0;
        while (counter != index){
            walker = walker.next;
            counter++;
        }
        return walker.item;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return (client == null);
    }

    @Override
    public boolean contains(Object o) {
        if (mSize == 0 || client == null || o == null)
            return false;
        Node walker = client;
        ClientInfo obj = (ClientInfo) o;
        while(walker != null){
            if(walker.equals(obj))
                return true;
            walker = walker.next;
        }
        return false;
    }

    @Override
    public Iterator<Type> iterator() {
        return new myIterable().iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[mSize];
        Node walker = client;
        int mCounter1 = 0;
        while (walker != null){
            array[mCounter1] = walker.item;
            mCounter1++;
            walker = walker.next;
        }
        return array;
    }

    @Override
    public boolean add(Type element)  {
        if (element == null)
            return false;
        if (client == null){
            client = new Node<>();
            client.item = element;
            mSize++;
            return true;
        }
        Node<Type> walker = client;
        while(walker.hasNext()){
            walker = walker.next;
        }
        Node<Type> type = new Node<>();
        type.item = element;

        walker.next = type;

        mSize++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    public boolean remove(int index) {
            if (mSize == 0 || client == null || index < 0 || index > mSize)
                return false;

            Node<Type> walker = client;
            Node<Type> willRemoved;
            int mCounter = 0;
            mSize--;

            if (index == 0){
                willRemoved = walker;
                walker = willRemoved.next;
                return true;
            }

            while (walker != null){
                if (mCounter == index-1){
                    willRemoved = walker.next;
                    willRemoved.item = null;
                    walker.next = willRemoved.next;
                    return true;
                }
                walker = walker.next;
                mCounter++;
            }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (mSize == 0 || client == null || c.size() != mSize || c == null)
            return false;

        Node<Type> walker = client;
        Iterator it = c.iterator();
        while(walker != null && it.hasNext()){
            if(!(walker.item).equals(it.next()))
                return false;
            walker = walker.next;
        }
        return true;
    }

    @Override
    public void clear() {
        Node<Type> walker = client;
        while(walker != null){
            walker.item = null;
            walker = walker.next;
        }
        client = null;
    }

    class myIterable implements Iterable<Type>{

        @Override
        public Iterator<Type> iterator() {
            return new mIterator();
        }

        class mIterator implements Iterator<Type>{
            Node<Type> walker;
            int counter = 0;

            public mIterator(){
                walker = client;
                counter = 0;
            }

            @Override
            public boolean hasNext() {
                if (counter == 0) {
                    if (walker != null)
                        return true;
                    return false;
                }
                if (walker.next != null)
                    return true;
                return false;
            }

            @Override
            public Type next() {
                if (counter == 0) {
                    counter++;
                    return walker.item;
                }
                counter++;
                walker = walker.next;
                return walker.item;
            }
        }
    }

    public class Node<Type extends ClientInfo> implements Serializable {
        Type item;
        Node<Type> next;

        Node() {
            item = null;
            next = null;
        }

        public Type getItem() {
            return item;
        }

        public void setItem(Type item) {
            this.item = item;
        }

        public Node<Type> getNext() {
            return next;
        }

        public void setNext(Node<Type> next) {
            this.next = next;
        }

        public boolean equals(ClientInfo clientInfo) {
            if (clientInfo == null)
                return false;
            else if (!(item.getMName()).equals(clientInfo.getMName()))
                return false;
            else if (!(item.getMDateOfBirth()).equals(clientInfo.getMDateOfBirth()))
                return false;
            else if (!(item.getMColorEyes()).equals(clientInfo.getMColorEyes()))
                return false;
            else if (item.getMGrowth() != item.getMGrowth())
                return false;
            else if (!(item.getMSex()).equals(clientInfo.getMSex()))
                return false;
            else if (!(item.getMRegNumber()).equals(clientInfo.getMRegNumber()))
                return false;
            else if (!(item.getMDateOfRegistr()).equals(clientInfo.getMDateOfRegistr()))
                return false;

            if ((item.getHobbi()).size() != (clientInfo.getHobbi()).size())
                return false;

            for (int i = 0; i < item.getHobbi().size(); i++){
                if(!(item.getHobbi().get(i)).equals(clientInfo.getHobbi().get(i)))
                    return false;
            }

            return true;
        }

        public boolean hasNext(){
            return (next != null);
        }

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /**
     * Don`t need to use
     * */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean addAll(Collection<? extends Type> c) {
        return false;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Don`t need to use
     * */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}
