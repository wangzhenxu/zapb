package com.loiot.baqi.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.ZpCompanyInfoDao;
import com.loiot.baqi.service.ZpCompanyInfoService;
import com.loiot.baqi.pojo.ZpCompanyInfo;

/**
 * 公司信息 逻辑类。
 * 
 * @author  wangzx 
 * @creation 2015-09-29
 */
@Service("zpCompanyInfoService")
@Transactional
public class ZpCompanyInfoService{
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
	private ZpCompanyInfoDao zpCompanyInfoDao;
	
	
	 /**
     * 查询 公司信息列表分页
     * 
     * @param name 公司信息名称
     * @param pageIndex 页索引
     * @return
     */
    public Pager<ZpCompanyInfo> queryZpCompanyInfoListPage(HashMap<String,Object> pMap, int pageIndex)throws Exception {

        // 查询公司信息列表总条数
        int totalResults = zpCompanyInfoDao.getZpCompanyInfoListCount(pMap);

        // 构造一个分页器
        Pager<ZpCompanyInfo> pager = new Pager<ZpCompanyInfo>(totalResults, pageIndex);

        // 查询公司信息列表
        List<ZpCompanyInfo> zpCompanyInfoList = zpCompanyInfoDao.queryZpCompanyInfoList(pMap, pager.getSkipResults(),
                pager.getMaxResults());
        pager.setData(zpCompanyInfoList);
        return pager;
    }
	
	 /**
     * 添加 公司信息
     * 
     * @param p 参数对象
     */
    public void addZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        zpCompanyInfoDao.addZpCompanyInfo(p);
    }
    
    /**
     * 修改 公司信息
     * 
     * @param p 参数对象
     */
    public void updateZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        zpCompanyInfoDao.updateZpCompanyInfo(p);
    }
    
    /**
     * 删除  公司信息
     * 
     * @param id 主键
     */
    public void deleteZpCompanyInfo(java.lang.Long id)throws Exception {
        zpCompanyInfoDao.deleteZpCompanyInfo(id);
    }
    
    /**
     * 删除  公司信息
     * 
     * @param id 主键
     */
    public void deleteZpCompanyInfo(ZpCompanyInfo p)throws Exception {
        zpCompanyInfoDao.deleteZpCompanyInfo(p);
    }
    
    /**
     * 获得  公司信息
     * 
     * @param id 公司信息Id
     * 
     * @return 返回与ID匹配的公司信息
     */
    public ZpCompanyInfo getZpCompanyInfoById(java.lang.Long id)throws Exception {
        return  zpCompanyInfoDao.getZpCompanyInfoById(id);
    }
    
    /**
     * 获得  公司信息
     * 
     * @param name 公司信息名称
     * 
     * @return 返回与NAME匹配的公司信息
     */
    public ZpCompanyInfo getZpCompanyInfoByName(String name)throws Exception {
        return  zpCompanyInfoDao.getZpCompanyInfoByName(name);
    }
    
    /**
     * 查询 公司信息列表
     * @return 公司信息列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList(HashMap<String,Object> pMap)throws Exception {
        return  zpCompanyInfoDao.queryZpCompanyInfoList(pMap);
    }
    
    /**
     * 查询 公司信息列表
     * @return 公司信息列表
     */
    public List<ZpCompanyInfo> queryZpCompanyInfoList(ZpCompanyInfo p)throws Exception {
        return  zpCompanyInfoDao.queryZpCompanyInfoList(p);
    }
    
    /**
     * 查询  公司信息列表条数
     * 
     * @param name 公司信息名称
     * @return 公司信息列表条数
     */
    
    public int getZpCompanyInfoListCount(HashMap<String,Object> pMap)throws Exception {
        return  zpCompanyInfoDao.getZpCompanyInfoListCount(pMap);
    }
	
}
