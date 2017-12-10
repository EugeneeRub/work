package com.ForWork.lab10;

import com.ForWork.lab7.ClientInfo;

public class MethodsSort {

    static boolean isEqualByRegistration(ClientInfo client1, ClientInfo client2, boolean direction){
        String s1 = client1.getMDateOfRegistr();
        String s2 = client2.getMDateOfRegistr();
        for (int i = 0; i < s1.length(); i++) {
            if (direction)
                return s1.charAt(i) < s2.charAt(i);
            else
                return s1.charAt(i) > s2.charAt(i);
        }
        return true;
    }

    static boolean isEqualByCountOFAboutMe(ClientInfo client1, ClientInfo client2, boolean direction){
        int v1 = client1.getHobbi().size();
        int v2 = client2.getHobbi().size();
        if (direction)
            return v1 < v2;
        else
            return v1 > v2;
    }

    static boolean isEqualByCountOFPretendToPartner(ClientInfo client1, ClientInfo client2, boolean direction){
        int v1 = client1.getMDemandsHuman().getHobbi().size();
        int v2 = client2.getMDemandsHuman().getHobbi().size();
        if (direction)
            return v1 < v2;
        else
            return v1 > v2;
    }
}