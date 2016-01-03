
package com.loiot.baqi.controller;

import java.util.*;

import com.loiot.baqi.pojo.*;
import com.loiot.baqi.dao.*;
import com.loiot.baqi.service.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.loiot.baqi.utils.UserSessionUtils;
import com.loiot.commons.message.util.JsonUtil;
import com.timeloit.pojo.Account;

/**
 * 用户扩展信息 处理器。
 * AccountExpandInfo
 * @author  wangzx 
 * @creation 2015-10-21
 */


@Controller
@RequestMapping(value = "/accountExpandInfo")
public class AccountExpandInfoController {
    
    public static final AjaxResponse NAME_EXIST= new AjaxResponse(-100, "用户扩展信息已存在");
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private AccountExpandInfoService accountExpandInfoService;
	
	private AccountExpandInfo accountExpandInfo;
	
	 /**
     * 后台用户业务逻辑
     */
    @Resource
    private AccountService accountService;
	
	/**
     * 跳转  用户扩展信息列表页
     * 
     * @return 模板位置
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "pi", defaultValue = "0") int pageIndex,
    		@RequestParam(value = "jsonParam", defaultValue = "{}") String jsonParam,
    	AccountExpandInfo p, ModelMap model)throws Exception {
		/*Subject subject = SecurityUtils.getSubject();
		if (subject.isPermitted("zpJlInfo:list")) {
			
		}*/
    	if(UserSessionUtils.getAccountType()==AccountType.ADMIN.getCode()|| UserSessionUtils.getAccountType()==AccountType.HEAD_HUNTING_MANAGER.getCode() || UserSessionUtils.getAccountType()==AccountType.SALARY_MANAGER.getCode() ){
    	} else {
    		return URLConst.ERROR_URL;
    	}
    	HashMap<String,Object> paramMap=this.getParaMap(jsonParam, model);
    	paramMap.put("qtype", "like");
        Pager<AccountExpandInfo> pager = accountExpandInfoService.queryAccountExpandInfoListPage(paramMap , pageIndex);
        model.put("pager", pager);
        model.put("jsonParam", jsonParam);
        return "/accountExpandInfo/accountExpandInfo_list";
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
    		}else{
    			newParamMap.put(String.valueOf(key), val);
    		}
    		model.put(String.valueOf(key), val);
		}
		return newParamMap;
    }

    /**
     * 跳转到添加页面
     * 
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAddAccountExpandInfo(ModelMap model) {
        
        return "/accountexpandInfo/accountexpandInfo_add";
    }

    /**
     * 添加  用户扩展信息
     * 
     * @param p 对象参数
     * @return ajax响应
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addAccountExpandInfo(AccountExpandInfo p,HttpSession session,HttpServletRequest request) {
    	/*try {
            Account account = (Account) session.getAttribute(Const.SESSION_USER_KEY);
    		//验证唯一性
        	HashMap<String,Object> pMap =new HashMap<String,Object>();
        	pMap.put("name", p.getName());
        	int result=accountExpandInfoService.getAccountExpandInfoListCount(pMap);
        	if(result>0){
		        return NAME_EXIST;
			}
        	//p.setInDatetime(new Date());
    		p.setInPerson(account.getAccountId());
    		accountExpandInfoService.addAccountExpandInfo(p);
    		// 添加成功
    		return AjaxResponse.OK;
    	}
        catch (Exception e) {
			e.printStackTrace();
			 //失败
	        return AjaxResponse.FAILED;
		}*/
       return null;
    }

    /**
     * 跳转到编辑页面
     * 
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public String toEditAccountExpandInfo(@RequestParam(value = "id", required = true) java.lang.Long id, ModelMap model)throws Exception {
        //model.put("p", accountExpandInfoService.getAccountExpandInfoById(id));
    	model.put("pid",  id);
        return "/accountExpandInfo/accountExpandInfo_add";
    }

    /**
     * 更新 用户扩展信息
     * 
     * @param p 对象参数
     * @return ajax响应
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object editAccountExpandInfo(AccountExpandInfo p,HttpSession session,HttpServletRequest request) {
    	try {
    		// 获得账号
    		//Account account = (Account) session.getAttribute(Const.SESSION_USER_KEY);
    		//如果前端，没有改变编号，就不用验证
        	//String onlyName=request.getParameter("onlyName");
        	/*if(!StringUtils.isBlank(onlyName) &&  !p.getName().equals(onlyName)){
	    	//验证唯一性
	    	HashMap<String,Object> pMap =new HashMap<String,Object>();
	    	pMap.put("name", p.getName());
	    	int result=accountExpandInfoService.getAccountExpandInfoListCount(pMap);
	    	if(result>0){
		        return NAME_EXIST;
			}
    	}*/
    	//如果是自己，要验证是否是自己修改信息
    	if(UserSessionUtils.getAccountType()!=AccountType.ADMIN.getCode()){
    		HashMap<String,Object> pMap =new HashMap<String,Object>();
    		pMap.put("expandId", p.getExpandId());
    		pMap.put("accountId",UserSessionUtils.getAccount().getAccountId());
    		int count =this.accountExpandInfoService.getAccountExpandInfoListCount(pMap);
    		if(count==1){
    			  accountExpandInfoService.updateAccountExpandInfo(p);
    		      Account newAccount = this.accountService.getAccountById(UserSessionUtils.getAccount().getAccountId());
    		      UserSessionUtils.resetAccount(session, newAccount);
    		}
    	}else {
    		  accountExpandInfoService.updateAccountExpandInfo(p);
		      Account newAccount = this.accountService.getAccountById(UserSessionUtils.getAccount().getAccountId());
		      UserSessionUtils.resetAccount(session, newAccount);
    	}
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
    public String toViewAccountExpandInfo(@RequestParam(value = "id", required = true) java.lang.Long id, ModelMap model)throws Exception {
        //model.put("p", accountExpandInfoService.getAccountExpandInfoById(id));
    	 model.put("pid",  id);
    	return "/accountexpandInfo/accountexpandInfo_add";
    }

    /**
     * 删除  用户扩展信息
     * 
     * @param id AccountExpandInfoID
     */
    @RequestMapping(value = "/delete")
    public String deleteAccountExpandInfo(@RequestParam(value = "id", required = true) java.lang.Long id,HttpServletRequest request)throws Exception {
    	accountExpandInfoService.deleteAccountExpandInfo(id);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/accountExpandInfo/"), s.length());
        return "redirect:"+redirectStr;
    }
    
    /**
     * ajax删除  用户扩展信息
     * 
     * @param id AccountExpandInfoID
     */
    @RequestMapping(value = "/ajaxDelete")
    @ResponseBody
    public Object ajaxDeleteAccountExpandInfo(@RequestParam(value = "id", required = true) java.lang.Long id) {
    	try {
    		AccountExpandInfo p = new AccountExpandInfo();
        	p.setExpandId(id);
			accountExpandInfoService.deleteAccountExpandInfo(p);
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
    	HashMap<String,Object> pmap = new HashMap<String,Object>();
    	pmap.put("expandId", id);
    	if(UserSessionUtils.getAccountType()==AccountType.ADMIN.getCode()|| UserSessionUtils.getAccountType()==AccountType.HEAD_HUNTING_MANAGER.getCode() || UserSessionUtils.getAccountType()==AccountType.SALARY_MANAGER.getCode() ){
    		
    	} else {
    		pmap.put("accountId", UserSessionUtils.getAccount().getAccountId());
    	}
    	List<AccountExpandInfo> list = accountExpandInfoService.queryAccountExpandInfoList(pmap);
    	if(list!=null && list.size()>0 ){
    		return AjaxResponse.OK(list.get(0));
    	}else {
    		return AjaxResponse.NOEXITS;
    	}
    	
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
    	AccountExpandInfo p = new AccountExpandInfo();
    	p.setExpandId(id);
    	//p.setIsDelete(isDelete);
    	accountExpandInfoService.updateAccountExpandInfo(p);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/accountExpandInfo/"), s.length());
        return "redirect:"+redirectStr;
    }
    

}
