package org.ixkit.land.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class:Console
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 15/07/2022
 * @version:0.1.0
 * @purpose:
 */
public class Console {

    public static String read(String tips){
        try {
            System.out.println(tips);
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            int value = 0;
            String result =null;
            while ((value = bufferedReader.read()) != -1) {
                char c = (char) value;
                result =  String.valueOf(c);
                break;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
