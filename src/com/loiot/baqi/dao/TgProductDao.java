package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.loiot.baqi.pojo.TgProduct;

/**
 * 建议 访问层。
 * 
 * @author  wangzx 
 * @creation 2016-01-15
 */
@Repository("tgProductDao")
public class TgProductDao extends SqlSessionDaoSupport{
    
    /**
     * 添加 建议
     * 
     * @param p 参数对象
     */
    public TgProduct addTgProduct(TgProduct p)throws Exception {
        this.getSqlSession().insert("TgProduct.addTgProduct", p);
        return p;
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgProduct(TgProduct p)throws Exception {
        this.getSqlSession().update("TgProduct.updateTgProduct", p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgProduct(HashMap<String,Object> pMap)throws Exception {
        this.getSqlSession().update("TgProduct.updateTgProductByMap", pMap);
    }
    
    
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgProduct(java.lang.Long id)throws Exception {
        HashMap<String,Object> pMap = new HashMap<>();
        pMap.put("id", id);
        getSqlSession().delete("TgProduct.deleteTgProduct", pMap);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgProduct(TgProduct p)throws Exception {
        getSqlSession().delete("TgProduct.deleteTgProduct", p);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgProduct getTgProductById(java.lang.Long id)throws Exception {
    	HashMap<String, Object> pMap = new HashMap<String, Object>();
    	pMap.put("id", id == 0 ? "0":id);
    	return (TgProduct) getSqlSession().selectOne("TgProduct.getTgProductById", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgProduct getTgProductById(java.lang.Long id,Long accountId)throws Exception {
    	HashMap<String, Object> pMap = new HashMap<String, Object>();
    	pMap.put("id", id);
    	pMap.put("inPerson", accountId);
    	return (TgProduct) getSqlSession().selectOne("TgProduct.getTgProductById", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     */
    public TgProduct getTgProduct(HashMap<String,Object> pMap)throws Exception {
    	return (TgProduct) getSqlSession().selectOne("TgProduct.queryTgProductList", pMap);
    }
    
    /**
     * 获得  建议
     * 
     * @param name 建议名称
     * 
     * @return 返回与NAME匹配的建议
     */
    public TgProduct getTgProductByName(String name)throws Exception {
        return null;
        //return (TgProduct) getSqlSession().selectOne("TgProduct.getTgProductByName", name);
    }
    
    
    
    /**
     * 查询  建议列表条数
     * 
     * @param name 建议名称
     * @return 建议列表条数
     */
    
    public int getTgProductListCount(HashMap<String,Object> pMap)throws Exception {
        return (Integer) getSqlSession().selectOne("TgProduct.getTgProductListCount", pMap);
    }

    /**
     * 查询 建议列表
     * 
     * @param name 建议名称
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 建议列表
     */
    public List<TgProduct> queryTgProductList(HashMap<String, Object> pMap, int skipResults, int maxResults)throws Exception {
    	pMap.put("skipResults", skipResults);
    	pMap.put("maxResults", maxResults);
        return getSqlSession().selectList("TgProduct.queryTgProductList", pMap);
    }
    
    /**
     * 查询 建议列表
     * 
     * @param pMap 参数列表
     */
    public List<TgProduct> queryTgProductList(HashMap<String, Object> pMap)throws Exception {
        return getSqlSession().selectList("TgProduct.queryTgProductList", pMap);
    }
    
    /**
     * 查询 建议列表
     * 
     * @return 建议列表
     */
    public List<TgProduct> queryTgProductList()throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return getSqlSession().selectList("TgProduct.queryTgProductList",params);
    }
    
    /**
     * 查询 建议列表
     * 
     * @return 建议列表
     */
    public List<TgProduct> queryTgProductList(TgProduct p )throws Exception {
        return getSqlSession().selectList("TgProduct.queryTgProductList",p);
    }
    
    /**
     * 统计 建议列表
     * 
     * @return 建议列表
     */
    public List<HashMap<String,Object>> statisticsTgProductInfo(HashMap<String, Object> pMap )throws Exception {
    	List<HashMap<String,Object>> p = null; 
    	p =(List)getSqlSession().selectList("TgProduct.statisticsTgProductInfo",p);
    	return p;
    }
    
    /**
     * 统计 建议列表
     * 
     * @return 建议列表
     */
    public Long getProductMaxId(){
        return (Long)getSqlSession().selectOne("TgProduct.getProductMaxId");
    }
    
}
