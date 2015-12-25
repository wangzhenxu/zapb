package com.loiot.baqi.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.loiot.baqi.utils.JsonUtils;
import com.loiot.baqi.utils.UserSessionUtils;
import com.loiot.commons.message.util.JsonUtil;
import com.loiot.commons.utils.ReflectUtil;


/**
 * 分页拦截器
 * 
 * @author zhengrj
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

    private final Log log = LogFactory.getLog(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
       
        //不可以，不带条件删除数据
        String sql = boundSql.getSql().toLowerCase().trim();
        if(sql.startsWith("update") || sql.startsWith("delete")){
        	sql=sql.replaceAll("\n"," ");
        	if((sql.indexOf("where ")==-1) || sql.endsWith("1=1")){
        		 throw new RuntimeException("account is :"+UserSessionUtils.getAccount().getUsername()+" update or delete not args  sql:" +boundSql.getSql());
        	}
        }
       /* MappedStatement mappedStatement = (MappedStatement) invocation  
                .getArgs()[0];  
        String sqlId = mappedStatement.getId();  
        String namespace = sqlId.substring(0, sqlId.indexOf('.'));  
        Executor exe = (Executor) invocation.getTarget();  
        String methodName = invocation.getMethod().getName();  
  
        if (methodName.equals("query")) {  
                Object parameter = invocation.getArgs()[1];  
                RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];  
                   
        }  
        else if(methodName.equals("update")){  
            Object parameter = invocation.getArgs()[1];  
        }  */
        
        
        
        
        
        
        //System.out.println(JsonUtils.toJson(boundSql.getParameterMappings()).toString());
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {

            return invocation.proceed();

        }

        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

        Dialect.Type databaseType = null;

        try {

            databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());

        } catch (Exception e) {

            // ignore

        }

        if (databaseType == null) {

            throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
                    + configuration.getVariables().getProperty("dialect"));

        }

        Dialect dialect = null;

        switch (databaseType) {

        case MYSQL:
            dialect = new MySqlDialect();
        default:
            break;
        }

        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        metaStatementHandler.setValue("delegate.boundSql.sql",
                dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));

        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);

        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

        if (log.isDebugEnabled()) {

            log.debug("生成分页SQL : " + boundSql.getSql());

        }

        return invocation.proceed();

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
