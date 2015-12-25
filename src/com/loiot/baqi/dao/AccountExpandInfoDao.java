package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.loiot.baqi.pojo.AccountExpandInfo;

/**
 * 用户扩展信息 访问层。
 * 
 * @author  wangzx 
 * @creation 2015-10-21
 */
@Repository("accountExpandInfoDao")
public class AccountExpandInfoDao extends SqlSessionDaoSupport{
    
    /**
     * 添加 用户扩展信息
     * 
     * @param p 参数对象
     */
    public AccountExpandInfo addAccountExpandInfo(AccountExpandInfo p)throws Exception {
        this.getSqlSession().insert("AccountExpandInfo.addAccountExpandInfo", p);
        return p;
    }
    
    /**
     * 修改 用户扩展信息
     * 
     * @param p 参数对象
     */
    public void updateAccountExpandInfo(AccountExpandInfo p)throws Exception {
        this.getSqlSession().update("AccountExpandInfo.updateAccountExpandInfo", p);
    }
    
    /**
     * 修改 用户扩展信息
     * 
     * @param p 参数对象
     */
    public void updatePostionInfo(AccountExpandInfo p)throws Exception {
        this.getSqlSession().update("AccountExpandInfo.updatePostionInfo", p);
    }
    
    /**
     * 删除  用户扩展信息
     * 
     * @param id 主键
     */
    public void deleteAccountExpandInfo(java.lang.Long id)throws Exception {
        getSqlSession().delete("AccountExpandInfo.deleteAccountExpandInfo", id);
    }
    
    /**
     * 删除  用户扩展信息
     * 
     * @param id 主键
     */
    public void deleteAccountExpandInfo(AccountExpandInfo p)throws Exception {
        getSqlSession().delete("AccountExpandInfo.deleteAccountExpandInfo", p);
    }
    
    /**
     * 获得  用户扩展信息
     * 
     * @param id 用户扩展信息Id
     * 
     * @return 返回与ID匹配的用户扩展信息
     */
    public AccountExpandInfo getAccountExpandInfoById(java.lang.Long id)throws Exception {
    	HashMap<String, Object> pMap = new HashMap<String, Object>();
    	pMap.put("expandId", id);
    	return (AccountExpandInfo) getSqlSession().selectOne("AccountExpandInfo.getAccountExpandInfoById", pMap);
    }
    
    /**
     * 获得  用户扩展信息
     * 
     * @param name 用户扩展信息名称
     * 
     * @return 返回与NAME匹配的用户扩展信息
     */
    public AccountExpandInfo getAccountExpandInfoByName(String name)throws Exception {
        return null;
        //return (AccountExpandInfo) getSqlSession().selectOne("AccountExpandInfo.getAccountExpandInfoByName", name);
    }
    
    
    
    /**
     * 查询  用户扩展信息列表条数
     * 
     * @param name 用户扩展信息名称
     * @return 用户扩展信息列表条数
     */
    
    public int getAccountExpandInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return (Integer) getSqlSession().selectOne("AccountExpandInfo.getAccountExpandInfoListCount", pMap);
    }

    /**
     * 查询 用户扩展信息列表
     * 
     * @param name 用户扩展信息名称
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 用户扩展信息列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList(HashMap<String, Object> pMap, int skipResults, int maxResults)throws Exception {
    	pMap.put("skipResults", skipResults);
    	pMap.put("maxResults", maxResults);
        return getSqlSession().selectList("AccountExpandInfo.queryAccountExpandInfoList", pMap);
    }
    
    /**
     * 查询 用户扩展信息列表
     * 
     * @param pMap 参数列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList(HashMap<String, Object> pMap)throws Exception {
        return getSqlSession().selectList("AccountExpandInfo.queryAccountExpandInfoList", pMap);
    }
    
    /**
     * 查询 用户扩展信息列表
     * 
     * @return 用户扩展信息列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList()throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return getSqlSession().selectList("AccountExpandInfo.queryAccountExpandInfoList",params);
    }
    
    /**
     * 查询 用户扩展信息列表
     * 
     * @return 用户扩展信息列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList(AccountExpandInfo p )throws Exception {
        return getSqlSession().selectList("AccountExpandInfo.queryAccountExpandInfoList",p);
    }
    
    

}
