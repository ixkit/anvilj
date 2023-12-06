package org.ixkit.land.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONs {
    public static String toPrettyString(Object target){
        if(null == target){
            return "null";
        }
        String result = "";
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            result = gson.toJson(target);
        }catch (Exception e){
            //@ System.err.println("error toPrettyString:" +e.getMessage());
            result = target.toString();
        }
        return result;

    }
}
