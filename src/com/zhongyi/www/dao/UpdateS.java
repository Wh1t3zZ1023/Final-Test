package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class UpdateS {
	public Boolean updateS(String no, String name, String sex, String age, String collage, String department,
			String pwd) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 修改学生信息
				String sql = "update  student set s_name=?,s_sex=?,s_age=?,s_collage=?,s_department=?,s_pwd=? where S_No=? ";
				ps = conn.prepareStatement(sql);

				ps.setString(1, name);
				ps.setString(2, sex);
				ps.setString(3, age);
				ps.setString(4, collage);
				ps.setString(5, department);
				ps.setString(6, pwd);
				ps.setString(7, no);
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
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return bool;
	}

}
