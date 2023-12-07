package org.ixkit.land.lang;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.HashMap;

public   class Argument<K,V> extends HashMap  {

    public static <T> Argument of(T... arguments) {
        Argument result = new Argument();
        result.putAll(HashMaps.toMap(arguments));
        return result;
    }
    public static <T> Argument of(String[] arguments) {
        Argument result = new Argument();
        for (int i = 0; i <arguments.length ; i++) {
            Object key = arguments[i];
            Object value = null;
            if (i +1 < arguments.length){
                value =  arguments[i+1];
            }
            result.put(key,value);
        }

        return result;
    }

    /*
      retrieve object fields value into argument
     */
    public static <T> Argument ofObject(Object object,T... excludeNames ) {
        Argument excludes = Argument.of(excludeNames);
        Argument result = new Argument();
        Field[] fields = ReflectUtil.getFields(object.getClass());
        for (int i = 0; i < fields.length; i++) {
            Field item = fields[i];
            String name = item.getName();
            if (null != excludes.get(name)) continue;
            Object value =  ReflectUtil.getFieldValue(object,item);
            if (null != value) {
                result.put(name, value);
            }
        }
        return result;
    }

    public String getAsString(Object key){
        try{
            return  _getAsString(key);
        }catch (Exception ex){
            ex.printStackTrace();;
        }
        return null;
    }
    public String  _getAsString(Object key){

        Object v = this.get(key);
        if (null == v){
            return null;
        }
        if (v instanceof String) return  (String)v;
        if (v instanceof Long)  return Long.toString((Long)v);
        if (v instanceof Integer) return Integer.toString((Integer)v);

        return v.toString();
    }
    public Long getAsLong(Object key){
        Object v = this.get(key);
        if (null == v){
            return null;
        }
        if (v instanceof String){
            return Long.parseLong((String) v);
        }
        if (v instanceof Long) return (Long)v;

        String buf = ""+ v;
        return Long.parseLong(buf);
    }
}
