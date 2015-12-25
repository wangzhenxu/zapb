package com.loiot.baqi.commons.message.email;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.loiot.commons.message.MessageVo;

/**
 * 邮件。
 * 
 * @author zhengrunjin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleEmailVo extends EmailVo implements MessageVo {
	/**
	 * 内容
	 */
	private String content;
}
