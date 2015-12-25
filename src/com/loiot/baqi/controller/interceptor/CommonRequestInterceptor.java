package com.loiot.baqi.controller.interceptor;


import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * controller的拦截器，用于进入controller的拦截
 * @Id $Id: CommonRequestInterceptor.java 7790 2012-06-05 06:14:22Z yulianyu $
 * @author yulianyu
 */
public class CommonRequestInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(CommonRequestInterceptor.class);
    
    private long currmillis = 0;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
    	String url = request.getRequestURL().toString();  
    	//若没有获取到当前用户，则尝试通过openid获取
		return true;
	}
    /* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		StringBuffer sb = new StringBuffer(1000);
		sb.append("执行完成请求：").append(request.getRequestURL());
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		int i = 0;
        if(keySet != null)
		for (String key : keySet) {
            if(key !=null){
            	sb.append(i==0?"?":"&");
            	sb.append(key).append("=");
            	sb.append(request.getParameter(key));
            	i++;
            }
        }
        logger.info(sb.toString());
        logger.info("\n执行时间(秒)："+(System.currentTimeMillis() - currmillis) / 1000d);
	}
}
