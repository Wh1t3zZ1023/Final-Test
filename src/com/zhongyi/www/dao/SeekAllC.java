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
public class SeekAllC {
	public List<Course> seekAllC(List<Course> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				// 查询所有课程信息
				String sql = "select * from course ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					Course c = new Course();

					c.setNo(rs.getString("c_no"));
					c.setName(rs.getString("c_name"));
					c.setTime(rs.getString("c_time"));
					c.setPlace(rs.getString("c_place"));
					c.setCredit(rs.getString("c_credit"));
					c.setTeacher(rs.getString("c_teacher"));
					c.setLimitNum(rs.getString("c_limitNum"));
					c.setResidueNum(rs.getString("c_residueNum"));
					list.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(rs,ps,conn);
			}
		} else {
			System.out.println("数据库连接错误！");
		}
		return list;
	}

}
