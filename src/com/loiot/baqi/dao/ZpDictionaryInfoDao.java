package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.loiot.baqi.pojo.ZpDictionaryInfo;

/**
 * 字典信息 访问层。
 * 
 * @author  wangzx 
 * @creation 2015-09-19
 */
@Repository("zpDictionaryInfoDao")
public class ZpDictionaryInfoDao extends SqlSessionDaoSupport{
    
    /**
     * 添加 字典信息
     * 
     * @param p 参数对象
     */
    public void addZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        this.getSqlSession().insert("ZpDictionaryInfo.addZpDictionaryInfo", p);
    }
    
    /**
     * 修改 字典信息
     * 
     * @param p 参数对象
     */
    public void updateZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        this.getSqlSession().update("ZpDictionaryInfo.updateZpDictionaryInfo", p);
    }
    
    /**
     * 删除  字典信息
     * 
     * @param id 主键
     */
    public void deleteZpDictionaryInfo(java.lang.Long id)throws Exception {
        getSqlSession().delete("ZpDictionaryInfo.deleteZpDictionaryInfo", id);
    }
    
    /**
     * 删除  字典信息
     * 
     * @param id 主键
     */
    public void deleteZpDictionaryInfo(ZpDictionaryInfo p)throws Exception {
        getSqlSession().delete("ZpDictionaryInfo.deleteZpDictionaryInfo", p);
    }
    
    /**
     * 获得  字典信息
     * 
     * @param id 字典信息Id
     * 
     * @return 返回与ID匹配的字典信息
     */
    public ZpDictionaryInfo getZpDictionaryInfoById(java.lang.Long id)throws Exception {
        return (ZpDictionaryInfo) getSqlSession().selectOne("ZpDictionaryInfo.getZpDictionaryInfoById", id);
    }
    
    /**
     * 获得  字典信息
     * 
     * @param name 字典信息名称
     * 
     * @return 返回与NAME匹配的字典信息
     */
    public ZpDictionaryInfo getZpDictionaryInfoByName(String name)throws Exception {
        return null;
        //return (ZpDictionaryInfo) getSqlSession().selectOne("ZpDictionaryInfo.getZpDictionaryInfoByName", name);
    }
    
    
    
    /**
     * 查询  字典信息列表条数
     * 
     * @param name 字典信息名称
     * @return 字典信息列表条数
     */
    
    public int getZpDictionaryInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return (Integer) getSqlSession().selectOne("ZpDictionaryInfo.getZpDictionaryInfoListCount", pMap);
    }

    /**
     * 查询 字典信息列表
     * 
     * @param name 字典信息名称
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 字典信息列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList(HashMap<String, Object> pMap, int skipResults, int maxResults)throws Exception {
    	pMap.put("skipResults", skipResults);
    	pMap.put("maxResults", maxResults);
        return getSqlSession().selectList("ZpDictionaryInfo.queryZpDictionaryInfoList", pMap);
    }
    
    /**
     * 查询 字典信息列表
     * 
     * @param pMap 参数列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList(HashMap<String, Object> pMap)throws Exception {
        return getSqlSession().selectList("ZpDictionaryInfo.queryZpDictionaryInfoList", pMap);
    }
    
    /**
     * 查询 字典信息列表
     * 
     * @return 字典信息列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList()throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return getSqlSession().selectList("ZpDictionaryInfo.queryZpDictionaryInfoList",params);
    }
    
    /**
     * 查询 字典信息列表
     * 
     * @return 字典信息列表
     */
    public List<ZpDictionaryInfo> queryZpDictionaryInfoList(ZpDictionaryInfo p )throws Exception {
        return getSqlSession().selectList("ZpDictionaryInfo.queryZpDictionaryInfoList",p);
    }
    
    

}
