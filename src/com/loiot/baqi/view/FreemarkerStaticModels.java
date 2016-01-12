/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */
package com.loiot.baqi.view;

/**
 * @author yanjg 
 * 在freemarker中使用java静态类
 * @creation 2012-11-8
 */
import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class FreemarkerStaticModels extends HashMap<Object, Object> {

    private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;
    private Properties staticModels;

    private FreemarkerStaticModels() {

    }

    public static FreemarkerStaticModels getInstance() {
        if (FREEMARKER_STATIC_MODELS == null) {
            FREEMARKER_STATIC_MODELS = new FreemarkerStaticModels();
        }
        return FREEMARKER_STATIC_MODELS;
    }

    public Properties getStaticModels() {
        return staticModels;
    }

    public void setStaticModels(Properties staticModels) {
        if (this.staticModels == null && staticModels != null) {
            this.staticModels = staticModels;
            Set<String> keys = this.staticModels.stringPropertyNames();
            for (String key : keys) {
                FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
            }
            loadAllEnumClass();
        }
    }

    public static TemplateHashModel useStaticPackage(String packageName) {
        try {
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
            return fileStatics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 加载项目所有枚举类不用每次，添加到freearker-static.properties文件中
     */
    public static void loadAllEnumClass(){
    	String classDic="com/loiot/baqi/status";
	    	String enumDirct = FreemarkerStaticModels.class.getClassLoader().getResource("").getPath()+classDic;
	    	//System.out.println(enumDirct);
	    	 File file=new File(enumDirct);
	    	  File[] tempList = file.listFiles();
	    	  //System.out.println("该目录下对象个数："+tempList.length);
	    	  for (int i = 0; i < tempList.length; i++) {
	    	   if (tempList[i].isFile()) {
	    		  String classKey = tempList[i].getName().replace(".class", "");
	    		  String classValue = classDic.replaceAll("/", ".")+"."+classKey;
	              FREEMARKER_STATIC_MODELS.put(classKey, useStaticPackage(classValue));
	    		  //System.out.println("文     件key："+classKey);
	    		  //System.out.println("文     件value："+useStaticPackage(classValue));
	    	   }
	    }
    }
}
