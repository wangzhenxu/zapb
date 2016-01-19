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
import com.loiot.baqi.dao.TgProductDao;
import com.loiot.baqi.service.TgProductService;
import com.loiot.baqi.pojo.TgProduct;


/**
 * 建议 逻辑类。
 * 
 * @author  wangzx 
 * @creation 2016-01-15
 */
@Service("tgProductService")
@Transactional
public class TgProductService{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private TgProductDao tgProductDao;
	
	
	 /**
     * 查询 建议列表分页
     * 
     * @param name 建议名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<TgProduct> queryTgProductListPage(HashMap<String,Object> pMap, int pageIndex)throws Exception {

        // 查询建议列表总条数
        int totalResults = tgProductDao.getTgProductListCount(pMap);

        // 构造一个分页器
        Pager<TgProduct> pager = new Pager<TgProduct>(totalResults, pageIndex);

        // 查询建议列表
        List<TgProduct> tgProductList = tgProductDao.queryTgProductList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(tgProductList);
        return pager;
    }
    
    
    
    
    /**
     * 查询 建议（假）分页
     * 
     * @param name 建议名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<TgProduct> queryFlasePageList(HashMap<String,Object> pMap, int pageIndex)throws Exception {
    	//假分页
    	Pager<TgProduct> pager = this.setPkList(pMap,pageIndex);
    	List<TgProduct> tgProductList = tgProductDao.queryTgProductList(pMap);
        pager.setData(tgProductList);
        return pager;
    }
    
    /**
     * 设置 假分页id集合到Map中
     * @param pMap
     * @param pageIndex
     * @return
     * @throws Exception
     */
    public Pager<TgProduct> setPkList(HashMap<String,Object> pMap,int pageIndex) throws Exception{
    	  // 查询职位匹配信息列表总条数
        List<TgProduct> list = tgProductDao.queryTgProductList(pMap);
        // 构造一个分页器
        Pager<TgProduct> pager = new Pager<TgProduct>(list.size(), pageIndex, 5,list);
        List<TgProduct> idsList = pager.getCurrentPageData();
        List<Long> ids =this.getIds(idsList);
        pMap.put("ids", ids);
        return pager;
    }
	
	 /**
     * 添加 建议
     * 
     * @param p 参数对象
     */
    public TgProduct addTgProduct(TgProduct p)throws Exception {
       return  tgProductDao.addTgProduct(p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgProduct(TgProduct p)throws Exception {
        tgProductDao.updateTgProduct(p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgProduct(HashMap<String,Object> pMap)throws Exception {
        tgProductDao.updateTgProduct(pMap);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgProduct(java.lang.Long id)throws Exception {
        tgProductDao.deleteTgProduct(id);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgProduct(TgProduct p)throws Exception {
        tgProductDao.deleteTgProduct(p);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgProduct getTgProductById(java.lang.Long id)throws Exception {
        return  tgProductDao.getTgProductById(id);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgProduct getTgProductById(java.lang.Long id,Long accountId)throws Exception {
        return  tgProductDao.getTgProductById(id,accountId);
    }
    
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     */
    public TgProduct getTgProduct(HashMap<String,Object> pMap)throws Exception {
    	return (TgProduct) tgProductDao.getTgProduct(pMap);
    }
    /**
     * 获得  建议
     * 
     * @param name 建议名称
     * 
     * @return 返回与NAME匹配的建议
     */
    public TgProduct getTgProductByName(String name)throws Exception {
        return  tgProductDao.getTgProductByName(name);
    }
    
    /**
     * 查询 建议列表
     * @return 建议列表
     */
    public List<TgProduct> queryTgProductList(HashMap<String,Object> pMap)throws Exception {
        return  tgProductDao.queryTgProductList(pMap);
    }
    
    /**
     * 查询 建议列表
     * @return 建议列表
     */
    public List<TgProduct> queryTgProductList(TgProduct p)throws Exception {
        return  tgProductDao.queryTgProductList(p);
    }
    
    /**
     * 查询  建议列表条数
     * 
     * @param name 建议名称
     * @return 建议列表条数
     */
    
    public int getTgProductListCount(HashMap<String,Object> pMap)throws Exception {
        return  tgProductDao.getTgProductListCount(pMap);
    }
    
    /**
     * 查询id集合
     * @return
     */
    public List<Long> getIds(List<TgProduct> list) {
    	List<Long> idsList = null;
        if(list!=null && list.size()>0) {
        	idsList = new ArrayList<Long>();
        	for (TgProduct b : list) {
            	idsList.add(null);
            }
        }
        return idsList;
    }
    
    /**
     * 查询数据库最大值ID
     * @return
     */
    public Long getMaxId() {
        return  tgProductDao.getProductMaxId();
    }
    /**
     * 统计 建议列表
     * 
     * @return 建议列表
     */
    public List<HashMap<String,Object>> statisticsTgProductInfo(HashMap<String, Object> pMap )throws Exception {
    	return  tgProductDao.statisticsTgProductInfo(pMap);
    }
}
