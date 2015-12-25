package com.loiot.baqi.commons.message.email;

import java.util.Map;

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
public class TemplateEmailVo extends EmailVo implements MessageVo {
	/**
	 * 模板名
	 */
	private String templateName;
	/**
	 * 模板参数
	 */
	private Map<String, Object> templateParams;
}
