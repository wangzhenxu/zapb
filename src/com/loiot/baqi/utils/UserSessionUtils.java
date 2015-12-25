package com.loiot.baqi.utils;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.loiot.baqi.constant.Const;
import com.timeloit.pojo.Account;

public class UserSessionUtils {
	public static Account getAccount(){
		Subject subject = SecurityUtils.getSubject();
    	Account account =(Account)subject.getSession().getAttribute(Const.SHIRO_SESSION_USER_KEY);
    	return account;
	} 
	
	public static Integer getAccountType(){
		Subject subject = SecurityUtils.getSubject();
    	Account account =(Account)subject.getSession().getAttribute(Const.SHIRO_SESSION_USER_KEY);
    	return account.getType();
	}
	
	public static void resetAccount(HttpSession session,Account newAccount){
		//shiro session  update
		Subject subject = SecurityUtils.getSubject();
		Session shiroSession = subject.getSession();
		if(shiroSession.getAttribute(Const.SHIRO_SESSION_USER_KEY)!=null){
			shiroSession.removeAttribute(Const.SHIRO_SESSION_USER_KEY);
		}
		shiroSession.setAttribute(Const.SHIRO_SESSION_USER_KEY,newAccount);
		
		//update httpSession
		if(session.getAttribute(Const.SESSION_USER_KEY)!=null){
			session.removeAttribute(Const.SESSION_USER_KEY);
		}
		session.setAttribute(Const.SESSION_USER_KEY, newAccount);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
