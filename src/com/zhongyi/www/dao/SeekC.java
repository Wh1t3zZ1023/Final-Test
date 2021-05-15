package com.zhongyi.www.dao;


import com.zhongyi.www.po.Course;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class SeekC {
	public List<Course> seekC(String select, String seek, String name, List<Course> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String s1 = "模糊查询";
		String s2 = "精确查询";
		if (conn != null) {
			try {
				String sql = "";
				if (seek.equals(s1)) {
					// 模糊查询课程
					sql = "select * from course where " + select + " like '%" + name + "%'";
					ps = conn.prepareStatement(sql);
				} else if (seek.equals(s2)) {
					// 精准查询课程
					sql = "select * from course where " + select + "=?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, name);
				}
				if (ps != null){
					rs = ps.executeQuery();
				}

				while (rs.next()) {
					Course c = new Course();
					c.setNo(rs.getString("c_no"));
					c.setName(rs.getString("c_name"));
					c.setTime(rs.getString("c_time"));
					c.setPlace(rs.getString("c_place"));
					c.setCredit(rs.getString("c_credit"));
					c.setTeacher(rs.getString("c_teacher"));
					list.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(rs,ps,conn);
			}
		} else {
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return list;
	}

}
