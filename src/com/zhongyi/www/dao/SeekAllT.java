package com.zhongyi.www.dao;


import com.zhongyi.www.po.Teacher;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class SeekAllT {
	public List<Teacher> seekAllT(List<Teacher> list) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				// 查询所有教师信息
				String sql = "select * from teacher ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					Teacher t = new Teacher();
					t.setNo(rs.getString("t_no"));
					t.setName(rs.getString("t_name"));
					t.setSex(rs.getString("t_sex"));
					t.setAge(rs.getString("t_age"));
					t.setCollage(rs.getString("t_collage"));
					list.add(t);
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
