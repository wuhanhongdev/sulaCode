package com.sula.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerUtil {
	
    public static void makeHtml(String sourceFile,String aimDir,String aimFile,Map<String, Object> paramMap) {
    	try {  
	    	Configuration configuration = new Configuration();
	    	
	    	/**
	    	 * 获取模板文件夹地址
	    	 */
	    	String templateDir = ConfigUtils.getProperty("file.template");
	    	configuration.setDirectoryForTemplateLoading(new File(templateDir)); 
	    	/**
	    	 * 获取生成目标文件夹地址
	    	 */
	    	String successDir = ConfigUtils.getProperty("file.aimplace");
	    	File outFile = new File(successDir+aimDir+"/"+aimFile);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
	    	
	        configuration.setObjectWrapper(new DefaultObjectWrapper());  
	        configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
	        //获取或创建一个模版。  
	        Template template = configuration.getTemplate(sourceFile);
	        Writer writer  = new OutputStreamWriter(new FileOutputStream(successDir+aimDir+"/"+aimFile),"UTF-8");
	        template.process(paramMap, writer);
	        writer.close();
    	} catch (Exception e) {  
    		e.printStackTrace();  
        } finally {
        }
    }
    
    public static void main(String[] args) {
    	/**
    	 * 使用方法如下:
    	 */
    	for (int i = 0; i < 100; i++) {
    		Map<String, Object> paramMap = new HashMap<String, Object>();  
	        paramMap.put("description", "使用Freemarker生成静态文件！");
			makeHtml("static.html","","d_"+i,paramMap);
		}
    }
}
