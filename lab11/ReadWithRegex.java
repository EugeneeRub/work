package com.ForWork.lab11;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.Human;
import com.ForWork.lab9.LinkedConteiner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ForWork.lab7.ConsoleWork.getHobbiFromConsole;

/**
 * Class reader for prisoners data
 *
 * This class can load data from console or from .json file and check by regEx
 *
 * @author Milena Davydova
 * Data 11.12.2017
 * */
public class ReadWithRegex {
    private static final String DATEPATTERN = "((0[1-9]|[1-2][0-9]|3[0-1])(\\.|\\,)(0[1-9]|1[0-2])(\\.|\\,)" + "([0-9]{2,4}))|(-)";
    private static final String COLOREYESPATTERN = "([a-zA-Zа-яА-я]+)|(-)";
    private static final String GROWTHPATTERN = "(\\d+.\\d+)|(-)";
    private static final String NAMEPATTERN = "(\\D+)|(-)";
    private static final String REGNUMPATTERN = "([a-zA-Zа-яА-я]){2}\\s(\\d){5,20}";
    private static final String SEXPATTERN = "([a-zA-Zа-яА-я]+)|(-)";

    /**
     * Method load data from console and check all value by regEx
     *
     * @return ClientInfo - return clients that was created
     * */
    public static ClientInfo readFromConsole() throws NotMatchData {
        Scanner in = new Scanner(System.in);
        ClientInfo client = new ClientInfo();

        System.out.println("Input the information about client");
        //get info from console about person
        {
            System.out.print(" ИФО: ");
            client.setMName(getDataFromConsole(in,NAMEPATTERN));

            System.out.print(" День рождeния: ");
            client.setMDateOfBirth(getDataFromConsole(in,DATEPATTERN));

            System.out.print(" Рост: ");
            client.setMGrowth((float) Double.parseDouble(getDataFromConsole(in,GROWTHPATTERN)));

            System.out.print(" Цвет глаз: ");
            in = new Scanner(System.in);
            client.setMColorEyes(getDataFromConsole(in,COLOREYESPATTERN));

            System.out.print(" Пол: ");
            client.setMSex(getDataFromConsole(in,SEXPATTERN));
        }

        System.out.print(" Регистрационный номер: ");
        client.setMRegNumber(getDataFromConsole(in,REGNUMPATTERN));

        System.out.print(" Дата регистрации: ");
        client.setMDateOfRegistr(getDataFromConsole(in,DATEPATTERN));

        System.out.println(" Хобби: (Введите -stop для прекращения записи)");
        Scanner in2 = new Scanner(System.in);
        boolean flag = true;
        LinkedList<String> list = new LinkedList<>();
        do {
            String text = in.nextLine();
            if (text.equals("-stop")) {
                flag = false;
            } else
                list.add(text);
        } while (flag);
        client.setHobbi(list);

        System.out.println(" Требования к кандидату: ");
        client.setMDemandsHuman(pretendToCandidat());

        return client;
    }

    /**
     * Method get data from file and check it
     * if data are not valid, stop the process
     *
     * @param fileWay way to file
     * @return MyArrayList - return list of prisoners
     * */
    public static LinkedConteiner<ClientInfo> readFromFile(File fileWay) throws NotMatchData{
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(fileWay));

