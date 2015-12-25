package com.loiot.baqi.model;

public class ResAtom {

    private int st=1;//StateType  返回结果状态类型 ， 类型含义参考服务器请求错误类型定义
    private String sd="";//StateDescription  返回结果状态描述
    private String result = "";		//设备返回信息

    public int getSt() {
        return st;
    }
    public void setSt(int st) {
        this.st = st;
    }
    public String getSd() {
        return sd;
    }
    public void setSd(String sd) {
        this.sd = sd;
    }
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
    
}
