package org.ixkit.land.utils;

import com.fasterxml.uuid.Generators;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @class:GuidHelper
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 19/11/2021
 * @version:0.1.0
 * @purpose:
 */
public class GuidUtil {

    public static String getUuid() {
        return Generators.timeBasedGenerator().generate().toString();
    }
    private static String getUuidHex()   {
//        if (1>0){
//           return Generators.timeBasedGenerator().generate().toString();
//        }
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            String digest = bytesToHex(salt.digest());
            return digest;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
