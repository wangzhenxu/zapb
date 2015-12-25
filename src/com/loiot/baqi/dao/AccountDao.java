package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.timeloit.pojo.Account;


/**
 * 账号数据访问层。
 * 
 * @author zhengrj
 */
@Repository("accountDao")
public class AccountDao extends SqlSessionDaoSupport {

    /**
     * 通过账号名查询账号
     * 
     * @param username 账号
     * @return 返回一个查询到的账号
     */
    public Account getAccountByUsername(String username) {
        return (Account) getSqlSession().selectOne("Account.getAccountByUserName", username);
    }

    /**
     * 通过用户名获得密码
     * 
     * @param username 账号
     * @return 返回这个账号的密码
     */
    public String getPasswordByUsername(String username) {
        return (String) getSqlSession().selectOne("Account.getPasswordByUsername", username);
    }

    /**
     * 检测用户名是否存在
     * 
     * @param username 用户名
     * @return true 已存在，false 不存在
     */
    public boolean isExistUsername(String username) {
        return getSqlSession().selectOne("Account.isExistUsername", username) != null;
    }

    /**
     * 更新密码
     * 
     * @param username 用户名
     * @param password 密码
     */
    public void updatePassword(String username, String password) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);

        getSqlSession().update("Account.updatePassword", params);
    }
    
    public void updateDeleteStatus(Long accountId,Integer isDelete){
    	 Map<String, Object> params = new HashMap<String, Object>();
         params.put("accountId", accountId);
         params.put("isDelete", isDelete);
        getSqlSession().update("Account.updateDeleteStatus", params);

    }

    /**
     * 为账户添加角色
     * 
     * @param accountId
     * @param roleId
     */
    public void addAccountRole(Long accountId, Long roleId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accountId", accountId);
        params.put("roleId", roleId);

        getSqlSession().insert("Account.addAccountRole", params);
    }

    /**
     * 删除一个角色
     * 
     * @param accountId 账号ID
     * @param roleId 角色ID
     */
    public void deleteAccountRole(Long accountId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accountId", accountId);

        getSqlSession().insert("Account.deleteAccountRole", params);
    }

    /**
     * 添加后台账号
     * 
     * @param account 后台账号
     * @return 返回新增后台账号的ID
     */
    public Account addAccount(Account account) {
        getSqlSession().insert("Account.addAccount", account);
        return account;
    }
    
    /**
     * 更新type
     * 
     * @param account 后台账号
     * @return 返回新增后台账号的ID
     */
    public void updateAccountType(Account account) {
        getSqlSession().update("Account.updateType", account);
    }
    
    

    /**
     * 删除后台账号
     * 
     * @param accountId 后台账号ID
     */
    public void deleteAccount(Long accountId) {
        getSqlSession().delete("Account.deleteAccount", accountId);
    }

    /**
     * 查询后台账号列表条数
     * 
     * @param username 后台账号名
     * @return 后台账号列表
     */
    public int getAccountListCount(String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(username)) {
            params.put("username", username);
        }
        return (Integer) getSqlSession().selectOne("Account.getAccountListCount", params);
    }

    /**
     * 查询后台账号列表
     * 
     * @param username 后台账号名
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 后台账号列表
     */
    public List<Account> getAccountList(String username, int skipResults, int maxResults) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("skipResults", skipResults);
        params.put("maxResults", maxResults);
        if (!StringUtils.isEmpty(username)) {
            params.put("username", username);
        }
        return getSqlSession().selectList("Account.getAccountList", params);
    }
    
    public int queryAccountListCount(HashMap<String,Object> pMap){
     return (Integer) getSqlSession().selectOne("Account.getAccountListCount", pMap);
   }
    
   public List<Account> queryAccountList(HashMap<String,Object> pMap, int skipResults, int maxResults){
		pMap.put("skipResults", skipResults);
		pMap.put("maxResults", maxResults);
	   return getSqlSession().selectList("Account.getAccountList", pMap);
   }
   
   public List<Account> queryAccountList(HashMap<String,Object> pMap){
    	 return getSqlSession().selectList("Account.getAccountList", pMap);
    }

    /**
     * 获得后台账号
     * 
     * @param accountId 后台账号ID
     * @return 返回与ID匹配的后台账号
     */
    public Account getAccountById(Long accountId) {
        return (Account) getSqlSession().selectOne("Account.getAccountById", accountId);
    }

}
