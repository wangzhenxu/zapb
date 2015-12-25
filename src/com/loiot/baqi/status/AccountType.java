package com.loiot.baqi.status;

/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */

/**
 * 账号类型
 * 
 * @author wangzx
 * 
 */
public enum AccountType {

	HR(1, "HR"),
	
	TECHICAL_AUDIT(2,"技术评审"),
	
	ADMIN(3, "后台管理员"),
	
	JOB_HUNTER(4, "求职者"),
	
	SALARY_MANAGER(5,"薪水管理员"),
	
	HEAD_HUNTING_MANAGER(6,"猎头顾问"),
	
	COMPANY_INTERFACER(7,"企业对接人");

	
	
	
	
	
	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 构造一个类型
	 * 
	 * @param code
	 *            状态码
	 * @param title
	 *            标题
	 */
	AccountType(int code, String title) {
		this.code = code;
		this.title = title;
	}

	/**
	 * 获得此枚举的标题。
	 * 
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 获得此枚举的状态码。
	 * 
	 * @return 状态码
	 */
	public short getCode() {
		return (short) code;
	}

	/**
	 * 根据状态码获得枚举。
	 * 
	 * @param code
	 *            状态码
	 * @return 状态码对应的枚举，如果找不到则返回null。
	 */
	public static AccountType get(int code) {
		for (AccountType type : values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}
