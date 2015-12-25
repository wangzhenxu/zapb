package com.loiot.baqi.commons.message.email;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.loiot.commons.message.MessageVo;

/**
 * 邮件。
 * 
 * @author zhengrunjin
 */
public abstract class EmailVo implements MessageVo {
	/**
	 * 收件人地址
	 */
	private List<String> emails = new ArrayList<String>();
	/**
	 * 标题
	 */
	@Getter
	@Setter
	private String title;

	/**
	 * 添加收件人
	 * 
	 * @param email
	 *            收件人地址
	 */
	public void addEmail(String... email) {
		for (String e : email) {
			emails.add(e);
		}
	}

	/**
	 * 获得收件人地址
	 * 
	 * @return 收件人地址数组
	 */
	public String[] getEmails() {
		return emails.toArray(new String[] {});
	}
}
