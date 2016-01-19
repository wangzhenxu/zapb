package com.loiot.baqi.controller;

import java.util.*;

import com.loiot.baqi.pojo.*;
import com.loiot.baqi.dao.*;
import com.loiot.baqi.service.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loiot.baqi.pojo.*;
import com.loiot.baqi.constant.Const;
import com.loiot.baqi.constant.URLConst;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.service.*;
import com.loiot.baqi.status.AccountType;
import com.loiot.baqi.status.SuggestType;
import com.loiot.commons.message.util.JsonUtil;
import com.timeloit.pojo.Account;
import com.loiot.baqi.utils.UserSessionUtils;


/**
 * 建议 处理器。
 * TgSuggestBugInfo
 * @author  wangzx 
 * @creation 2015-12-30
 */


@Controller
@RequestMapping(value = "/tgSuggestBugInfo")
public class TgSuggestBugInfoController {
    
    public static final AjaxResponse NAME_EXIST= new AjaxResponse(-100, "建议已存在");
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private TgSuggestBugInfoService tgSuggestBugInfoService;
	
	private TgSuggestBugInfo tgSuggestBugInfo;
	
	private HashMap<String,Object> pmap = new HashMap<String,Object>();
	
	/**
     * 跳转  建议列表页
     * 
     * @return 模板位置
     */
    @RequestMapping(value = "/suggestList")
    public String suggestList(@RequestParam(value = "pi", defaultValue = "0") int pageIndex,
    		@RequestParam(value = "jsonParam", defaultValue = "{}") String jsonParam,
    	TgSuggestBugInfo p, ModelMap model)throws Exception {
    	HashMap<String,Object> paramMap=this.getParaMap(jsonParam, model);
    	paramMap.put("qtype", "like");
    	paramMap.put("sugType",com.loiot.baqi.status.SuggestType.SUGGEST.getCode());
    	//用户数据过滤
    	/*
    	if(UserSessionUtils.getAccountType()==AccountType.HR.getCode() || UserSessionUtils.getAccountType()==AccountType.JOB_HUNTER.getCode() ){
    		paramMap.put("inPerson", UserSessionUtils.getAccount().getAccountId());
    	}*/
        Pager<TgSuggestBugInfo> pager = tgSuggestBugInfoService.queryTgSuggestBugInfoListPage(paramMap , pageIndex);
        model.put("pager", pager);
        model.put("jsonParam", jsonParam);
        return "/suggestInfo/suggestInfo_list";
    }
    
