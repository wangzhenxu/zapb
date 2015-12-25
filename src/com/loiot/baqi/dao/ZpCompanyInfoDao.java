package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.loiot.baqi.pojo.ZpCompanyInfo;

/**
 * 公司信息 访问层。
 * 
 * @author  wangzx 
 * @creation 2015-09-29
 */
@Repository("zpCompanyInfoDao")
public class ZpCompanyInfoDao extends SqlSessionDaoSupport{
    
    /**
     * 添加 公司信息
     * 
     * @param p 参数对象
     */
    public void addZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        this.getSqlSession().insert("ZpCompanyInfo.addZpCompanyInfo", p);
    }
    
    /**
     * 修改 公司信息
     * 
     * @param p 参数对象
     */
    public void updateZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        this.getSqlSession().update("ZpCompanyInfo.updateZpCompanyInfo", p);
    }
    
    /**
     * 删除  公司信息
     * 
     * @param id 主键
     */
    public void deleteZpCompanyInfo(java.lang.Long id)throws Exception {
        getSqlSession().delete("ZpCompanyInfo.deleteZpCompanyInfo", id);
    }
    
    /**
     * 删除  公司信息
     * 
     * @param id 主键
     */
    public void deleteZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        getSqlSession().delete("ZpCompanyInfo.deleteZpCompanyInfo", p);
    }
    
    /**
     * 获得  公司信息
     * 
     * @param id 公司信息Id
     * 
     * @return 返回与ID匹配的公司信息
     */
    public ZpCompanyInfo getZpCompanyInfoById(java.lang.Long id)throws Exception {
        return (ZpCompanyInfo) getSqlSession().selectOne("ZpCompanyInfo.getZpCompanyInfoById", id);
    }
    
    /**
     * 获得  公司信息
     * 
     * @param name 公司信息名称
     * 
     * @return 返回与NAME匹配的公司信息
     */
    public ZpCompanyInfo getZpCompanyInfoByName(String name)throws Exception {
        return null;
        //return (ZpCompanyInfo) getSqlSession().selectOne("ZpCompanyInfo.getZpCompanyInfoByName", name);
    }
    
    
    
    /**
     * 查询  公司信息列表条数
     * 
     * @param name 公司信息名称
     * @return 公司信息列表条数
     */
    
    public int getZpCompanyInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return (Integer) getSqlSession().selectOne("ZpCompanyInfo.getZpCompanyInfoListCount", pMap);
    }

    /**
     * 查询 公司信息列表
     * 
     * @param name 公司信息名称
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 公司信息列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList(HashMap<String, Object> pMap, int skipResults, int maxResults)throws Exception {
    	pMap.put("skipResults", skipResults);
    	pMap.put("maxResults", maxResults);
        return getSqlSession().selectList("ZpCompanyInfo.queryZpCompanyInfoList", pMap);
    }
    
    /**
     * 查询 公司信息列表
     * 
     * @param pMap 参数列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList(HashMap<String, Object> pMap)throws Exception {
        return getSqlSession().selectList("ZpCompanyInfo.queryZpCompanyInfoList", pMap);
    }
    
    /**
     * 查询 公司信息列表
     * 
     * @return 公司信息列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList()throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return getSqlSession().selectList("ZpCompanyInfo.queryZpCompanyInfoList",params);
    }
    
    /**
     * 查询 公司信息列表
     * 
     * @return 公司信息列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList(ZpCompanyInfo p )throws Exception {
        return getSqlSession().selectList("ZpCompanyInfo.queryZpCompanyInfoList",p);
    }
    
    

}
