package com.zhongyi.www.dao;


import com.zhongyi.www.po.Student;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class SeekS {
	public List<Student> seekS(String select, String seek, String name, List<Student> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String s1 = "模糊查询", s2 = "精确查询";
		if (conn != null) {
			try {
				String sql = "";
				if (seek.equals(s1)) {
					// 条件查询学生信息
					sql = "select * from student where " + select + " like '%" + name + "%'";
					ps = conn.prepareStatement(sql);
				} else if (seek.equals(s2)) {
					sql = "select * from student where " + select + "=?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, name);
				}
				if (ps != null) {
					rs = ps.executeQuery();
				}

				while (rs.next()) {
					Student s = new Student();
					s.setNo(rs.getString("s_no"));
					s.setName(rs.getString("s_name"));
					s.setSex(rs.getString("s_sex"));
					s.setAge(rs.getString("s_age"));
					s.setCollage(rs.getString("s_collage"));
					s.setDepartment(rs.getString("s_department"));
					list.add(s);
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
