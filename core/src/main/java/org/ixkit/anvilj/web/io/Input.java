package org.ixkit.anvilj.web.io;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.ixkit.anvilj.core.XObject;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class Input {

    public static Map<String, Object> asParameters(HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String[]> map=request.getParameterMap();
        for(String key :map.keySet()) {
            result.put(key, map.get(key)[0]);
        }
      return result;
    }

    public static Argument json2Map(String jsonStr){
        Map<String, Object> paramMap = JsonUtil.deserialize(jsonStr,Map.class);
        Argument result = Argument.of();
        result.putAll(paramMap);
        return  result;
    }
    public static  <T> T  deserializeXObject(String jsonStr,Class<T> tClass){
        Argument argument = json2Map(jsonStr);
        T xObject = JsonUtil.deserialize(jsonStr,tClass);
        //@step
        Object bucket =  argument.get("bucket");
       while (null!= bucket ){
            if (bucket instanceof JsonObject){
                Map<String, Object> map = JsonUtil.fromJsonObjectToPureMap((JsonObject) bucket);
                ((XObject)xObject).putAll(map);
                break;
            }
            if (bucket instanceof  Map){
                ((XObject)xObject).putAll((Map)bucket);
            }
            //@@todo
            log.warn("Error parse Input value:{}",tClass);
        }

        return xObject;
    }
}


