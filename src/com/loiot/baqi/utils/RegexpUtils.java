package com.loiot.baqi.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtils {
	
	/*public static Matcher  getMatcher(String regexp ,String str){
		Pattern p = Pattern.compile(regexp);
		Matcher matcher = p.matcher(str);
		return matcher;
		
	}*/
	
	
 public List<String[]> matchGroup(String regexp, String data) {  
	 	System.out.println("regexp:"+regexp);
        Pattern p = Pattern.compile(regexp);  
        Matcher matcher = p.matcher(data);
        List<String[]> list = new ArrayList<String[]>();
        int j=0;
        while (matcher.find()) {
        	String args[] = new String[10];
            int length = matcher.groupCount();
            for (int i = 1; i < length + 1; i++) { 
            	System.out.println("j="+j+" 匹配数据：" + matcher.group(i));
            	args[i-1] = matcher.group(i);
            }
            j++;
            list.add(args); 
        }
        return list;  
    } 
 
 public List<String> matchGroupB(String regexp, String data) {  
	 	//System.out.println("regexp:"+regexp);
     Pattern p = Pattern.compile(regexp);  
     Matcher matcher = p.matcher(data);
     List<String> list = new ArrayList<String>();
     int j=0;
     while (matcher.find()) {
    	 if(!list.contains(matcher.group("vv")))
         list.add(matcher.group("vv")); 
     }
     if(list.size()>1){
 	 	System.out.println("cont >1 regexp:"+regexp);
     }
     return list;  
 } 
 
 public boolean isExitsKeyword(String regexp, String data) {  
	  Pattern p = Pattern.compile(regexp,Pattern.CASE_INSENSITIVE);  
	  Matcher matcher = p.matcher(data);
	  boolean b = matcher.find();
	  return b;  
} 
 
 /*** 
  * replaceAll,忽略大小写 
  *  
  * @param input 
  * @param regex 
  * @param replacement 
  * @return 
  */  
 public String replace(String input, String regex, String replacement) {  
     Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
     Matcher m = p.matcher(input);  
     String result = m.replaceAll(replacement);  
     return result;  
 }  
 
    private static RegexpUtils ru = null;  
    //单太模式  
    public static RegexpUtils getInstance() {  
        if (ru == null) {  
            ru = new RegexpUtils() {  
            };  
        }  
        return ru;  
    }
    
    public static void main(String[] args) {  
    	/*RegexpUtils ru = RegexpUtils.getInstance();  
        String regexp = "(\\d{3}).(\\d{3}).(\\d{2})";  
        String data = "ldap://444.555.66.152:389/  ldap://111.222.33.152:389/  ";  
        System.out.println("" + ru.matchGroup(regexp, data).size());  */
    	
    	//boolean b =RegexpUtils.getInstance().isExitsKeyword("springbvc", " springMVC ");
        //System.out.println("b:" + b);
    	
    	RegexpUtils ru = RegexpUtils.getInstance();  
    	String content ="sdfsadff  sdf  df";
		String keyword="sdf";
    	System.out.println(ru.replace(content, keyword, "111"));;
    	

    }  

}
