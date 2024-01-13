package org.ixkit.land.lang;

import com.google.common.base.Joiner;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lists {

    public static String toString(List list){
        String result = Joiner.on(",").join(list);
        return result;
    }

    public static String toLines(List list){
        String result = Joiner.on("\r\n").join(list);
        return result;
    }
    public static boolean isEmpty(List list){
        return null == list || list.isEmpty();
    }


    public static List<String> of(String commaStrings){
        List<String> result = Stream.of(commaStrings.split(",", -1))
                .collect(Collectors.toList());
        return result;
    }
}
