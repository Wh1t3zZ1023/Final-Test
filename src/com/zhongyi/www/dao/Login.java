package com.zhongyi.www.dao;

import com.zhongyi.www.po.JudgeSort;
import com.zhongyi.www.po.User;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author Wh1t3zZ
 */
public class Login {
	public User login(String Usersort, String Username, String Pwd) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		if (conn != null) {
			// 判断用户身份
			JudgeSort js = new JudgeSort(Usersort);
			String TableName = js.getTableName();
			String name = js.getUname();
			String password = js.getPwd();
			try {
				String sql = "select * from " + TableName + " where " + name + "= ? and " + password + "= ?";
				// 创建PreparedStatement对象
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, Username);
				ps.setString(2, Pwd);
				ResultSet rs = ps.executeQuery();
				String uname = "";
				String pwd = "";
				while (rs.next()) {
					// 从数据库中获得用户名，密码
					uname = rs.getString(name);
					pwd = rs.getString(password);
				}
				// 判断用户名，登录密码是否正确
				if (uname.equals(Username) && Pwd.equals(pwd)) {
					User user = new User(uname, pwd);
					return user;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return null;
	}
}
