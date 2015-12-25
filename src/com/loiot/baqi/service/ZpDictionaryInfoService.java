package com.loiot.baqi.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.ZpDictionaryInfoDao;
import com.loiot.baqi.service.ZpDictionaryInfoService;
import com.loiot.baqi.pojo.ZpDictionaryInfo;

/**
 * 字典信息 逻辑类。
 * 
 * @author  wangzx 
 * @creation 2015-09-19
 */
@Service("zpDictionaryInfoService")
@Transactional
public class ZpDictionaryInfoService{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private ZpDictionaryInfoDao zpDictionaryInfoDao;
	
	
	 /**
     * 查询 字典信息列表分页
     * 
     * @param name 字典信息名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<ZpDictionaryInfo> queryZpDictionaryInfoListPage(HashMap<String,Object> pMap, int pageIndex)throws Exception {

        // 查询字典信息列表总条数
        int totalResults = zpDictionaryInfoDao.getZpDictionaryInfoListCount(pMap);

        // 构造一个分页器
        Pager<ZpDictionaryInfo> pager = new Pager<ZpDictionaryInfo>(totalResults, pageIndex);

        // 查询字典信息列表
        List<ZpDictionaryInfo> zpDictionaryInfoList = zpDictionaryInfoDao.queryZpDictionaryInfoList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(zpDictionaryInfoList);
        return pager;
    }
	
	 /**
     * 添加 字典信息
     * 
     * @param p 参数对象
     */
    public void addZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        zpDictionaryInfoDao.addZpDictionaryInfo(p);
    }
    
    /**
     * 修改 字典信息
     * 
     * @param p 参数对象
     */
    public void updateZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        zpDictionaryInfoDao.updateZpDictionaryInfo(p);
    }
    
    /**
     * 删除  字典信息
     * 
     * @param id 主键
     */
    public void deleteZpDictionaryInfo(java.lang.Long id)throws Exception {
        zpDictionaryInfoDao.deleteZpDictionaryInfo(id);
    }
    
    /**
     * 删除  字典信息
     * 
     * @param id 主键
     */
    public void deleteZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        zpDictionaryInfoDao.deleteZpDictionaryInfo(p);
    }
    
    /**
     * 获得  字典信息
     * 
     * @param id 字典信息Id
     * 
     * @return 返回与ID匹配的字典信息
     */
    public ZpDictionaryInfo getZpDictionaryInfoById(java.lang.Long id)throws Exception {
        return  zpDictionaryInfoDao.getZpDictionaryInfoById(id);
    }
    
    /**
     * 获得  字典信息
     * 
     * @param name 字典信息名称
     * 
     * @return 返回与NAME匹配的字典信息
     */
    public ZpDictionaryInfo getZpDictionaryInfoByName(String name)throws Exception {
        return  zpDictionaryInfoDao.getZpDictionaryInfoByName(name);
    }
    
    /**
     * 查询 字典信息列表
     * @return 字典信息列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList(HashMap<String,Object> pMap)throws Exception {
        return  zpDictionaryInfoDao.queryZpDictionaryInfoList(pMap);
    }
    
    /**
     * 查询 字典信息列表
     * @return 字典信息列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList(ZpDictionaryInfo p)throws Exception {
        return  zpDictionaryInfoDao.queryZpDictionaryInfoList(p);
    }
    
    /**
     * 查询  字典信息列表条数
     * 
     * @param name 字典信息名称
     * @return 字典信息列表条数
     */
    
    public int getZpDictionaryInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return  zpDictionaryInfoDao.getZpDictionaryInfoListCount(pMap);
    }
	
}
