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
import com.loiot.baqi.dao.TgSuggestBugInfoDao;
import com.loiot.baqi.service.TgSuggestBugInfoService;
import com.loiot.baqi.pojo.TgSuggestBugInfo;


/**
 * 建议 逻辑类。
 * 
 * @author  wangzx 
 * @creation 2015-12-30
 */
@Service("tgSuggestBugInfoService")
@Transactional
public class TgSuggestBugInfoService{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private TgSuggestBugInfoDao tgSuggestBugInfoDao;
	
	
	 /**
     * 查询 建议列表分页
     * 
     * @param name 建议名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<TgSuggestBugInfo> queryTgSuggestBugInfoListPage(HashMap<String,Object> pMap, int pageIndex)throws Exception {

        // 查询建议列表总条数
        int totalResults = tgSuggestBugInfoDao.getTgSuggestBugInfoListCount(pMap);

        // 构造一个分页器
        Pager<TgSuggestBugInfo> pager = new Pager<TgSuggestBugInfo>(totalResults, pageIndex);

        // 查询建议列表
        List<TgSuggestBugInfo> tgSuggestBugInfoList = tgSuggestBugInfoDao.queryTgSuggestBugInfoList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(tgSuggestBugInfoList);
        return pager;
    }
    
    
    
    
    /**
     * 查询 建议（假）分页
     * 
     * @param name 建议名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<TgSuggestBugInfo> queryFlasePageList(HashMap<String,Object> pMap, int pageIndex)throws Exception {
    	//假分页
    	Pager<TgSuggestBugInfo> pager = this.setPkList(pMap,pageIndex);
    	List<TgSuggestBugInfo> tgSuggestBugInfoList = tgSuggestBugInfoDao.queryTgSuggestBugInfoList(pMap);
        pager.setData(tgSuggestBugInfoList);
        return pager;
    }
    
    /**
     * 设置 假分页id集合到Map中
     * @param pMap
     * @param pageIndex
     * @return
     * @throws Exception
     */
    public Pager<TgSuggestBugInfo> setPkList(HashMap<String,Object> pMap,int pageIndex) throws Exception{
    	  // 查询职位匹配信息列表总条数
        List<TgSuggestBugInfo> list = tgSuggestBugInfoDao.queryTgSuggestBugInfoList(pMap);
        // 构造一个分页器
        Pager<TgSuggestBugInfo> pager = new Pager<TgSuggestBugInfo>(list.size(), pageIndex, 5,list);
        List<TgSuggestBugInfo> idsList = pager.getCurrentPageData();
        List<Long> ids =this.getIds(idsList);
        pMap.put("ids", ids);
        return pager;
    }
	
	 /**
     * 添加 建议
     * 
     * @param p 参数对象
     */
    public TgSuggestBugInfo addTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
       return  tgSuggestBugInfoDao.addTgSuggestBugInfo(p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
        tgSuggestBugInfoDao.updateTgSuggestBugInfo(p);
    }
    
    /**
     * 修改 建议
     * 
     * @param p 参数对象
     */
    public void updateTgSuggestBugInfo(HashMap<String,Object> pMap)throws Exception {
        tgSuggestBugInfoDao.updateTgSuggestBugInfo(pMap);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgSuggestBugInfo(java.lang.Long id)throws Exception {
        tgSuggestBugInfoDao.deleteTgSuggestBugInfo(id);
    }
    
    /**
     * 删除  建议
     * 
     * @param id 主键
     */
    public void deleteTgSuggestBugInfo(TgSuggestBugInfo p)throws Exception {
        tgSuggestBugInfoDao.deleteTgSuggestBugInfo(p);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoById(java.lang.Long id)throws Exception {
        return  tgSuggestBugInfoDao.getTgSuggestBugInfoById(id);
    }
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     * @return 返回与ID匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoById(java.lang.Long id,Long accountId)throws Exception {
        return  tgSuggestBugInfoDao.getTgSuggestBugInfoById(id,accountId);
    }
    
    
    /**
     * 获得  建议
     * 
     * @param id 建议Id
     * 
     */
    public TgSuggestBugInfo getTgSuggestBugInfo(HashMap<String,Object> pMap)throws Exception {
    	return (TgSuggestBugInfo) tgSuggestBugInfoDao.getTgSuggestBugInfo(pMap);
    }
    /**
     * 获得  建议
     * 
     * @param name 建议名称
     * 
     * @return 返回与NAME匹配的建议
     */
    public TgSuggestBugInfo getTgSuggestBugInfoByName(String name)throws Exception {
        return  tgSuggestBugInfoDao.getTgSuggestBugInfoByName(name);
    }
    
    /**
     * 查询 建议列表
     * @return 建议列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList(HashMap<String,Object> pMap)throws Exception {
        return  tgSuggestBugInfoDao.queryTgSuggestBugInfoList(pMap);
    }
    
    /**
     * 查询 建议列表
     * @return 建议列表
     */
    public List<TgSuggestBugInfo> queryTgSuggestBugInfoList(TgSuggestBugInfo p)throws Exception {
        return  tgSuggestBugInfoDao.queryTgSuggestBugInfoList(p);
    }
    
    /**
     * 查询  建议列表条数
     * 
     * @param name 建议名称
     * @return 建议列表条数
     */
    
    public int getTgSuggestBugInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return  tgSuggestBugInfoDao.getTgSuggestBugInfoListCount(pMap);
    }
    
    /**
     * 查询id集合
     * @return
     */
    public List<Long> getIds(List<TgSuggestBugInfo> list) {
    	List<Long> idsList = null;
        if(list!=null && list.size()>0) {
        	idsList = new ArrayList<Long>();
        	for (TgSuggestBugInfo b : list) {
            	idsList.add(null);
            }
        }
        return idsList;
    }
    
    /**
     * 统计 建议列表
     * 
     * @return 建议列表
     */
    public List<HashMap<String,Object>> statisticsTgSuggestBugInfoInfo(HashMap<String, Object> pMap )throws Exception {
    	return  tgSuggestBugInfoDao.statisticsTgSuggestBugInfoInfo(pMap);
    }
}
