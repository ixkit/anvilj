//package org.ixkit.anvil.util;
//
//public class HexStrings  {
//    public static HexStrings value(String hex){
//        return new HexStrings()._(hex);
//    }
//    private StringBuffer stringBuffer = new StringBuffer();
//    public HexStrings(){
//
//    }
//    public HexStrings _(String hex){
//        stringBuffer.append(hex);
//        return this;
//    }
//    public HexStrings _zero(){
//        return _("00");
//    }
//    public String toString(){
//        return stringBuffer.toString();
//    }
//
//    public static boolean match(String hex, int index, String target){
//        try {
//            int exist = hex.indexOf(target);
//            boolean result = exist == index;
//            return result;
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return false;
//    }
//}
