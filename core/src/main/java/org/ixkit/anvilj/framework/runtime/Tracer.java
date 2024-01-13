package org.ixkit.anvilj.framework.runtime;


/**
 * @class:Tracer
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 02/03/2022
 * @version:0.1.0
 * @purpose:
 */
public class Tracer {
    public static String trace(Object o){
        try {
            String buf = "";//JsonUtil.serialize(o);
            return buf;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
