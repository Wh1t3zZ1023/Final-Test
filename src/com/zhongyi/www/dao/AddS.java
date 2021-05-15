package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class AddS {
	public Boolean addS(String no, String name, String sex, String age, String collage, String department,
			String pwd) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 插入注册信息的SQL语句(使用?占位符) //添加学生信息
				String sql = "insert into student (s_no,s_name,s_sex,s_age,s_collage,s_department,s_pwd) values(?,?,?,?,?,?,?)";
				// 创建PreparedStatement对象
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值
				ps.setString(1, no);
				ps.setString(2, name);
				ps.setString(3, sex);
				ps.setString(4, age);
				ps.setString(5, collage);
				ps.setString(6, department);
				ps.setString(7, pwd);
				// 执行更新操作
				int count = ps.executeUpdate();
				if (count >= 1) {
					bool = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(ps,conn);
			}
		} else {
			System.out.println("数据库连接错误！");

		}
		return bool;
	}
}
