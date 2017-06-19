package com.sula.util;
import java.util.Arrays;

public class VerifyMD5 {
    
    
    private static String mySign = "sula";
    
    public static boolean Sign(String nativeSign,String ...sign){
        sign = sort(sign);
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < sign.length;i++){
            sb.append(sign[i]);
        }
        sb.append(mySign);
        return MD5Util.encodeToMd5(sb.toString()).equals(nativeSign);
    }
    public static String[] sort(String ...sign){
        Arrays.sort(sign);
        return sign;
    }
}
