//package org.ixkit.anvil.util;
//
//import net.lbruun.hexutils.Hex;
//
//public class HexHelper {
//
//    public static byte[] hexStringToBytes(String hexString) {
//
//        return Hex.hexStrToBytes(hexString);
//
//    }
//
//    public static String byteToHexString(byte[] bytes) {
//        return Hex.bytesToHexStr(bytes, Hex.HexCase.UPPER);
//    }
//
//
//    public static String HighAndLowSwap(String hexString) {
//        return reverseHex(hexString);
//    }
//
//    public static String reverseHex(String hex) {
//        char[] charArray = hex.toCharArray();
//        int length = charArray.length;
//        int times = length / 2;
//        for (int c1i = 0; c1i < times; c1i += 2) {
//            int c2i = c1i + 1;
//            char c1 = charArray[c1i];
//            char c2 = charArray[c2i];
//            int c3i = length - c1i - 2;
//            int c4i = length - c1i - 1;
//            charArray[c1i] = charArray[c3i];
//            charArray[c2i] = charArray[c4i];
//            charArray[c3i] = c1;
//            charArray[c4i] = c2;
//        }
//        return new String(charArray);
//    }
//
//}