    /**
     * 跳转  建议列表页
     * 
     * @return 模板位置
     */
    @RequestMapping(value = "/bugList")
    public String bugList(@RequestParam(value = "pi", defaultValue = "0") int pageIndex,
    		@RequestParam(value = "jsonParam", defaultValue = "{}") String jsonParam,
    	TgSuggestBugInfo p, ModelMap model)throws Exception {
    	HashMap<String,Object> paramMap=this.getParaMap(jsonParam, model);
    	paramMap.put("qtype", "like");
    	paramMap.put("sugType",com.loiot.baqi.status.SuggestType.BUG.getCode());

    	//用户数据过滤
    	/*
    	if(UserSessionUtils.getAccountType()==AccountType.HR.getCode() || UserSessionUtils.getAccountType()==AccountType.JOB_HUNTER.getCode() ){
    		paramMap.put("inPerson", UserSessionUtils.getAccount().getAccountId());
    	}*/
        Pager<TgSuggestBugInfo> pager = tgSuggestBugInfoService.queryTgSuggestBugInfoListPage(paramMap , pageIndex);
        model.put("pager", pager);
        model.put("jsonParam", jsonParam);
        return "/suggestInfo/bugInfo_list";
    }
    
    
    /**
     * 获取查询条件
     * @param jsonParam
     * @param model
     * @return
     */
    public HashMap<String,Object> getParaMap(String jsonParam,ModelMap model){
    	HashMap<String,Object> newParamMap = newParamMap =  new HashMap<String,Object>();
    	 HashMap<String,Object> paramMap =JsonUtil.toObject(jsonParam, HashMap.class);
		Iterator iter = paramMap.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
    		Object key = entry.getKey();
    		Object val = entry.getValue();
    		if(key.toString().equals("name")){
    			newParamMap.put("nameT", val);
    		}else
    		if(String.valueOf(val).length()>0){
    			newParamMap.put(String.valueOf(key), val);
        		model.put(String.valueOf(key), val);
    		}
		}
		return newParamMap;
    }

    /**
     * 跳转到添加页面
     * 
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAddTgSuggestBugInfo(ModelMap model) {
        
        return "/suggestInfo/suggestInfo_add";
    }

   

    /**
     * 跳转到编辑页面
     * 
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public String toEditTgSuggestBugInfo(
    		@RequestParam(value = "id", required = true) java.lang.Long id,
    		@RequestParam(value = "type", required = true) int type,
    		ModelMap model)throws Exception {
        //model.put("p", tgSuggestBugInfoService.getTgSuggestBugInfoById(id));
    	String resultType="";
    	if(id==null){
    		return URLConst.ERROR_URL;
    	}
    	model.put("pid",  id);
    	
    	if((int)SuggestType.SUGGEST.getCode()==type){
    		resultType="/suggestInfo/suggestInfo_add";
    	}else 
		if((int)SuggestType.BUG.getCode()==type){
    		resultType="/suggestInfo/bugInfo_add";
    	}else {
    		resultType=URLConst.ERROR_URL;
    	}
        return resultType;
    }

    /**
     * 更新 建议
     * 
     * @param p 对象参数
     * @return ajax响应
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object editTgSuggestBugInfo(TgSuggestBugInfo p,HttpSession session,HttpServletRequest request) {
    	try {
           p.setUpdatePerson(UserSessionUtils.getAccount().getAccountId());
           p.setUpdateTime(new Date());
           tgSuggestBugInfoService.updateTgSuggestBugInfo(p);
    	} catch (Exception e) {
			  e.printStackTrace();
			  return AjaxResponse.FAILED;
		}
       

        return AjaxResponse.OK;
    }

    /**
     * 跳转到查看页面
     * 
     * @return
     */
    @RequestMapping(value = "/toView")
    public String toViewTgSuggestBugInfo(@RequestParam(value = "id", required = true) java.lang.Long id, 
    		@RequestParam(value = "type", required = true) int type,
    		ModelMap model)throws Exception {
    	if(id==null){
    		return URLConst.ERROR_URL;
    	}
    	//model.put("p", tgSuggestBugInfoService.getTgSuggestBugInfoById(id));
    	 model.put("pid",  id);
     	String resultType="";

    	 if((int)SuggestType.SUGGEST.getCode()==type){
     		resultType="/suggestInfo/suggestInfo_add";
     	}else 
 		if((int)SuggestType.BUG.getCode()==type){
     		resultType="/suggestInfo/bugInfo_add";
     	}else {
     		resultType=URLConst.ERROR_URL;
     	}
    	 return resultType;
    }

    /**
     * 删除  建议
     * 
     * @param id TgSuggestBugInfoID
     */
    @RequestMapping(value = "/delete")
    public String deleteTgSuggestBugInfo(@RequestParam(value = "id", required = true) java.lang.Long id,HttpServletRequest request)throws Exception {
    	tgSuggestBugInfoService.deleteTgSuggestBugInfo(id);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/tgSuggestBugInfo/"), s.length());
        return "redirect:"+redirectStr;
    }
    
    /**
     * ajax删除  建议
     * 
     * @param id TgSuggestBugInfoID
     */
    @RequestMapping(value = "/ajaxDelete")
    @ResponseBody
    public Object ajaxDeleteTgSuggestBugInfo(@RequestParam(value = "id", required = true) java.lang.Long id) {
    	try {
    		TgSuggestBugInfo p = new TgSuggestBugInfo();
        	p.setSuggestId(id);
			tgSuggestBugInfoService.deleteTgSuggestBugInfo(p);
			return AjaxResponse.OK;
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  return AjaxResponse.FAILED;
		}
    }
    
    /**
     * ajax 根据id查询实体bean
     * @return
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public Object ajaxGetById(@RequestParam(value = "id", required = true) java.lang.Long id)throws Exception {
    	TgSuggestBugInfo p=null;
     	//用户数据过滤
     	if(UserSessionUtils.getAccountType()==AccountType.ADMIN.getCode() || UserSessionUtils.getAccountType()==AccountType.TESTING.getCode() || UserSessionUtils.getAccountType()==AccountType.PRODUCT_MANAGER.getCode() ){
     		  p = tgSuggestBugInfoService.getTgSuggestBugInfoById(id);
     	} 
     	if(p==null){
     		return AjaxResponse.NOEXITS;
     	}
    	return AjaxResponse.OK(p);
    }
    
    /**
     * 更新 （停用、启用状态）
     * 
     * @param id 
     */
    @RequestMapping(value = "/modifyDeleteStatus")
    public String modifyDeleteStatus(@RequestParam(value = "id", required = true) java.lang.Long id,
    		@RequestParam(value = "deleteStatus", required = true) java.lang.Integer isDelete,
    		HttpServletRequest request)throws Exception {
    	TgSuggestBugInfo p = new TgSuggestBugInfo();
    	p.setSuggestId(id);
    	//p.setIsDelete(isDelete);
    	tgSuggestBugInfoService.updateTgSuggestBugInfo(p);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/tgSuggestBugInfo/"), s.length());
        return "redirect:"+redirectStr;
    }
    
    /**
     * 查询，名称是否存在，验证唯一性
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/checkNameExits")
    @ResponseBody
    public Object checkNameExits(@RequestParam(value = "name", required = true) java.lang.String name,
    		@RequestParam(value = "oldname",required = false) java.lang.String oldName,
    		@RequestParam(value = "flag", required = true) String flag
    		) throws Exception
    {
    	//验证唯一性
    	pmap.clear();
    	pmap.put("name", name);
    	if("edit".equals(flag) && oldName.equals(name)){
        	return AjaxResponse.OK(null);
    	} 
    	int result=tgSuggestBugInfoService.getTgSuggestBugInfoListCount(pmap);
    	if(result>0){
	        return NAME_EXIST;
		}
    	return AjaxResponse.OK(null);
    }
    

}
