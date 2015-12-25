package com.loiot.baqi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.AccountExpandInfoDao;
import com.loiot.baqi.service.AccountExpandInfoService;
import com.loiot.baqi.pojo.AccountExpandInfo;


/**
 * 用户扩展信息 逻辑类。
 * 
 * @author  wangzx 
 * @creation 2015-10-21
 */
@Service("accountExpandInfoService")
@Transactional
public class AccountExpandInfoService{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private AccountExpandInfoDao accountExpandInfoDao;
	
	
	 /**
     * 查询 用户扩展信息列表分页
     * 
     * @param name 用户扩展信息名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<AccountExpandInfo> queryAccountExpandInfoListPage(HashMap<String,Object> pMap, int pageIndex)throws Exception {

        // 查询用户扩展信息列表总条数
        int totalResults = accountExpandInfoDao.getAccountExpandInfoListCount(pMap);

        // 构造一个分页器
        Pager<AccountExpandInfo> pager = new Pager<AccountExpandInfo>(totalResults, pageIndex);

        // 查询用户扩展信息列表
        List<AccountExpandInfo> accountExpandInfoList = accountExpandInfoDao.queryAccountExpandInfoList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(accountExpandInfoList);
        return pager;
    }
	
	 /**
     * 添加 用户扩展信息
     * 
     * @param p 参数对象
     */
    public AccountExpandInfo addAccountExpandInfo(AccountExpandInfo p)throws Exception {
       return  accountExpandInfoDao.addAccountExpandInfo(p);
    }
    
    /**
     * 修改 用户扩展信息
     * 
     * @param p 参数对象
     */
    public void updateAccountExpandInfo(AccountExpandInfo p)throws Exception {
        accountExpandInfoDao.updateAccountExpandInfo(p);
    }
    
    /**
     * 删除  用户扩展信息
     * 
     * @param id 主键
     */
    public void deleteAccountExpandInfo(java.lang.Long id)throws Exception {
        accountExpandInfoDao.deleteAccountExpandInfo(id);
    }
    
    /**
     * 删除  用户扩展信息
     * 
     * @param id 主键
     */
    public void deleteAccountExpandInfo(AccountExpandInfo p)throws Exception {
        accountExpandInfoDao.deleteAccountExpandInfo(p);
    }
    
    /**
     * 获得  用户扩展信息
     * 
     * @param id 用户扩展信息Id
     * 
     * @return 返回与ID匹配的用户扩展信息
     */
    public AccountExpandInfo getAccountExpandInfoById(java.lang.Long id)throws Exception {
        return  accountExpandInfoDao.getAccountExpandInfoById(id);
    }
    
    /**
     * 获得  用户扩展信息
     * 
     * @param name 用户扩展信息名称
     * 
     * @return 返回与NAME匹配的用户扩展信息
     */
    public AccountExpandInfo getAccountExpandInfoByName(String name)throws Exception {
        return  accountExpandInfoDao.getAccountExpandInfoByName(name);
    }
    
    /**
     * 查询 用户扩展信息列表
     * @return 用户扩展信息列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList(HashMap<String,Object> pMap)throws Exception {
        return  accountExpandInfoDao.queryAccountExpandInfoList(pMap);
    }
    
    /**
     * 查询 用户扩展信息列表
     * @return 用户扩展信息列表
     */
    public List<AccountExpandInfo> queryAccountExpandInfoList(AccountExpandInfo p)throws Exception {
        return  accountExpandInfoDao.queryAccountExpandInfoList(p);
    }
    
    /**
     * 查询  用户扩展信息列表条数
     * 
     * @param name 用户扩展信息名称
     * @return 用户扩展信息列表条数
     */
    
    public int getAccountExpandInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return  accountExpandInfoDao.getAccountExpandInfoListCount(pMap);
    }
    
    /**
     * 查询id集合
     * @return
     */
    public List<Long> getIds(List<AccountExpandInfo> list) {
    	List<Long> idsList = null;
        if(list!=null && list.size()>0) {
        	idsList = new ArrayList<Long>();
        	for (AccountExpandInfo b : list) {
            	idsList.add(null);
            }
        }
        return idsList;
    }
}
