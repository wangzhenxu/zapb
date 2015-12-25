package com.loiot.baqi.constant;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * email静态常量
 * 
 * @author zhengrj
 */
public class EmailConst {

    /**
     * 发送邮件相关配置
     */
    private static ResourceBundle emailBundle = PropertyResourceBundle.getBundle("conf/email");

    /**
     * 发件人的显示名称
     */
    public final static String SENDER_DISPLAY = emailBundle.getString("email.sender.display");

    /**
     * 发送人的邮件地址
     */
    public final static String SENDER_EMAIL = emailBundle.getString("email.sender.email");

    /**
     * 发送人的邮件密码
     */
    public final static String SENDER_PASSWORD = emailBundle.getString("email.sender.password");

    /**
     * 邮件服务器地址
     */
    public final static String SERVER_HOST = emailBundle.getString("email.server.host");

    /**
     * 邮件服务器验证方式是否打开STMP
     */
    public final static String SERVER_STMP_AUTH = emailBundle.getString("email.server.smtp.auth");

    /**
     * 节点报警的邮件标题
     */
    public final static String NODE_ALERT_TITLE = emailBundle.getString("email.alert.title");

}
