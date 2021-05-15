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
public class SeekElectC {
	public List<Course> seekElectC(String sNo, List<Course> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				// 根据学号查询选修的课程编号
				String sql = "select * from electcourse where s_no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, sNo);
				rs = ps.executeQuery();

				while (rs.next()) {
					// 遍历所有把符合条件的对象存入list集合
					Course c = new Course();
					c.setNo(rs.getString("C_No"));
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
