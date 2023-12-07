package org.ixkit.land.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @class:XObject
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 27/12/2021
 * @version:0.1.0
 * @purpose:
 */
public class X {

    public static  <T> T  with(T t, Consumer<T> consumer){
        consumer.accept(t);
        return t;
    }

    public static <T> T __(Class<T> tClass, Consumer<T> consumer){
        return build(tClass,consumer);
    }

    public static <T> T build(Class<T> tClass, Consumer<T> consumer){
        try {
            T obj = tClass.getDeclaredConstructor().newInstance();
            consumer.accept(obj);
            return obj;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T toValue( Object value, Class targetClass){
        if (null == value) return null;
        //@case
        if (targetClass == String.class) {
            if (value instanceof String) return   (T)value ;
            return  (T)(value +"");
        }
        //@case
        if (targetClass == Integer.class) {
            if (value instanceof Integer) {
                return (T) value;
            }
            if (value instanceof String) {
                //if (isPostgresql())
                {
                    String buf = (String) value;
                    return (T) Integer.valueOf(buf);
                }
                //other not care
            }
        }
        //@case
        if (targetClass == Long.class) {
            if (value instanceof Long) {
                return (T) value;
            }
            if (value instanceof String) {
                //if (isPostgresql())
                {
                    String buf = (String) value;
                    return (T) Long.valueOf(buf);
                }
                //other not care
            }
        }
        //@case

        return (T)value;
    }


    public static Class fetchGenericType(Object object){
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class tClass = (Class) actualTypeArguments[0];

        System.out.println(tClass.getName());
        return tClass;
    }

    public static void _if(Supplier<Boolean> ifTrue, Consumer then){
        if (ifTrue.get()){
            then.accept(true);
        }
    }

    public static void _if( boolean ifTrue, Consumer then){
        if (ifTrue){
            then.accept(true);
        }
    }
}
