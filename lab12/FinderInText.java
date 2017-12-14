package com.ForWork.lab12;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.ConsoleWork;
import com.ForWork.lab9.LinkedConteiner;

import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that used to find data by regEx
 * call DataMap.createDataMap() for parse to pair-clients with condition by 5 years old
 *
 * @author Milena Davydova
 * Data 14.12.2017
 * */
public class FinderInText {
    private LinkedConteiner<ClientInfo> conteiner;// clients
    private Map<Integer,LinkedConteiner<ClientInfo>> mapOfData;//map of pair-clients

    /**
     * Constructor with arguments
     * @param conteiner conteiner of elements
     * */
    public FinderInText(LinkedConteiner<ClientInfo> conteiner) {
        this.conteiner = conteiner;
    }

    /**
     * Method start special method by language
     *
     * @param regex regEx
     * @return boolean - valid value
     */
    public boolean find(String regex) {
        DataMap data = new DataMap(conteiner);
        mapOfData = data.createDataMap();
        for (Map.Entry<Integer, LinkedConteiner<ClientInfo>> entry : mapOfData.entrySet()) {
            LinkedConteiner<ClientInfo> clients = entry.getValue();
            LinkedList<String> list1 = clients.get(0).getMDemandsHuman().getHobbi();
            LinkedList<String> list2 = clients.get(1).getMDemandsHuman().getHobbi();

            if (specialFinder(list1, regex, parseRegex(regex)) && specialFinder(list2, regex, parseRegex(regex)))
                printSpecClient(clients);
        }
        return true;
    }

    /**
     * Method print all about client
     * by using special class that was developed by myself
     *
     * @param clients list of pair-clients
     */
    private void printSpecClient(LinkedConteiner<ClientInfo> clients) {
        System.out.println("--------!! Два клиента которые могут встретиться !!--------");
        ConsoleWork work = new ConsoleWork();
        for (ClientInfo prisoner : clients) {
            work.printClient(prisoner);
        }
        System.out.println();
    }

    /**
     * Method that search data by regEx
     *
     * @param list        list of texts
     * @param PATTERN     regEx for russian text
     * @param specialKeys keys that was parsed in parsing method
     * @return boolean - valid value
     */
    private boolean specialFinder(LinkedList<String> list, String PATTERN, LinkedList<LinkedList<String>> specialKeys) {
        Pattern pattern = Pattern.compile(PATTERN);
        for (String data : list) {
            Matcher matcher = pattern.matcher(data);
            if (matcher.find())
                return true;
        }
        return deepSearching(list, specialKeys);
    }

    /**
     * Method that use special searching to find information that was in regEx
     *
     * @param list        list of texts
     * @param specialKeys keys that was parsed in parsing method
     * @return boolean - valid value
     */
    private boolean deepSearching(LinkedList<String> list, LinkedList<LinkedList<String>> specialKeys) {
        int numberOfWord[] = new int[specialKeys.size()];
        int sum = 0;
        for (String text : list) {
            String arraOfWords[] = text.split(" ");

            for (int n : numberOfWord)
                sum += n;
            if (sum >= specialKeys.size()) {
                for (int n : numberOfWord){
                    if (n == 0)
                        return false;
                }
                return true;
            } else {
                sum = 0;
                numberOfWord = new int[specialKeys.size()];
            }
            for (String str : arraOfWords) {
                StringBuilder word = new StringBuilder(str);
                char ch = word.charAt(word.length() - 1);
                if (ch == '.' || ch == ',' || ch == '!' || ch == '?') {
                    word.delete(word.length() - 1, word.length());
                }
                goThrow:
                for (int i = 0; i < specialKeys.size(); i++) {
                    if (numberOfWord[i] != 0)
                        continue;
                    LinkedList<String> list1 = specialKeys.get(i);
                    if (list1.getLast().equals("?"))
                        numberOfWord[i]++;
                    for (String word2 : list1) {
                        if (word.toString().equals(word2)) {
                            numberOfWord[i]++;
                            break goThrow;
                        }
                    }
                }
            }
        }

        for (int n : numberOfWord)
            sum += n;
        if (sum >= specialKeys.size()) {
            for (int n : numberOfWord){
                if (n == 0)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Method that parse regular expression from text
     *
     * @param regex regEx
     * @return LinkedList - list of keys from regex
     */
    private LinkedList<LinkedList<String>> parseRegex(String regex) {
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        LinkedList<String> listOfString = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < regex.length(); i++) {
            char ch = regex.charAt(i);
            if (ch == '|') {
                listOfString.add(builder.toString());
                builder.delete(0, builder.length());
            } else if (ch == '?') {
                listOfString = list.get(list.size() - 1);
                listOfString.add("?");
                listOfString = new LinkedList<>();
            } else if (ch != '(' && ch != ')' && ch != ' ') {
                builder.append(ch);
            } else {
                if (!builder.toString().isEmpty()) {
                    listOfString.add(builder.toString());
                    list.add(listOfString);
                    listOfString = new LinkedList<>();
                }
                builder.delete(0, builder.length());
            }
        }
        return list;
    }
}
