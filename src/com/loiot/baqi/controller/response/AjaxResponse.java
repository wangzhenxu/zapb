package com.loiot.baqi.controller.response;

import java.io.Serializable;

import lombok.Data;

/**
 * ajax响应类
 * 
 * @author zhengrj
 * 
 */
@Data
public class AjaxResponse implements Serializable {

    private static final long serialVersionUID = -603454364015061374L;

    public static final AjaxResponse OK = new AjaxResponse(1, "ok");
    public static final AjaxResponse FAILED = new AjaxResponse(-1, "系统繁忙，请您稍后再试");
    public static final AjaxResponse SYSTEM_BUSY = new AjaxResponse(-100000, "系统繁忙，请您稍后再试");
    public static final AjaxResponse NOEXITS = new AjaxResponse(-1000, "信息不存在");
    public static final AjaxResponse ILLEGAL_OPERATER = new AjaxResponse(-10000, "非法操作");



    /**
     * 状态码
     */
    private int s;
    /**
     * 描述原因
     */
    private String d;
    /**
     * 响应数据
     */
    private Object data;

    /**
     * 构造一个有状态码和描述的Ajax响应
     * 
     * @param s 状态码
     * @param d 描述
     */
    public AjaxResponse(int s, String d) {
        this.s = s;
        this.d = d;
    }

    /**
     * 构造一个有状态码、描述、数据的Ajax响应
     * 
     * @param s 状态码
     * @param d 描述
     * @param data 响应数据
     */
    public AjaxResponse(int s, String d, Object data) {
        this.s = s;
        this.d = d;
        this.data = data;
    }

    /**
     * 产生一个成功的响应，并携带数据
     * 
     * @return
     */
    public static AjaxResponse OK(Object data) {
        return new AjaxResponse(1, "ok", data);
    }
}
