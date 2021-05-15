package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Wh1t3zZ
 */
public class SeekElected {
	public Boolean seekExist(String cNo, String sNo) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 根据课程编号和学号判断该课是否已选
				String sql = "select * from electcourse where c_no=? and s_no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, cNo);
				ps.setString(2, sNo);
				// 执行更新操作
				rs = ps.executeQuery();
				while (rs.next()) {
					bool = true;
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
		return bool;
	}
}