            LinkedConteiner<ClientInfo> listOfClients = new LinkedConteiner<>();
            JSONArray array = (JSONArray) object.get("clients");
            ClientInfo client;
            Iterator<JSONObject> it = array.iterator();
            String data;
            while (it.hasNext()){
                client = new ClientInfo();
                JSONObject obj = it.next();
                {
                    data = (String) obj.get("name");
                    checkExpression(data,NAMEPATTERN);
                    client.setMName(data);

                    data = (String) obj.get("birthday");
                    checkExpression(data,DATEPATTERN);
                    client.setMDateOfBirth(data);

                    client.setMGrowth((float)(double)obj.get("growth"));

                    data = (String) obj.get("colorEyes");
                    checkExpression(data,COLOREYESPATTERN);
                    client.setMColorEyes(data);

                    data = (String) obj.get("sex");
                    checkExpression(data,SEXPATTERN);
                    client.setMSex(data);

                    JSONArray arr1 = (JSONArray) obj.get("signs");
                    LinkedList<String> listOfDecarations = new LinkedList<>();
                    Iterator it2 = arr1.iterator();
                    while (it2.hasNext()){
                        data = (String) it2.next();
                        listOfDecarations.add(data);
                    }
                    client.setHobbi(listOfDecarations);

                    data = (String) obj.get("registrationNumber");
                    checkExpression(data,REGNUMPATTERN);
                    client.setMRegNumber(data);

                    data = (String) obj.get("registrationDate");
                    checkExpression(data,DATEPATTERN);
                    client.setMDateOfRegistr(data);

                }
                JSONObject object2 = (JSONObject) obj.get("pretendHuman");
                {
                    Human human = new Human();
                    data = (String) object2.get("name");
                    checkExpression(data,NAMEPATTERN);
                    human.setMName(data);

                    data = (String) object2.get("birthday");
                    checkExpression(data,DATEPATTERN);
                    human.setMDateOfBirth(data);

                    human.setMGrowth((float) (double)object2.get("growth"));

                    data = (String) object2.get("colorEyes");
                    checkExpression(data,COLOREYESPATTERN);
                    human.setMColorEyes(data);

                    data = (String) object2.get("sex");
                    checkExpression(data,SEXPATTERN);
                    human.setMSex(data);

                    JSONArray arr2 = (JSONArray) object2.get("signs");
                    LinkedList<String> listOfDecarations = new LinkedList<>();
                    Iterator it2 = arr2.iterator();
                    while (it2.hasNext()){
                        data = (String) it2.next();
                        listOfDecarations.add(data);
                    }
                    human.setHobbi(listOfDecarations);
                    client.setMDemandsHuman(human);
                }
                listOfClients.add(client);
            }
            return listOfClients;
        }catch (IOException e) {
            System.out.println("Sorry but file not found by this way " + fileWay.getPath());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Human pretendToCandidat(){
        Human human = new Human();
        System.out.println("Если нет требований к критерию, ставьте \'-\' ");
        Scanner in = new Scanner(System.in);

        System.out.print(" ИФО: ");
        human.setMName(getDataFromConsole(in,NAMEPATTERN));

        System.out.print(" День рождeния: ");
        human.setMDateOfBirth(getDataFromConsole(in,DATEPATTERN));

        System.out.print(" Рост: ");
        String str = getDataFromConsole(in,GROWTHPATTERN);
        if (str.equals("-"))
            human.setMGrowth(0.0f);
        else
            human.setMGrowth((float) Double.parseDouble(str));

        System.out.print(" Цвет глаз: ");
        in = new Scanner(System.in);
        human.setMColorEyes(getDataFromConsole(in,COLOREYESPATTERN));

        System.out.print(" Пол: ");
        human.setMSex(getDataFromConsole(in,SEXPATTERN));

        System.out.println(" Хобби: (Введите -stop для прекращения записи)");
        human.setHobbi(getHobbiFromConsole());
        return human;
    }

    /**
     * Method get data from console and check it
     * if data are not valid, start repeating the process
     *
     * @param PATTERN - regEx
     * @param in scanner for getting data
     * @return String - return value from console
     * */
    private static String getDataFromConsole(Scanner in,String PATTERN){
        boolean flag;
        String data;
        do {
            data = in.nextLine();
            try{
                checkExpression(data,PATTERN);
                flag = false;
            }catch (NotMatchData ex){
                ex.printError();
                flag = true;
                System.out.println("Repeat input the data");
            }
        }while (flag);
        return data;
    }

    /**
     * Method check expression and if we have error throw exception
     *
     * @param data some text
     * @param regex regEx
     * */
    private static void checkExpression(String data,String regex) throws NotMatchData {
        if (!checkByRegex(data, regex))
            throw new NotMatchData(data);
    }

    /**
     * Method get data from file and check it
     * if data are not valid, stop the process
     *
     * @param data some text
     * @param regEx regEx
     * @return boolean - return valid value of checking regEx
     * */
    private static boolean checkByRegex(String data,String regEx){
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
