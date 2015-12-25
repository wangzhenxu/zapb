package com.loiot.baqi.controller;

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

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.pojo.ZpCompanyInfo;
import com.loiot.baqi.security.shiro.UsernameExistException;
import com.loiot.baqi.service.AccountService;
import com.loiot.baqi.service.RoleService;
import com.loiot.baqi.status.AccountType;
import com.loiot.baqi.status.PauseStartType;
import com.loiot.baqi.utils.UserSessionUtils;
import com.loiot.commons.utils.JsonUtil;
import com.timeloit.pojo.Account;

/**
 * 后台用户处理器。
 * 
 * @author zhengrj
 * 
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    public static final AjaxResponse ACCOUNT_USERNAME_EXIST = new AjaxResponse(-100301, "用户名已存在");

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    

    /**
     * 后台用户业务逻辑
     */
    @Resource
    private AccountService accountService;
    /**
     * 角色业务逻辑
     */
    @Resource
    private RoleService roleService;

    /**
     * 跳转后台用户列表页
     * 
     * @return 模板位置
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "pi", defaultValue = "0") int pageIndex,
            @RequestParam(value = "jsonParam", defaultValue = "{}") String jsonParam,
            Account p, ModelMap model
    		) {
    	HashMap<String,Object> paramMap=this.getParaMap(jsonParam, model);
    	paramMap.put("qtype", "like");
    	Pager<Account> pager = accountService.getAccountListPage(paramMap, pageIndex);
    	model.put("pager", pager);
        model.put("jsonParam", jsonParam);
        
        model.put("menuClass", "accountManage");
        return "/account/account_list";
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
    @RequestMapping(value = "/toAddAccount")
    public String toAddAccount(ModelMap model) {
        model.put("roleList", roleService.getRoleListAll());
        return "/account/account_add";
    }

    /**
     * 添加账户
     * 
     * @param account 账户
     * @return ajax响应
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Account account,HttpSession session) {
         
        // 数据不合格，返回系统繁忙
        if (StringUtils.isBlank(account.getUsername()) || account.getRole() == null
                || account.getRole().getRoleId() == null) {
            return AjaxResponse.SYSTEM_BUSY;
        }
        try {
            Account accoun = (Account) session.getAttribute(Const.SESSION_USER_KEY);
            account.setInPerson(accoun.getAccountId());
            account.setInTime(new Date());
            account.setIsDelete((int)PauseStartType.START.getCode());
            accountService.addAccount(account);
        } catch (UsernameExistException ex) {
            // 用户名已经存在
            log.debug("username is exist");
            return ACCOUNT_USERNAME_EXIST;
        }  catch (Exception e){
        	e.printStackTrace();
        	return AjaxResponse.FAILED;
        }

        // 添加成功
        return AjaxResponse.OK;
    }

    /**
     * 跳转到编辑页面
     * 
     * @return
     */
    @RequestMapping(value = "/toEditAccount")
    public String toEditAccount(@RequestParam(value = "accountId", required = true) Long accountId, ModelMap model) {
        model.put("account", accountService.getAccountById(accountId));
        model.put("roleList", roleService.getRoleListAll());
        return "/account/account_edit";
    }

    /**
     * 更新账户
     * 
     * @param account 账户
     * @return ajax响应
     * @throws Exception 
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(Account account,HttpSession session) throws Exception {
        accountService.updateAccount(account);
        Account newAccount = this.accountService.getAccountById(UserSessionUtils.getAccount().getAccountId());
    	UserSessionUtils.resetAccount(session, newAccount);
        return AjaxResponse.OK;
    }

    /**
     * 跳转到查看页面
     * 
     * @return
     */
    @RequestMapping(value = "/toViewAccount")
    public String toViewAccount(@RequestParam(value = "accountId", required = true) Long accountId, ModelMap model) {
        model.put("account", accountService.getAccountById(accountId));
        return "/account/account_view";
    }

    /**
     * 删除后台账号
     * 
     * @param accountId 后台账号ID
     */
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "accountId", required = true) Long accountId) {
        accountService.deleteAccount(accountId);
        return "redirect:/account/list.action";
    }
    
    /**
     * 更新 （停用、启用状态）
     * 
     * @param id ZpCompanyInfoID
     */
    @RequestMapping(value = "/modifyDeleteStatus")
    public String modifyDeleteStatus(@RequestParam(value = "id", required = true) java.lang.Long id,
    		@RequestParam(value = "deleteStatus", required = true) java.lang.Integer isDelete,
    		HttpServletRequest request)throws Exception {
    	this.accountService.updateDeleteStatus(id, isDelete);
    	  String s = request.getHeader("Referer");
          String redirectStr = s.substring(s.indexOf("/account/"), s.length());
          return "redirect:"+redirectStr;
      
    }
    
}
