package org.ixkit.land.lang;

import java.util.HashMap;

/**
 * Created by Robin
 */
public class HashMaps {

    public static <T> HashMap<T, T> toMap(T ... arguments ){
        HashMap<T, T> result = new HashMap<T, T>();
        if (null == arguments){
            return result;
        }
        int count = arguments.length;
        for (int i = 0; i < count; i++){
            T key = arguments[i];
            int next = i + 1;

            if (next >= count){
                result.put(key,null);
               break;
            }
            T  value  = arguments[next];
            i = i + 1;
            //@step
            if (null != value) {
                result.put(key, value);
            }
        }
        return result;
    }


}
