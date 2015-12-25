package com.loiot.baqi.commons.message.email;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;

public class AddressUtil {
    public final static String regex1 = ".*[<][^>]*[>].*"; // 判断是 xxxx <xxx>格式文本
    public final static String regex2 = "<([^>]*)>"; // 尖括号匹配

    /**
     * 获取发件人
     * 
     * @param from
     * @return
     */
    public static InternetAddress getFromInternetAddress(String from) {
        String personal = null;
        String address = null;

        if (from.matches(regex1)) {
            personal = from.replaceAll(regex2, "").trim();
            Matcher m = Pattern.compile(regex2).matcher(from);
            if (m.find()) {
                address = m.group(1).trim();
            }
            try {
                return new InternetAddress(address, personal, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                return new InternetAddress(from);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
