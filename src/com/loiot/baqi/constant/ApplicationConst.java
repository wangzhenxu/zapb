package com.loiot.baqi.constant;


import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 公用静态常量
 * @author FrancisJin
 * @since 2012-01-14
 */
public class ApplicationConst {
	
	/**
	 * 项目相关参数
	 */
	private static ResourceBundle appBundle = PropertyResourceBundle.getBundle("conf/application");
	
	/**
	 * 消息相关
	 */
	private static ResourceBundle messageBundle = PropertyResourceBundle.getBundle("conf/message" );

   
    
    

    public static String getMessage(String code,String... values) {
	  String messvalue = messageBundle.getString(code);
	  String v="";
	try {
		v = new String(messvalue.getBytes("ISO-8859-1"),"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  StringBuffer buffer=new StringBuffer();
	    //对应占位符参数值
	    String[] vs=values;
	  	for(int i = 0; i < vs.length; i++){
	  	//遍历参数数组
	  		//替换前清空原有替换值
				buffer.delete(0, buffer.length());
				Pattern pattern=Pattern.compile("\\{"+i+"\\}");
				Matcher matcher=pattern.matcher(v);
					while(matcher.find()){
				          matcher.appendReplacement(buffer, vs[i]);
					}
		        matcher.appendTail(buffer);
		        //进行下一次替换
		        v=buffer.toString();
	  	}
		//返回后替换的字符串
		return buffer.toString();
      
    }
    public static String getMessage(String code) throws UnsupportedEncodingException{
  	  String messvalue = messageBundle.getString(code);
  	  String v =  new String(messvalue.getBytes("ISO-8859-1"),"utf-8");
  	  return v;
     }
      


	public static void main(String args[]) throws UnsupportedEncodingException{
       //System.out.println(appBundle.getString("product.upload.pic.path"));
	  String str = ApplicationConst.getMessage("10102", "23","34");
	  System.out.println(str);

	}
}
