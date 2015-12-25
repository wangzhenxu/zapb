package com.loiot.baqi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.constant.URLConst;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.pojo.ZpCompanyInfo;
import com.loiot.baqi.security.shiro.UsernameExistException;
import com.loiot.baqi.service.AccountService;
import com.loiot.baqi.service.DownloadService;
import com.loiot.baqi.service.RoleService;
import com.loiot.baqi.status.AccountType;
import com.loiot.baqi.status.PauseStartType;
import com.loiot.baqi.utils.UserSessionUtils;
import com.loiot.commons.utils.JsonUtil;
import com.timeloit.pojo.Account;

/**
 * 下载控制器
 * 
 * @author wangzx
 * 
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    public static final AjaxResponse ACCOUNT_USERNAME_EXIST = new AjaxResponse(-100301, "用户名已存在");

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    

    /**
     * 下载业务逻辑
     */
    @Resource
    private DownloadService downloadService;
   
    /**下载序列号
     * @param response
     * @param pbId
     */
    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public String downLoad(HttpServletResponse response,
    		@RequestParam(value="id",required=true) long id,
    		@RequestParam(value="type",required=true) int type){
            
    	try {
			downloadService.download(response, id, type);
		} catch (Exception e) {
			return URLConst.ERROR_URL;
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	return null;
    }
    
}
