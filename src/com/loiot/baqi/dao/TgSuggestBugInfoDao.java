package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.loiot.baqi.pojo.TgSuggestBugInfo;

/**
 * 建议 访问层。
 * 
 * @author  wangzx 
 * @creation 2015-12-30
 */
@Repository("tgSuggestBugInfoDao")
public class TgSuggestBugInfoDao extends SqlSessionDaoSupport{
    
    /**
     * 添加 建议
     * 
     * @param p 参数对象
     */
    public TgSuggestBugInfo addTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
        this.getSqlSession().insert("TgSuggestBugInfo.addTgSuggestBugInfo", p);
        return p;
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
        this.getSqlSession().update("TgSuggestBugInfo.updateTgSuggestBugInfo", p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgSuggestBugInfo(HashMap<String,Object> pMap)throws Exception {
        this.getSqlSession().update("TgSuggestBugInfo.updateTgSuggestBugInfoByMap", pMap);
    }
    
    
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgSuggestBugInfo(java.lang.Long id)throws Exception {
        getSqlSession().delete("TgSuggestBugInfo.deleteTgSuggestBugInfo", id);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
        getSqlSession().delete("TgSuggestBugInfo.deleteTgSuggestBugInfo", p);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoById(java.lang.Long id)throws Exception {
    	HashMap<String, Object> pMap = new HashMap<String, Object>();
    	pMap.put("suggestId", id);
    	return (TgSuggestBugInfo) getSqlSession().selectOne("TgSuggestBugInfo.getTgSuggestBugInfoById", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoById(java.lang.Long id,Long accountId)throws Exception {
    	HashMap<String, Object> pMap = new HashMap<String, Object>();
    	pMap.put("suggestId", id);
    	pMap.put("inPerson", accountId);
    	return (TgSuggestBugInfo) getSqlSession().selectOne("TgSuggestBugInfo.getTgSuggestBugInfoById", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     */
    public TgSuggestBugInfo getTgSuggestBugInfo(HashMap<String,Object> pMap)throws Exception {
    	return (TgSuggestBugInfo) getSqlSession().selectOne("TgSuggestBugInfo.queryTgSuggestBugInfoList", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param name 建议名称
     * 
     * @return 返回与NAME匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoByName(String name)throws Exception {
        return null;
        //return (TgSuggestBugInfo) getSqlSession().selectOne("TgSuggestBugInfo.getTgSuggestBugInfoByName", name);
    }
    
    
    
    /**
     * 查询  建议列表条数
     * 
     * @param name 建议名称
     * @return 建议列表条数
     */
    
    public int getTgSuggestBugInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return (Integer) getSqlSession().selectOne("TgSuggestBugInfo.getTgSuggestBugInfoListCount", pMap);
    }

    /**
     * 查询 建议列表
     * 
     * @param name 建议名称
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 建议列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList(HashMap<String, Object> pMap, int skipResults, int maxResults)throws Exception {
    	pMap.put("skipResults", skipResults);
    	pMap.put("maxResults", maxResults);
        return getSqlSession().selectList("TgSuggestBugInfo.queryTgSuggestBugInfoList", pMap);
    }
    
    /**
     * 查询 建议列表
     * 
     * @param pMap 参数列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList(HashMap<String, Object> pMap)throws Exception {
        return getSqlSession().selectList("TgSuggestBugInfo.queryTgSuggestBugInfoList", pMap);
    }
    
    /**
     * 查询 建议列表
     * 
     * @return 建议列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList()throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return getSqlSession().selectList("TgSuggestBugInfo.queryTgSuggestBugInfoList",params);
    }
    
    /**
     * 查询 建议列表
     * 
     * @return 建议列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList(TgSuggestBugInfo p )throws Exception {
        return getSqlSession().selectList("TgSuggestBugInfo.queryTgSuggestBugInfoList",p);
    }
    
    /**
     * 统计 建议列表
     * 
     * @return 建议列表
     */
    public List<HashMap<String,Object>> statisticsTgSuggestBugInfoInfo(HashMap<String, Object> pMap )throws Exception {
    	List<HashMap<String,Object>> p = null; 
    	p =(List)getSqlSession().selectList("TgSuggestBugInfo.statisticsTgSuggestBugInfoInfo",p);
    	return p;
    }
    
    
    
    

}
