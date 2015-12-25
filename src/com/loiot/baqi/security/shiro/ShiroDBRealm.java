/**
 * Specl.com Inc.
 * Copyright (c) 2010-2011 All Rights Reserved.
 */
package com.loiot.baqi.security.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loiot.baqi.dao.AccountDao;
import com.loiot.baqi.status.PauseStartType;
import com.timeloit.pojo.Account;

/**
 * 权限数据访问类。
 * 
 * @author zhengrj
 */
public class ShiroDBRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 账号访问层。
     */
    @Resource
    private AccountDao accountDao;

    /**
     * 认证方法。
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        log.debug("do authentication ，token data [{}]", authcToken);

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        if (token.getUsername() == null) {
            return null;
        }

        // 根据账户名获得账户
        Account account = accountDao.getAccountByUsername(token.getUsername());

        
        // 当账号密码不存在
        if (account == null) {
            log.debug("account not exist.");
            throw new AccountNotExistException();
        }

        // 当密码错误
        if (!StringUtils.equals(account.getPassword(), new String(token.getPassword()))) {
            log.debug("password not equals.");
            throw new PasswordWrongException();
        }
        
        // 当账号停用
        if (account.getIsDelete()==PauseStartType.PAUSE.getCode()) {
            log.debug("account is stop ");
            throw new DisabledAccountException();
        }

        return new SimpleAuthenticationInfo(account.getUsername(), account.getPassword(), getName());
    }

    /**
     * 授权方法。
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.debug("do authortization , principals data [{}]", principals);

        String username = (String) principals.fromRealm(getName()).iterator().next();

        // 根据账户名获得账户
        Account account = accountDao.getAccountByUsername(username);

        if (account == null) {
            log.debug("account not exist.");
            throw new AccountNotExistException();
        }

        // 封装授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 将用户的角色字符，进行授权
//        info.addRoles(account.getRoleStringList());
        // 将用户的权限字符，进行授权
        info.addStringPermissions(account.getPermissionStringList());
        return info;
    }
}
