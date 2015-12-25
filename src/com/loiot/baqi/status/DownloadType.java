package com.loiot.baqi.status;

/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */

/**
 * 下载类型
 * 
 * @author wangzx
 * 
 */
public enum DownloadType {

	JL(1, "简历");
	
	
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
	DownloadType(int code, String title) {
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
	public static DownloadType get(int code) {
		for (DownloadType type : values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}
