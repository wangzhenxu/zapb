package com.loiot.baqi.controller.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.service.ZpDictionaryInfoService;
import com.loiot.baqi.utils.IndexInfoSingleTon;


/**
 * CacheInitialize.java
 * 
 * @author: Hinsteny
 * @date: 2016年1月11日
 * @copyright: 2016 All rights reserved.
 * 
 */
@Component
public class CacheInitialize implements InitializingBean {

    @Resource
    private ZpDictionaryInfoService zpDictionaryInfoService;
    
    @Override
    @SuppressWarnings("rawtypes")
    public void afterPropertiesSet() throws Exception {
        IndexInfoSingleTon indexInfo = IndexInfoSingleTon.getInstance();
        Map<String, List> infoMap = indexInfo.getIndexInfoMap();
        infoMap.put(Const.SESSION_DICTIONARYS_KEY, zpDictionaryInfoService.queryZpDictionaryInfoList(new HashMap<String,Object>()));
    }

}
