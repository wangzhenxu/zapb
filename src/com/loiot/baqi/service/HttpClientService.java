package com.loiot.baqi.service;

/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.loiot.baqi.constant.ApplicationConst;
import com.loiot.baqi.model.ResAtom;
import com.loiot.baqi.model.ResSubject;
import com.loiot.commons.utils.JsonUtil;
import com.timeloit.pojo.User;
import com.timeloit.pojo.mongo.NodeData;

/**
 * @author yulianyu $Id$
 * 
 */

@Service("httpClientService")
public class HttpClientService {
	private Logger logger = Logger.getLogger(HttpClientService.class);
	@Autowired
	RestTemplate restTemplate;

	private static HttpEntity<String> prepareGet(MediaType type, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		headers.set("Authorization", "OAuth2 " + token);
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}

	public static void main(String[] args) {
		RestTemplate restTemplate = getTemplate();

		String jsonParam = "{\"pwd\":\"123456\",\"clientId\":\"88\",\"nm\":\"wangzx\"}";
		String url = "http://localhost:8080/yunjian/oauth2/assessTokenLogin";

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("data", jsonParam);
		String response = restTemplate.postForObject(url, map, String.class);
		System.out.println("-------------------------------------------------:"
				+ response);
	}

	public void getNode(RestTemplate rest) {
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				"token");
		ResponseEntity<String> response = rest
				.exchange(
						"http://localhost:8080/yunjian/scene/{sceneSn}/node/{nodeSn}?data={data}",
						HttpMethod.GET, entity, String.class, "123456",
						"321321", "{\"st\":\"2012-11-01 22:22:10\"}");
		String jsonStr = response.getBody();
		try {
			NodeData data = JsonUtil.toObject(jsonStr, "currentData",
					NodeData.class);
			// System.out.println(data.getSceneSn());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void remove(RestTemplate rest) {
		rest.delete("http://localhost/yunjian/scene/{id}", "99");
	}

