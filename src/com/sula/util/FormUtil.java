package com.sula.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;




import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jfinal.plugin.activerecord.Record;

public class FormUtil {
	 private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("yyMMddHHmmssS");
 
    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");
 
    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;
 
    private static final int MAX = 9999;
 
	public static Record formToRecord(HttpServletRequest request,
			Enumeration<String> e) {
		Record map = new Record();
		while (e.hasMoreElements()) {
			String keyString = String.valueOf(e.nextElement());
			Object valueObject = null;
			if (!keyString.equals("timeStamp")) {
				valueObject = request.getParameter(keyString);
				if(valueObject!=null) {
					map.set(keyString, valueObject);
				}
			}
		}
		return map;
	}
	/****************************
	 * 
	 */
	public static String renameFile(File file,String path){
		Long newName = System.currentTimeMillis() ;
		String oldpath = file.getPath();
		String suffix = oldpath.substring(oldpath.lastIndexOf("."), oldpath.length());
		//System.out.println(suffix);
		String path_ = path+newName+suffix;
		//System.out.println(path_);
		file.renameTo(new File(path_));
		return newName+suffix;
	}
	public static void main(String[] args){
		String json="{\"code\":1,\"data\":\"42784_0,37300_847\"}";  
//		System.out.println(FormUtil.getList(json));
		Record r = FormUtil.getMap(json);
		String temp = r.get("data");
		String[] codes = temp.split(",");
		String[] o = codes[0].split("_");
		System.out.println(o[0]+"--"+o[1]);
	} 
	public static String getJsonString(String urlPath) throws Exception {  
		System.out.println("路径："+urlPath);
        URL url = new URL(urlPath);  
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.connect();  
        InputStream inputStream = connection.getInputStream();  
        //对应的字符编码转换  
        Reader reader = new InputStreamReader(inputStream, "UTF-8");  
        BufferedReader bufferedReader = new BufferedReader(reader);  
        String str = null;  
        StringBuffer sb = new StringBuffer();  
        while ((str = bufferedReader.readLine()) != null) {  
            sb.append(str);  
        }  
        reader.close();  
        connection.disconnect();  
        return sb.toString();  
    } 
	/***
	 * 
	 */
	public static String getOrderSN(){
		Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        } 
        return sb.toString();
	}
	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
		  if(a.size() != b.size())
		    return false;
		  Collections.sort(a);
		  Collections.sort(b);
		  for(int i=0;i<a.size();i++){
		    if(!a.get(i).equals(b.get(i)))
		      return false;
		  }
		  return true;
		}
	public static List<Record> getList(String json){
		JSONArray array = JSONArray.fromObject(json);
		System.out.println(array);  
		@SuppressWarnings("unchecked")
		List<Record> list = (List<Record>)JSONArray.toCollection(array);
		System.out.println(list.size());
		return list;
	}
	public static Record getMap(String json){
		Record data = new Record(); 
		 JSONObject jsonObject = JSONObject.fromObject(json);  
	       @SuppressWarnings("rawtypes")
		Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = jsonObject.get(key).toString();  
	           data.set(key, value);  
	       }  
	       return data;
	}
	public static String returnStrCodes(List<Record> code){
		String str = "";
		for (int i = 0; i < code.size(); i++) {
			Record r = code.get(i);
			if(i == code.size() - 1){
				str += r.get("hy_code");
			}else{
				str += r.get("hy_code")+","; 
			}
		}
		return str;
	} 
}
