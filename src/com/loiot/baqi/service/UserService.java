package com.loiot.baqi.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.loiot.baqi.constant.Const;
import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.UserDao;
import com.loiot.commons.utils.EncryptUtil;
import com.loiot.commons.utils.RandomUtil;
import com.timeloit.pojo.CustomerAccount;
import com.timeloit.pojo.ProjectRest;
import com.timeloit.pojo.Scene;
import com.timeloit.pojo.User;

/**
 * 网站用户业务逻辑类
 * 
 * @author zhengrj
 * 
 */
@Service("userService")
public class UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * yunjian访问类
	 */
	@Resource
	private HttpClientService httpClientService;
	/**
	 * 网站用户数据访问接口
	 */
	@Resource
	private UserDao userDao;

	/**
	 * 查询网站用户列表分页
	 * 
	 * @param userName
	 *            网站用户名
	 * @param pageIndex
	 *            页索引
	 * @return
	 */
	public Pager<User> getUserListPage(String userName, int pageIndex) {

		// 查询网站用户列表总条数
		int totalResults = userDao.getUserListCount(userName);

		// 构造一个分页器
		Pager<User> pager = new Pager<User>(totalResults, pageIndex);

		// 查询网站用户列表
		List<User> userList = userDao.getUserList(userName,
				pager.getSkipResults(), pager.getMaxResults());
		pager.setData(userList);

		return pager;
	}

	/**
	 * 通过ID查询用户详细信息
	 * 
	 * @param user_id
	 * @return
	 */
	public User getUserinfoById(long user_id) {
		return userDao.getUserinfoById(user_id);
	}

	/**
	 * 查询场景产品相关信息
	 * 
	 * @param user_id
	 * @return
	 */
	public Pager<Scene> getSceneAndProductByUserId(long user_id, int pageIndex) {

		// 查询网站用户列表总条数
		int totalResults = userDao.getPruductCountByUserId(user_id);
		// 构造一个分页器
		Pager<Scene> pager = new Pager<Scene>(totalResults, pageIndex);
		// 查询网站用户列表
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("user_id", user_id);
		hashMap.put("skipResults", pager.getSkipResults());
		hashMap.put("maxResults", pager.getMaxResults());
		List<Scene> scenes = userDao.getSceneAndProductByUserId(hashMap);
		pager.setData(scenes);
		return pager;
	}

	public void addUser(CustomerAccount account, ProjectRest projectRest)
			throws RestClientException, RuntimeException {
		// TODO Auto-generated method stub
		// 调用yunjian注册用户接口
		User user = new User();
		user.setLoginName(account.getCaName()+"_" +account.getCaId() );
		user.setEmail(account.getCaId()+"_" + account.getCaName()+"@timeloit.com");
		String passTxt = RandomUtil.nextString(6);
		user.setPassword(passTxt);
		httpClientService.regUser(user);
		user = this.getUser(user);
		if(user==null){
			throw new RuntimeException("数据平台用户注册失败！");
		}
		projectRest.setPwd(passTxt);
		projectRest.setUserId(user.getUserId());
	}
/**
 * 以用户名来查询
 * @param user
 * @return
 */
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return this.userDao.getUser(user);
	}

	/**
	 * 加密用户密码
	 * 
	 */
	public static String encodePassword(String pass) {
		String resultString = null;
		try {
			resultString = EncryptUtil.md5Hex(pass);
			resultString = resultString.toLowerCase(); // 更新了Commons之后，返回大写的md5值，而之前存储的是小写的md5值，导致第二次计算所得md5值与就数据不能对应，所以在此处转为小写
			resultString = resultString + Const.USER_PASSWORD_KEY;
			resultString = EncryptUtil.md5Hex(resultString);
			;
			resultString = resultString.toLowerCase(); // 同上md5小写处理
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return resultString;
	}

}
