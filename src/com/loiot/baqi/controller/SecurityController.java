package com.loiot.baqi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.constant.URLConst;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.pojo.AccountExpandInfo;
import com.loiot.baqi.security.shiro.AccountNotExistException;
import com.loiot.baqi.security.shiro.PasswordWrongException;
import com.loiot.baqi.service.AccountExpandInfoService;
import com.loiot.baqi.service.AccountService;
import com.loiot.baqi.service.ZpDictionaryInfoService;
import com.loiot.baqi.utils.IndexInfoSingleTon;
import com.loiot.baqi.utils.MD5Util;
import com.timeloit.pojo.Account;

/**
 * 安全处理器。
 * 
 * @author zhengrj
 * 
 */
@Controller
public class SecurityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public static final AjaxResponse ACCOUNT_NOT_EXIST = new AjaxResponse(-100101, "您输入的账号不存在");
    public static final AjaxResponse PASSWORD_NOT_PASSED = new AjaxResponse(-100102, "您输入的账号密码不正确");
    public static final AjaxResponse ACCOUNT_DISABLED = new AjaxResponse(-100103, "您的账号已被暂停");

    /**
     * 账户业务逻辑
     */
    @Resource
    private AccountService accountService;
    
    @Resource
	private AccountExpandInfoService accountExpandInfoService;
    
    @Resource
   	private ZpDictionaryInfoService zpDictionaryInfoService;

    /**
     * 跳转登陆页
     * 
     * @return 登陆模板位置
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login";
    }

    /**
     * 登陆处理。
     * 
     * @param username 账号名
     * @param password 账号密码
     * @return ajax响应
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value = "loginUsername") String username,
            @RequestParam(value = "loginPassword") String password, HttpSession session) throws Exception {

        log.debug("loging username [{}] password [{}]", username, password);

        // 封装令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.encodePassword(password));
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
            // 用令牌登陆,如果登陆失败，则抛出异常
            subject.login(token);
            token.clear();
        } catch (AccountNotExistException ex) {
            return ACCOUNT_NOT_EXIST;
        } catch (PasswordWrongException ex) {
            return PASSWORD_NOT_PASSED;
        } catch (DisabledAccountException ex){
        	return ACCOUNT_DISABLED;
        }catch (Exception ex) {
            log.error("login error happend.", ex);
            return AjaxResponse.SYSTEM_BUSY;
        }
        
        if(session.getAttribute(Const.SESSION_USER_KEY)!=null){
        	session.removeAttribute(Const.SESSION_USER_KEY);
        }
        if(session.getAttribute(Const.SESSION_SUBJECT)!=null){
        	session.removeAttribute(Const.SESSION_SUBJECT);
        }
        
        Account account = accountService.getAccountByUsername(username);
        
        AccountExpandInfo exp = new AccountExpandInfo();
        exp.setExpandId(account.getExpandId());
        exp.setLastLoginTime(new java.util.Date());
        accountExpandInfoService.updateAccountExpandInfo(exp);
        
        // 保存会话
        session.setAttribute(Const.SESSION_SUBJECT, subject); // shiro已登录用户
        session.setAttribute(Const.SESSION_USER_KEY,account );// 登陆用户
        
        //放到shiro容器中
        
        if(subject.getSession().getAttribute(Const.SHIRO_SESSION_USER_KEY)!=null){
        	subject.getSession().removeAttribute(Const.SHIRO_SESSION_USER_KEY);
        }
        subject.getSession().setAttribute(Const.SHIRO_SESSION_USER_KEY, account);
        IndexInfoSingleTon indexInfo = IndexInfoSingleTon.getInstance();
		Map<String, List> infoMap = indexInfo.getIndexInfoMap();  //  得到Map集合
		infoMap.put(Const.SESSION_DICTIONARYS_KEY, zpDictionaryInfoService.queryZpDictionaryInfoList(new HashMap()));
        return AjaxResponse.OK;
    }

    /**
     * 跳转到修改密码页面。
     * 
     * @return 模板文件位置。
     */
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String toPassword() {
        return "/password";
    }

    /**
     * 变更密码。
     * 
     */
    @RequestMapping(value = "/account/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(@RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword, HttpSession session) {

        // 获得账号
        Account account = (Account) session.getAttribute(Const.SESSION_USER_KEY);

        // 密码变更
        try {
            accountService.changePassword(account.getUsername(), oldPassword, newPassword);
        } catch (PasswordWrongException ex) {
            // 旧密码错误
            log.debug("old password wrong", ex);
            return PASSWORD_NOT_PASSED;
        }

        return AjaxResponse.OK;
    }

    /**
     * 退出系统
     * 
     * @param request http请求
     * @return 登陆页的url
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        // 登出
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        // 会话失效
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 返回到登陆页
        return URLConst.REDIRECT_LOGIN_URL;
    }
}
