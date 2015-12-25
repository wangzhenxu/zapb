package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.timeloit.pojo.Scene;
import com.timeloit.pojo.User;

/**
 * 网站用户数据访问层。
 * 
 * @author zhengrj
 */
@Repository("userDao")
public class UserDao extends SqlSessionDaoSupport {

    /**
     * 查询网站用户列表条数
     * 
     * @param username 网站用户名
     * @return 网站用户列表
     */
    public int getUserListCount(String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(username)) {
            params.put("username", username);
        }

        return (Integer) getSqlSession().selectOne("User.getUserListCount", params);
    }

    /**
     * 查询网站用户列表
     * 
     * @param username 网站用户名
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 网站用户列表
     */
    public List<User> getUserList(String username, int skipResults, int maxResults) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("skipResults", skipResults);
        params.put("maxResults", maxResults);
        if (!StringUtils.isEmpty(username)) {
            params.put("username", username);
        }

        return getSqlSession().selectList("User.getUserList", params);
    }
    
    
    /**
     * 通过ID查询用户详细信息
     * @param user_id
     * @return
     */
    public User getUserinfoById(long user_id) {
        return (User) getSqlSession().selectOne("User.getUserinfoById", user_id);
    }
    
    /**
     * 查询场景产品相关信息
     * @param user_id
     * @return
     */
    public List<Scene> getSceneAndProductByUserId(Map<String, Object> map) {
        return getSqlSession().selectList("User.getSceneAndProductByUserId", map);
    }
    
    
    /**
     * 查询场景总数
     * @param user_id
     * @return
     */
    public Integer getPruductCountByUserId(long user_id) {
        return (Integer) getSqlSession().selectOne("User.getPruductCountByUserId", user_id);
    }

    /**
     * 自动注册数据平台账号
     * @param user
     */
	public void addUser(User user) {
		// TODO Auto-generated method stub
		getSqlSession().insert("User.regUser",user);
	}

	/**
	 * 通过用户名称来查询
	 * @param user
	 * @return
	 */
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return (User)getSqlSession().selectOne("User.getUser",user);
	}
    
    
}
