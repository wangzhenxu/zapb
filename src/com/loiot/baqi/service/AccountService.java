package com.loiot.baqi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.constant.DictionaryUtil;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.AccountDao;
import com.loiot.baqi.dao.AccountExpandInfoDao;
import com.loiot.baqi.pojo.AccountExpandInfo;
import com.loiot.baqi.security.shiro.PasswordWrongException;
import com.loiot.baqi.security.shiro.UsernameExistException;
import com.loiot.baqi.status.AccountType;
import com.loiot.baqi.status.DictionaryType;
import com.loiot.baqi.utils.MD5Util;
import com.timeloit.pojo.Account;

/**
 * 账号业务逻辑类
 * 
 * @author zhengrj
 * 
 */
@Service("accountService")
public class AccountService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 账号数据访问接口
     */
    @Resource
    private AccountDao accountDao;
    
    @Resource
	private AccountExpandInfoDao accountExpandInfoDao;

    /**
     * 获得账户
     * 
     * @param username 用户名
     * @return 返回账户
     */
    public Account getAccountByUsername(String username) {
        return accountDao.getAccountByUsername(username);
    }

    /**
     * 密码变更
     * 
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * 
     * @throws PasswordWrongException 当旧密码不正确时，返回异常。
     */
    public void changePassword(String username, String oldPassword, String newPassword) throws PasswordWrongException {

        // 加密用户输入旧密码
        log.debug("old password [{}]", oldPassword);
        oldPassword = MD5Util.encode(oldPassword + Const.USER_PASSWORD_KEY);
        log.debug("old password MD5 [{}]", oldPassword);

        // 原密码
        String origPassword = accountDao.getPasswordByUsername(username);
        log.debug("orginal password MD5 [{}]", origPassword);

        // 判定旧密码是否一直
        if (StringUtils.equals(oldPassword, origPassword)) {
            updatePassword(username, newPassword);
        } else {
            throw new PasswordWrongException();
        }
    }

    /**
     * 更新密码
     * 
     * @param username 用户名
     * @param password 原密码
     */
    private void updatePassword(String username, String password) {

        // 加密用户输入新密码
        log.debug("new password [{}]", password);
        password = MD5Util.encodePassword(password);
        log.debug("new password MD5 [{}]", password);

        // 更新
        accountDao.updatePassword(username, password);
    }

    /**
     * 添加后台账号
     * 
     * @param account 后台账号
     * @throws Exception 
     */
    public void addAccount(Account account) throws Exception {

        // 打印account信息
        log.debug("account [{}]", account);

        // 检查用户名是否存在
        if (accountDao.isExistUsername(account.getUsername())) {
            throw new UsernameExistException();
        }

        // 密码加密
        account.setPassword(MD5Util.encodePassword(account.getPassword()));

        // 添加后台账号
        accountDao.addAccount(account);
        
        //扩展信息
        AccountExpandInfo expandInfo = new AccountExpandInfo();
        expandInfo.setAccountId(account.getAccountId());
        expandInfo.setInPerson(account.getAccountId());
        expandInfo.setInTime(account.getInTime());
        expandInfo.setAuditPositionId(account.getAuditPositionId());
        
        //如果是选择技术评审类型，需要添加，技术评审开启状态
        if(account.getType()==AccountType.TECHICAL_AUDIT.getCode()){
            expandInfo.setIsAcceptAudit(DictionaryUtil.getCode(DictionaryType.ACCEPT_AUDIT.getCode(), "stop"));
        }
        accountExpandInfoDao.addAccountExpandInfo(expandInfo);
        
        // 添加角色
        accountDao.addAccountRole(account.getAccountId(), account.getRole().getRoleId());
    }

    /**
     * 修改后台账号
     * 
     * @param account 后台账号
     * @throws Exception 
     */
    public void updateAccount(Account account) throws Exception {

        // 打印account信息
        log.debug("account [{}]", account);

        // 如果密码不为空，加密密码
        if (account.getPassword() != null) {
            // 获得数据库账户
            Account dbAccount = accountDao.getAccountById(account.getAccountId());
            // 更新密码
            updatePassword(dbAccount.getUsername(), account.getPassword());
        }

        // 当用户角色存在时，更新
        if (account.getRole() != null && account.getRole().getRoleId() != null) {
            // 删除角色
            accountDao.deleteAccountRole(account.getAccountId());
            // 新增角色
            accountDao.addAccountRole(account.getAccountId(), account.getRole().getRoleId());
        }
        
        //扩展信息
        AccountExpandInfo expandInfo = new AccountExpandInfo();
        expandInfo.setAccountId(account.getAccountId());
        if(account.getType()!=AccountType.TECHICAL_AUDIT.getCode()){
        	expandInfo.setAuditPositionId(null);
        }else{
        	expandInfo.setAuditPositionId(account.getAuditPositionId());
        }
        accountExpandInfoDao.updatePostionInfo(expandInfo);
        
        this.accountDao.updateAccountType(account);
    }
    
    public void updateDeleteStatus(Long accountId,Integer isDelete){
    	this.accountDao.updateDeleteStatus(accountId, isDelete);
   }

    /**
     * 查询后台账号列表分页
     * 
     * @param accountName 后台账号名
     * @param pageIndex 页索引
     * @return
     */
    public Pager<Account> getAccountListPage(String accountName, int pageIndex) {

        // 查询后台账号列表总条数
        int totalResults = accountDao.getAccountListCount(accountName);

        // 构造一个分页器
        Pager<Account> pager = new Pager<Account>(totalResults, pageIndex);

        // 查询后台账号列表
        List<Account> accountList = accountDao.getAccountList(accountName, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(accountList);

        return pager;
    }
    
    /**
     * 查询后台账号列表分页
     * 
     * @param accountName 后台账号名
     * @param pageIndex 页索引
     * @return
     */
    public Pager<Account> getAccountListPage(HashMap<String,Object> pMap, int pageIndex) {

    	 // 查询后台账号列表总条数
        int totalResults = accountDao.queryAccountListCount(pMap);

        // 构造一个分页器
        Pager<Account> pager = new Pager<Account>(totalResults, pageIndex);

        // 查询后台账号列表
        List<Account> accountList = accountDao.queryAccountList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(accountList);
        return pager;
    }

    /**
     * 获得后台账号
     * 
     * @param accountId 后台账号ID
     * @return 返回与ID匹配的后台账号
     */
    public Account getAccountById(Long accountId) {
        return accountDao.getAccountById(accountId);
    }

    /**
     * 删除后台账号
     * 
     * @param accountId
     */
    public void deleteAccount(Long accountId) {

        // 获得账号
        Account account = accountDao.getAccountById(accountId);

        if (account == null) {
            return;
        }

        // 删除账号角色关系表
        accountDao.deleteAccountRole(account.getAccountId());

        // 删除账号
        accountDao.deleteAccount(accountId);
    }
    
    public List<Account> queryAccountList(HashMap<String,Object> pMap){
    	return accountDao.queryAccountList(pMap);
    }

}
