package com.ForWork.lab11;

import com.ForWork.lab7.ClientInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * Class that used special for load data to .json file
 *
 * @author Milena Davydova
 * Data 14.12.2017
 * */
public class WriteInFile {

    /**
     * Method load data, with JSONObjects and JSONArray
     *
     * @param fileWay way to file
     * @param list list of clients
     * */
    public static void writeToFile(Collection<ClientInfo> list, String fileWay) {
        JSONObject object1 = new JSONObject();
        JSONArray array1 = new JSONArray();
        for (ClientInfo info : list) {
            JSONObject obj = new JSONObject();
            {
                obj.put("name", info.getMName());
                obj.put("birthday", info.getMDateOfBirth());
                obj.put("growth", info.getMGrowth());
                obj.put("colorEyes", info.getMColorEyes());
                obj.put("sex", info.getMSex());
                JSONArray array2 = new JSONArray();
                for (String str : info.getHobbi())
                    array2.add(str);
                obj.put("signs", array2);
                obj.put("registrationNumber", info.getMRegNumber());
                obj.put("registrationDate", info.getMDateOfRegistr());
            }
            JSONObject object2 = new JSONObject();
            {
                object2.put("name", info.getMDemandsHuman().getMName());
                object2.put("birthday", info.getMDemandsHuman().getMDateOfBirth());
                object2.put("growth", info.getMDemandsHuman().getMGrowth());
                object2.put("colorEyes", info.getMDemandsHuman().getMColorEyes());
                object2.put("sex", info.getMDemandsHuman().getMSex());
                JSONArray array2 = new JSONArray();
                for (String str : info.getMDemandsHuman().getHobbi())
                    array2.add(str);
                object2.put("signs",array2);
            }
            obj.put("pretendHuman", object2);
            array1.add(obj);
        }
        object1.put("clients", array1);

        try (FileWriter writer = new FileWriter(fileWay)) {
            writer.write(object1.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
