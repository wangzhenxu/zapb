package com.loiot.baqi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.constant.URLConst;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.pojo.TgProduct;
import com.loiot.baqi.service.TgProductService;
import com.loiot.commons.message.util.JsonUtil;
import com.timeloit.pojo.Account;


/**
 * 商品 处理器。 TgProduct
 * 
 * @author Hinsteny
 * @creation 2016-01-15
 */
@Controller
@RequestMapping(value = "/tgProduct")
public class TgProductController {

    public static final AjaxResponse NAME_EXIST = new AjaxResponse(-100, "商品已存在");

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TgProductService tgProductService;

    private HashMap<String, Object> pmap = new HashMap<String, Object>();

    /**
     * 跳转 商品列表页
     * 
     * @return 模板位置
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "pi", defaultValue = "0") int pageIndex,
            @RequestParam(value = "jsonParam", defaultValue = "{}") String jsonParam, TgProduct p,
            ModelMap model) throws Exception {
        model.put("moduleName", "商品");
        model.put("pageTitle", "列表");
        HashMap<String, Object> paramMap = JsonUtil.toObject(jsonParam, HashMap.class);
        paramMap.put("qtype", "like");
        // 用户数据过滤
        /*
         * if(UserSessionUtils.getAccountType()==AccountType.HR.getCode() ||
         * UserSessionUtils.getAccountType()==AccountType.JOB_HUNTER.getCode() ){
         * paramMap.put("inPerson", UserSessionUtils.getAccount().getAccountId()); }
         */
        Pager<TgProduct> pager = tgProductService.queryTgProductListPage(paramMap, pageIndex);
        model.put("pager", pager);
        putJsonParamIntoModle(paramMap, model);
        return "/product/product_list";
    }

    /**
     * 把客户端的查询参数放置到model中，方便页面获取
     * 
     * @param paramMap
     * @param model
     */
    @SuppressWarnings("rawtypes")
    public void putJsonParamIntoModle(Map paramMap, ModelMap model) {
        if (paramMap == null || paramMap.size() == 0)
            return;
        Iterator iter = paramMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            model.put(String.valueOf(key), val);
        }
    }

    /**
     * 跳转到添加页面
     * 
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAddTgProduct(ModelMap model) {
        model.put("moduleName", "商品");
        model.put("pageTitle", "添加");
        return "/product/product_add";
    }

    /**
     * 添加 商品
     * 
     * @param p 对象参数
     * @return ajax响应
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addTgProduct(TgProduct p, HttpSession session, HttpServletRequest request) {
        try {
            Account account = (Account) session.getAttribute(Const.SESSION_USER_KEY);
            // 验证唯一性
            pmap.clear();
            pmap.put("name", p.getName());
            int result = tgProductService.getTgProductListCount(pmap);
            if (result > 0) {
                return NAME_EXIST;
            }
            p.setInTime(new Date());
            p.setInPerson(account.getAccountId());
            //没有设置数据库的主键自增，所以在这里查询递增
            Long id = tgProductService.getMaxId() + 1;
            p.setId(id);
            tgProductService.addTgProduct(p);
            // 添加成功
            return AjaxResponse.OK;
        } catch (Exception e) {
            e.printStackTrace();
            // 失败
            return AjaxResponse.FAILED;
        }

    }

    /**
     * 跳转到编辑页面
     * 
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public String toEditTgProduct(@RequestParam(value = "id", required = true) java.lang.Long id,
            ModelMap model) throws Exception {
        model.put("moduleName", "商品");
        model.put("pageTitle", "编辑");
        if (id == null) {
            return URLConst.ERROR_URL;
        }
        model.put("pid", id);
        model.put("p", tgProductService.getTgProductById(id));
        return "/product/product_add";
    }

    /**
     * 更新 商品
     * 
     * @param p 对象参数
     * @return ajax响应
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object editTgProduct(TgProduct p, HttpSession session, HttpServletRequest request) {
        try {
            // 获得账号
            Account account = (Account) session.getAttribute(Const.SESSION_USER_KEY);
            // 如果前端，没有改变编号，就不用验证
            String onlyName = request.getParameter("onlyName");
            if (!StringUtils.isBlank(onlyName) && !p.getName().equals(onlyName)) {
                // 验证唯一性
                pmap.clear();
                pmap.put("name", p.getName());
                int result = tgProductService.getTgProductListCount(pmap);
                if (result > 0) {
                    return NAME_EXIST;
                }
            }
            p.setUpdatePerson(account.getAccountId());
            p.setUdpateTime(new Date());
            tgProductService.updateTgProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResponse.FAILED;
        }
        return AjaxResponse.OK;
    }

    /**
     * 跳转到查看页面
     * 
     * @return
     */
    @RequestMapping(value = "/toView")
    public String toViewTgProduct(@RequestParam(value = "id", required = true) java.lang.Long id,
            ModelMap model) throws Exception {
        model.put("moduleName", "商品");
        model.put("pageTitle", "预览");
        if (id == null) {
            return URLConst.ERROR_URL;
        }
        model.put("pid", id);
        model.put("p", tgProductService.getTgProductById(id));
        return "/product/product_add";
    }

    /**
     * 删除 商品
     * 
     * @param id TgProductID
     */
    @RequestMapping(value = "/delete")
    public String deleteTgProduct(@RequestParam(value = "id", required = true) java.lang.Long id,
            HttpServletRequest request) throws Exception {
        tgProductService.deleteTgProduct(id);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/tgProduct/"), s.length());
        return "redirect:" + redirectStr;
    }

    /**
     * ajax删除 商品
     * 
     * @param id TgProductID
     */
    @RequestMapping(value = "/ajaxDelete")
    @ResponseBody
    public Object ajaxDeleteTgProduct(@RequestParam(value = "id", required = true) java.lang.Long id) {
        try {
            TgProduct p = new TgProduct();
            p.setId(id);
            tgProductService.deleteTgProduct(p);
            return AjaxResponse.OK;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return AjaxResponse.FAILED;
        }
    }

    /**
     * ajax 根据id查询实体bean
     * 
     * @return
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public Object ajaxGetById(@RequestParam(value = "id", required = true) java.lang.Long id)
            throws Exception {
        TgProduct p = tgProductService.getTgProductById(id);
        // 用户数据过滤
        /*
         * if(UserSessionUtils.getAccountType()==AccountType.HR.getCode() ||
         * UserSessionUtils.getAccountType()==AccountType.JOB_HUNTER.getCode() ){ p =
         * tgProductService.getTgProductById(id, UserSessionUtils.getAccount().getAccountId()); }
         * else { p = tgProductService.getTgProductById(id); }
         */
        if (p == null) {
            return AjaxResponse.NOEXITS;
        }
        return AjaxResponse.OK(p);
    }

    /**
     * 更新 （停用、启用状态）
     * 
     * @param id
     */
    @RequestMapping(value = "/modifyDeleteStatus")
    public String modifyDeleteStatus(
            @RequestParam(value = "id", required = true) java.lang.Long id, @RequestParam(
                    value = "deleteStatus", required = true) java.lang.Integer isDelete,
            HttpServletRequest request) throws Exception {
        TgProduct p = new TgProduct();
        p.setId(id);
        p.setIsDelete(isDelete);
        tgProductService.updateTgProduct(p);
        String s = request.getHeader("Referer");
        String redirectStr = s.substring(s.indexOf("/tgProduct/"), s.length());
        return "redirect:" + redirectStr;
    }

    /**
     * 查询，名称是否存在，验证唯一性
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkNameExits")
    @ResponseBody
    public Object checkNameExits(
            @RequestParam(value = "name", required = true) java.lang.String name, @RequestParam(
                    value = "oldname", required = false) java.lang.String oldName, @RequestParam(
                    value = "flag", required = true) String flag) throws Exception {
        // 验证唯一性
        pmap.clear();
        pmap.put("name", name);
        if ("edit".equals(flag) && oldName.equals(name)) {
            return AjaxResponse.OK(null);
        }
        int result = tgProductService.getTgProductListCount(pmap);
        if (result > 0) {
            return NAME_EXIST;
        }
        return AjaxResponse.OK(null);
    }


}
