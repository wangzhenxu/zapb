package com.loiot.baqi.mybatis;

/**
 * 数据库方言
 * 
 * @author zhengrj
 * 
 */
public abstract class Dialect {

    /**
     * 方言类型枚举
     * 
     * @author zhengrj
     * 
     */
    public static enum Type {
        MYSQL, ORACLE
    }

    /**
     * 返回数据方言对应的分页SQL
     * 
     * @param sql 原始SQL
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 添加分页语句后的SQL
     */
    public abstract String getLimitString(String sql, int skipResults, int maxResults);

}
