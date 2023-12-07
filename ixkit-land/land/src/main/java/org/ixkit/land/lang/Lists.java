package org.ixkit.land.lang;

import com.google.common.base.Joiner;

import java.util.List;

public class Lists {

    public static String toString(List list){
        String result = Joiner.on(",").join(list);
        return result;
    }
}
