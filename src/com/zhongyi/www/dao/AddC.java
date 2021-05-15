package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class AddC {
	public Boolean addC(String no, String name, String time, String place, String credit, String teacher) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				//添加课程信息
				String sql = "insert into course (c_no,c_name,c_time,c_place,c_credit,c_teacher,c_limitnum,c_residuenum) values(?,?,?,?,?,?,?,?)";
				// 创建PreparedStatement对象
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值
				ps.setString(1, no);
				ps.setString(2, name);
				ps.setString(3, time);
				ps.setString(4, place);
				ps.setString(5, credit);
				ps.setString(6, teacher);
				ps.setString(7, "30");
				ps.setString(8, "30");
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
