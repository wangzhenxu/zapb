package com.loiot.baqi.constant;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FtlUtil {

	private FreeMarkerConfigurationFactoryBean bean = null;
	private Configuration cfg = null;
	
	
	private static FtlUtil instance;
	
	private FtlUtil(){
		bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/template/message/");
		try {
			cfg = bean.createConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static FtlUtil getInstance(){
		if(instance == null){
			instance = new FtlUtil();
		}
		return instance;
	}
	
	/**
	 * 得到ftl字符串
	 * @param ftlName
	 * @param paraMap
	 * @return
	 * @author sujinbo
	 */
	public String getContent(String ftlName,Map<String,Object> paraMap){
		try {
			Template temp = cfg.getTemplate(ftlName,"UTF-8");
			return FreeMarkerTemplateUtils.processTemplateIntoString(temp, paraMap);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
