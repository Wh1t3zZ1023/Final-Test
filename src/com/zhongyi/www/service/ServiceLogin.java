package com.zhongyi.www.service;


import com.zhongyi.www.dao.Login;
import com.zhongyi.www.po.User;

/**
 * @author Wh1t3zZ
 */
public class ServiceLogin {
	private Login log = new Login();

	public User getLogin(String userSort, String userName, String pwd) {// 登录
		User user = new User();
		user = log.login(userSort, userName, pwd);
		return user;
	}
}
