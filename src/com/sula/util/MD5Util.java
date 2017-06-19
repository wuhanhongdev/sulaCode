package com.sula.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class MD5Util {
	public static String encodeToMd5(String source) {
		if(source==null)
			return null;
		
		try{
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			
			char hexDigits[] = {
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
					'e', 'f'};
			
			byte[] strTemp=source.getBytes("utf-8");
			mdTemp.update(strTemp);
			
			byte[] md = mdTemp.digest();
			int k = 0;
			int j = md.length;char str[] = new char[j * 2];
			
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>>4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		
	}
}
