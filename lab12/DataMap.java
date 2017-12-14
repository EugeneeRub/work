package com.ForWork.lab12;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab9.LinkedConteiner;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that parse data on pair-lists
 * with methods
 *
 * @author Milena Davydova
 * Data 14.12.2017
 * */
public class DataMap {
    private LinkedConteiner<ClientInfo> conteiner;// conteiner with clients
    private Map<Integer,LinkedConteiner<ClientInfo>> mapOfData;// map of pair-clients
    private int counter = 0;// counter for mapOfData

    /**
     * Constructor with arguments
     * @param conteiner conteiner of elements
     * */
    public DataMap(LinkedConteiner<ClientInfo> conteiner) {
        this.conteiner = conteiner;
    }

    /**
     * Method that call parsers methods for load data to mapOfData
     * @return Map - map with data
     * */
    public Map<Integer,LinkedConteiner<ClientInfo>> createDataMap(){
        mapOfData = new HashMap<>();
        for (int i = 0; i < conteiner.size(); i++) {
            for (int j = 0; j < conteiner.size(); j++) {
                if (i == j)
                    continue;
                if (equalsObjs(conteiner.get(i),conteiner.get(j))){
                    if (mapOfData.size() == 0) {
                        addToMaps(i, j);
                    } else {
                        if (!checkTheObjectsInMap(conteiner.get(i),conteiner.get(j)))
                            addToMaps(i, j);
                    }
                }
            }
        }
        return mapOfData;
    }

    /**
     * Method compare objects with pair-clients that already exists
     *
     * @param cl1 first client
     * @param cl2 second client
     * @return boolean - valid value
     */
    private boolean checkTheObjectsInMap(ClientInfo cl1, ClientInfo cl2){
        for (Map.Entry<Integer,LinkedConteiner<ClientInfo>> entry : mapOfData.entrySet()){
            LinkedConteiner<ClientInfo> list = entry.getValue();
            if (list.contains(cl1) && list.contains(cl2))
                return true;
        }
        return false;
    }

    /**
     * Method add data to map and increment counter
     *
     * @param i first index for element
     * @param j second index for element
     * @return boolean - valid value
     */
    private void addToMaps(int i,int j){
        LinkedConteiner<ClientInfo> listClients = new LinkedConteiner<>();
        listClients.add(conteiner.get(i));
        listClients.add(conteiner.get(j));
        mapOfData.put(counter++,listClients);
    }

    /**
     * Method compare obejects with regEx
     * and then check that this two objects are good for next work
     *
     * @param cl1 first client
     * @param cl2 second client
     * @return boolean - valid value
     */
    private boolean equalsObjs(ClientInfo cl1, ClientInfo cl2) {
        final String MANPATTERN = "Мужской|мужской|Мужчина|мужчина|М|м";
        final String WOMANPATTERN = "Женский|женский|Женщина|женщина|Ж|ж";
        if ((cl1.getMSex().matches(MANPATTERN) && cl2.getMSex().matches(WOMANPATTERN)) ||
                (cl1.getMSex().matches(WOMANPATTERN) && cl2.getMSex().matches(MANPATTERN))) {
            String[] arr1 = cl1.getMDateOfBirth().split("[\\.,]");
            String[] arr2 = cl2.getMDateOfBirth().split("[\\.,]");
            int n1 = Integer.parseInt(arr1[arr1.length - 1]);
            int n2 = Integer.parseInt(arr2[arr2.length - 1]);
            if ((-5 <= n1 - n2) && (n1-n2 < 0) || (n1 - n2 <= 5) && (n1-n2 > 0))
                return true;
        }
        return false;
    }
}
