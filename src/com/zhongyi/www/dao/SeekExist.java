package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Wh1t3zZ
 */
public class SeekExist {
	public Boolean seekExist(String no, String sort) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean bool = false;
		String sort1 = "", no1 = "";
		String s1 = "管理员", s2 = "学生", s3 = "教师", s4 = "课程";
		// 判断该管理员（学生、教师、课程）是否存在
		if (sort.equals(s1)) {
			sort1 = "manager";
			no1 = "m_name";
		} else if (sort.equals(s2)) {
			sort1 = "student";
			no1 = "s_no";
		} else if (sort.equals(s3)) {
			sort1 = "teacher";
			no1 = "t_no";
		} else if (sort.equals(s4)) {
			sort1 = "course";
			no1 = "c_no";
		}
		if (conn != null) {
			try {
				String sql = "select * from " + sort1 + " where " + no1 + "=?";
				ps = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值
				ps.setString(1, no);
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
