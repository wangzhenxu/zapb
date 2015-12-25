package com.loiot.baqi.exception;

import lombok.Getter;

import org.springframework.core.NestedCheckedException;

/**
 * 操作已删除应用,需要捕获的。
 * 
 * @author zhengrunjin
 * 
 */
public class IllegalAppException extends NestedCheckedException {

	private static final long serialVersionUID = -395692702693182512L;

	/**
	 * 状态码
	 */
	@Getter
	private int code;

	/**
	 * 实例化一个需要捕获的异常
	 * 
	 * @param code
	 *            状态码
	 * @param msg
	 *            消息
	 */
	public IllegalAppException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	
	/**
	 * 抛出统一错误
	 */
	public IllegalAppException() {
		super("非法操作");
	}
}