	private static RestTemplate getTemplate() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"application-mvc.xml");
		RestTemplate template = (RestTemplate) ctx.getBean("restTemplate");
		return template;
	}

	/**
	 * 获取登录信息
	 * 
	 * @param request
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public String getLoginInfo(HttpServletRequest request, String url,
			String jsonParam) {
		String token = "";
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				token);
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class, jsonParam);
		return response.getBody();
	}

	/**
	 * 找回密码(发送邮件)
	 * 
	 * @param request
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public String getpwd(HttpServletRequest request, String url,
			String jsonParam) {
		String token = "";
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				token);
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class, jsonParam);
		return response.getBody();
	}

	/**
	 * 发送
	 * 
	 * @param request
	 * @param url
	 * @param jsonParam
	 * @return
	 */
	public String send(HttpServletRequest request, String type, String url,
			String jsonParam) {
		String token = "";

		if (type.equals("post")) {
			HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
					token);
			ResponseEntity<String> response = restTemplate.exchange(url,
					HttpMethod.POST, entity, String.class, jsonParam);
			return response.getBody();
		} else if (type.equals("put")) {
			HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
					token);
			url = url + "&_method=PUT";
			ResponseEntity<String> response = restTemplate.exchange(url,
					HttpMethod.POST, entity, String.class, jsonParam);
			return response.getBody();
		} else if (type.equals("get")) {
			HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
					token);
			ResponseEntity<String> response = restTemplate.exchange(url,
					HttpMethod.GET, entity, String.class, jsonParam);
			return response.getBody();
		} else if (type.equals("delete")) {
			HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
					token);
			ResponseEntity<String> response = restTemplate.exchange(url,
					HttpMethod.DELETE, entity, String.class, jsonParam);
			return response.getBody();
		}
		return "";
	}

	/**
	 * 调用yunjian注册wagang网站用户
	 * 
	 * @param user
	 * @throws Exception 
	 * @throws RestClientException 
	 */
	public void regUser(User user) throws RestClientException, RuntimeException {
		// TODO Auto-generated method stub
		//String url = ApplicationConst.YUNJIAN_URL + "env/user?data={data}";
		String ulr="";
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", user.getLoginName());
		params.put("password", user.getPassword());
		params.put("email",user.getEmail());
		
		String token="";
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				token);
		ResponseEntity<String> response;
		ResAtom ra=new ResAtom();
		try {
			response = restTemplate.exchange("",
					HttpMethod.POST, entity, String.class, JsonUtil.toJson(params));
			String jsonData= response.getBody();
			ra = JsonUtil.toObject(jsonData, "ra", ResAtom.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("注册用户失败");
		}
		if (ra.getSt() < 0) {
			logger.error("注册用户失败");
		}
	}

	/**
	 * @param loginName 
	 * @param userId
	 * @param pwd
	 * @throws Exception 
	 * @throws RestClientException 
	 */
	public String userLogin(String loginName, Long userId, String pwd) throws RestClientException, RuntimeException {
		// TODO Auto-generated method stub
		String token = "";
		//String url=ApplicationConst.YUNJIAN_URL  + "oauth2/assessTokenLogin"+"?data={data}";
		String url="";
		Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("nm", loginName);
        paramMap.put("pwd", pwd);
        //paramMap.put("clientId",ApplicationConst.CLIENT_ID);
        
		
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				token);
		ResponseEntity<String> response;
		try {
			response = restTemplate.exchange(url,
					HttpMethod.GET, entity, String.class, JsonUtil.toJson(paramMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取accessToken失败  paramMap:"+paramMap.toString());
		}
		String jsonData= response.getBody();
        ResSubject rs = JsonUtil.toObject(jsonData, ResSubject.class);
        Map raMap=(HashMap)rs.get("ra");
        if(Integer.parseInt(raMap.get("st").toString())>0){
        	return ((HashMap)rs.get("data")).get("accessToken").toString();
        }else{
        	throw new RuntimeException("获取accessToken失败  paramMap:"+paramMap.toString());
        }
	}

	/**
	 * 通过productId来获取apikey,ssn
	 * @param accessToken 
	 * @param sceneProductId
	 * @throws Exception 
	 */
	public Map<String,Object> getApiSSn(String accessToken, Long sceneProductId) throws RuntimeException {
		// TODO Auto-generated method stub
//		http://api2.loiot.com/prod/create.action?productId=152&accessToken=8ff096aa15a2d7107385d437d9de29c3
		sceneProductId=214L;
		//String url=ApplicationConst.YUNJIAN_URL  + "prod/create.action"+"?productId={productId}";
		
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				accessToken);
		ResponseEntity<String> response = restTemplate.exchange("",
				HttpMethod.GET, entity, String.class, sceneProductId);
		String jsonData= response.getBody();
        ResAtom ra=null;
        Map<String,Object> dataObj=new HashMap<String,Object>();
		try {
			ra = JsonUtil.toObject(jsonData, "ra", ResAtom.class);
			dataObj=(HashMap<String,Object>)JsonUtil.toObject(jsonData, "data", HashMap.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(ra.getSt()>0 && Integer.parseInt(dataObj.get("hasKey").toString())>0){
        	return dataObj; 
        }else{
        	throw new RuntimeException("获取api,ssn失败");
        }
	}

	/**
	 * http://api.loiot.com/m/product/scene? data={"name":”新网关名称”,"snValue":”123456”,”keyValue”:”321321”,”isPublic”:0}
	 * &accessToken=74b7bf7d28b37697111b5ad1e0a8b6e6
	 * 自动注册网关
	 * @param string
	 * @param string2
	 * @param string3
	 * @throws Exception 
	 * @throws RestClientException 
	 */
	public void addScene(String accessToken,String apiKey, String ssn, String title) throws RestClientException, RuntimeException {
		// TODO Auto-generated method stub
		//String url=ApplicationConst.YUNJIAN_URL  + "/m/product/scene"+"?data={data}";
		Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("name", title);
        paramMap.put("snValue", ssn);
        paramMap.put("keyValue", apiKey);
        paramMap.put("isPublic", 1);
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON,
				accessToken);
		ResponseEntity<String> response=null;
		ResAtom ra=null;
		try {
			response = restTemplate.exchange("",
					HttpMethod.POST, entity, String.class, JsonUtil.toJson(paramMap));
			String jsonData= response.getBody();
	        ra = JsonUtil.toObject(jsonData, "ra", ResAtom.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(ra!=null && ra.getSt()>0){
        	logger.info("添加网关成功！");
        }else{
        	logger.info("添加网关失败！");
        }
	}
	
}
