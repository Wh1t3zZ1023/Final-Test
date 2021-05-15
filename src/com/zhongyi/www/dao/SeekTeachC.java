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
public class SeekTeachC {
	public List<Course> seekTeachC(String name, List<Course> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				// 根据教师姓名查询该教师的课程
				String sql = "select * from course where c_teacher=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();

				while (rs.next()) {
					Course c = new Course();
					c.setNo(rs.getString("c_no"));
					c.setName(rs.getString("c_name"));
					c.setTime(rs.getString("c_time"));
					c.setPlace(rs.getString("c_place"));
					c.setCredit(rs.getString("c_credit"));
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
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return list;
	}

}
