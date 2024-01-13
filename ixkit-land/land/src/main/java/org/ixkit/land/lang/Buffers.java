package org.ixkit.land.lang;

import com.google.common.base.Strings;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Buffers {

    public static void readLine(String lines, Consumer<String> onLine){

        try (final var scanner = new Scanner(lines)){
            while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               onLine.accept(line);
            }
        }
    }
    public static String trimLines(String lines, String prefixTag, boolean ignoreBlank){
        List list =  new LinkedList();
        readLine(lines,(x)->{
            String y = x;
            if (ignoreBlank){
                y = y.trim();
            }
            if ( y.startsWith(prefixTag)){

            }else{
               list.add(x);
            }
        });
       return Lists.toLines(list);
    }
    public static String trimLines(String lines, String prefixTag) {
        return trimLines(lines,prefixTag,true);
    }
}
