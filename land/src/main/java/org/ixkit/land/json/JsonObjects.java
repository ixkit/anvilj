package org.ixkit.land.json;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.ixkit.land.lang.X;


import org.ixkit.land.utils.StringUtil;
import org.json.JSONObject;

/**
 * @class:JSONObjects
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 01/03/2022
 * @version:0.1.0
 * @purpose:
 */

public class JsonObjects {

    public static <T> T fetch(JSONObject source, String path,   Class<T> t ){
        if (null == source || StringUtil.isEmpty(path)) return null;
        String buf = source.toString();
        DocumentContext jsonContext = JsonPath.parse(buf);
        T result = jsonContext.read(path);
        return result;
    }

    private static <T> T _safeGet(JSONObject value, String path, String key,  Class<T> t  ){
        if (null == value)return null;
        try {
            JSONObject root = value.getJSONObject(path);
            return (T) root.get(key);
        }catch (Exception ex){
            // ex.printStackTrace();
          //  log.error("Failed safeGet:{},ex:{}", key, ex.getMessage());
        }
        return null;
    }
    public static <T> T safeGet(JSONObject value,  String key,  Class<T> t  ){
        if (null == value)return null;
        try {
            //  return (T)  value.get(key);
            if (null == value.opt(key)){
                return null;
            }
            Object v = value.get(key);
            return (T) X.toValue(v,t);
        }catch (Exception ex){
              ex.printStackTrace();
        //    log.error("Failed safeGet:{},ex:{}", key, ex.getMessage());
        }
        return null;
    }

}
