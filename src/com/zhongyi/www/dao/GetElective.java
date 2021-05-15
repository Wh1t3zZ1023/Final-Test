package com.zhongyi.www.dao;

import com.zhongyi.www.po.Elective;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Wh1t3zZ
 */
public class GetElective {
	public Elective getElective(String cNo, String sNo) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Elective e = new Elective();
		if (conn != null) {
			try {
				// 根据课程编号和学号查询学生的选课信息
				String sql = "select * from electcourse where c_no=? and s_no=?";
				// 创建PreparedStatement对象
				ps = conn.prepareStatement(sql);
				ps.setString(1, cNo);
				ps.setString(2, sNo);
				rs = ps.executeQuery();

				while (rs.next()) {
					// 遍历查询符合条件的对象
					e.setcName(rs.getString("C_Name"));
					e.setsName(rs.getString("S_Name"));
					e.setsNo(sNo);
					e.setcNo(cNo);
				}
			} catch (Exception s) {
				s.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(rs,ps,conn);
			}
		} else {
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return e;
	}

}
