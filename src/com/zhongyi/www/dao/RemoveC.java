package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class RemoveC {
	public Boolean removeC(String no) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 删除课程
				String sql = "delete from course where c_no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, no);
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
