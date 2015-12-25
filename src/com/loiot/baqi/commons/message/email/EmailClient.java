package com.loiot.baqi.commons.message.email;
import java.io.IOException;
import java.util.List;

import javax.mail.internet.MimeMessage;

import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.loiot.commons.message.MessageClient;
import com.loiot.commons.message.MessageVo;
import com.loiot.commons.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 邮件客户端
 * 
 * @author zhengrj
 */
public class EmailClient implements MessageClient {

	private static Logger log = Logger.getLogger(EmailClient.class);

	/**
	 * 模板位置
	 */
	@Setter
	private String templatePath;
	/**
	 * 模板编码
	 */
	@Setter
	private String templateEncoding;
	/**
	 * 邮件编码
	 */
	@Setter
	private String mailEncoding;
	/**
	 * 邮件发送显示联系人
	 */
	@Setter
	private String mailSenderDisplay;

	/**
	 * 邮件发送
	 */
	@Setter
	private JavaMailSender mailSender;

	/**
	 * freemarker模板配置
	 */
	private Configuration config = null;

	/**
	 * 初始化方法
	 */
	public void init() {
		// 实例化模板配置
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath(templatePath);
		try {
			config = bean.createConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送单个邮件。
	 * 
	 * @param vo
	 *            邮件。
	 */
	public void send(MessageVo vo) {

		// 转换为抽象邮件
		EmailVo emailVo = (EmailVo) vo;

		try {
			// 创建邮件消息体
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, mailEncoding);
			// 设置邮件发送列表
			helper.setTo(emailVo.getEmails());
			// 设置发送现实人
			helper.setFrom(AddressUtil.getFromInternetAddress(mailSenderDisplay));
			// 设置主题
			helper.setSubject(emailVo.getTitle());
			// 设置发送内容
			helper.setText(getContent(vo), true);
			// 发送邮件
			mailSender.send(msg);
			log.info("邮件发送成功 :" + StringUtil.join(emailVo.getEmails()));
		} catch (Exception e) {
			log.error("构造邮件失败", e);
		}
	}

	/**
	 * 获得邮件内容
	 * 
	 * @param vo
	 *            邮件
	 * @return 邮件内容
	 */
	private String getContent(MessageVo vo) {

		if (vo instanceof SimpleEmailVo) {
			// 简单邮件
			SimpleEmailVo simpleVo = (SimpleEmailVo) vo;
			return simpleVo.getContent();
		} else if (vo instanceof TemplateEmailVo) {
			// 模板邮件
			TemplateEmailVo templateVo = (TemplateEmailVo) vo;
			try {
				// 获取模板
				Template template = config.getTemplate(templateVo.getTemplateName(), templateEncoding);
				// 编译模板内容
				return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateVo.getTemplateParams());
			} catch (IOException e) {
				log.error("read template error.", e);
			} catch (TemplateException e) {
				log.error("read template error.", e);
			}
		}
		return null;
	}

	/**
	 * 发送多个邮件。
	 * 
	 * @param list
	 *            邮件列表。
	 */
	public void send(List<MessageVo> list) {
		for (MessageVo sms : list) {
			send(sms);
		}
	}
}
