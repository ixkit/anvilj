package org.ixkit.land.utils;

import com.google.gson.*;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @class:JsonUtil
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 19/11/2021
 * @version:0.1.0
 * @purpose:
 */
public class JsonUtil {

    public static String  serialize(Object  obj ){
        Gson gson =   getGson();//new GsonBuilder().create();
        return gson.toJson(obj);
    }
    public static<T> T deserialize(String jsonString, Class<T>	 target  ){
        Gson gson =   getGson();//new GsonBuilder().create();
        return gson.fromJson(jsonString,  target);
    }

    //json map 浮点问
    public static Gson getGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new JsonDeserializer<HashMap>() {
            @Override
            public HashMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                HashMap<String, Object> resultMap = new HashMap<>();
                JsonObject jsonObject = json.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
                return resultMap;
            }

        }).create();
        return gson;
    }
    public static<T> void com(Class<T>	t){//泛型方法
        String string = new String();
        if(string.getClass().equals(t)){
            //if(string.getClass().toString().equals(t.toString())){ //或者 toString()

            System.out.println(true);
        }else System.out.println(false);

        //System.out.println((new String()) instanceof String);
    }

    public static String map2JsonStr(Map map){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

    public static JSONObject map2JsonObject(Map map){
        String buf = map2JsonStr(map);
//        JsonObject convertedObject = new Gson().fromJson(buf, JsonObject.class);
//        return convertedObject.
        // return deserialize(buf,JSONObject.class);

        return new JSONObject(buf);
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String flink = "{ fa: 1024, fb: 1024.0 }";
        HashMap<String, Object> map = getGson().fromJson(flink, HashMap.class);
        System.out.println(map);
    }
}
