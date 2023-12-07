package org.ixkit.land.lang;

/**
 * @class:Doubles
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 02/03/2022
 * @version:0.1.0
 * @purpose:
 */
public class Doubles {

    public static Double asDouble(Object value){
        if (null == value) return null ;
        if (value instanceof Integer){
            return Double.valueOf (""+(Integer)value);
        }
        if (value instanceof Double){
            return (Double) value;
        }
        try{
            return Double.valueOf (""+ value);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;

    }
}
