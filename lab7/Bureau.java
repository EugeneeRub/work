package com.ForWork;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!! change to your package !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

/**
 * Method-manager
 * Work with clients information
 *
 * @author Milena Davydova
 * Data 11.11.2017
 * */
public class Bureau {
    private ClientInfo[] clients;// all clients in our "DataBase"

    /**
     * Simple constructor
     * initialize data to null
     * */
    public Bureau(){
        clients = null;
    }

    /**
     * Constructor with arguments
     * that set ClientInfo array to this.ClientInfo array
     *
     * @param clients array of clients
     * */
    public Bureau(ClientInfo[] clients){
        this.clients = clients;
    }

    /**
     * get-method that return array of clients
     *
     * @return ClientInfo - array of clients
     * */
    synchronized public ClientInfo[] getClients() {
        return clients;
    }

    /**
     * set-method that set array of clients
     *
     * @param clients - array of clients
     * */
    synchronized public void setClients(ClientInfo[] clients) {
        this.clients = clients;
    }

    /**
     * method that add object in array
     *
     * @param client object that will be added
     * */
    synchronized public void addClient(ClientInfo client){
        if (clients == null){
            clients = new ClientInfo[0];
        }
        int c = clients.length;
        ClientInfo[] copyArrayOfClients = new ClientInfo[clients.length + 1];
        for (int i = 0; i < c; i++) {
            copyArrayOfClients[i] = clients[i];
        }
        copyArrayOfClients[c] = client;
        clients = null;
        clients = copyArrayOfClients;
    }

    /**
     * method that remove object from arrayby index
     *
     * @param index index of object in array
     * @return boolean - result of work
     * */
    synchronized public boolean removeClient(int index){
        if (clients == null || clients.length == 0 || (index < 0 || index >= clients.length)){
            System.out.println("Can not to delete");
            return false;
        }
        int c = clients.length;
        ClientInfo[] copyArrayOfClients = new ClientInfo[clients.length - 1];
        for (int i = 0, j = 0; i < c; i++) {
            if (i != index){
                copyArrayOfClients[j] = clients[i];
                j++;
            }
        }
        clients = null;
        clients = copyArrayOfClients;
        return true;
    }

    /**
     * get-method that return object by index
     *
     * @param index index of object in array
     * @return ClientInfo - object with information
     * */
    synchronized public ClientInfo get(int index){
        if (index < clients.length
                && index >= 0
                && clients != null){
            return clients[index];
        }
        return null;
    }
}
