package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class Grade {
	public Boolean grade(String cNo, String sNo, String grade) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 根据课程编号和学号给该学生的该选课评分
				String sql = "update electcourse set grade=? where c_no=? and s_no=?";
				// 创建PreparedStatement对象
				ps = conn.prepareStatement(sql);
				ps.setString(1, grade);
				ps.setString(2, cNo);
				ps.setString(3, sNo);
				int count = ps.executeUpdate();
				if (count >= 1) {
					bool = true;
				}
			} catch (Exception s) {
				s.printStackTrace();
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
