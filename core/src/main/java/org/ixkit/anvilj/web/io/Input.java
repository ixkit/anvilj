package org.ixkit.anvilj.web.io;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public class Input {

    public static Map<String, Object> asParameters(HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String[]> map=request.getParameterMap();
        for(String key :map.keySet()) {
            result.put(key, map.get(key)[0]);
        }
      return result;
    }
}


