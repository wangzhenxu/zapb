package com.loiot.baqi.mybatis;

/**
 * MySql方言
 * 
 * @author zhengrj
 * 
 */
public class MySqlDialect extends Dialect {

    /**
     * 返回MySql的分页SQL
     * 
     * @param sql 原始SQL
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 添加分页语句后的SQL
     */
    @Override
    public String getLimitString(String sql, int skipResults, int maxResults) {
        return new StringBuffer(sql.length() + 100).append(sql).append(" limit ").append(skipResults).append(",")
                .append(maxResults).toString();
    }

}
