package com.zhongyi.www.dao;

import com.zhongyi.www.po.Elective;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Wh1t3zZ
 */
public class SeekScore {
	public List<Elective> seekScore(String sNo) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Elective> list = new ArrayList<>();
		if (conn != null) {
			try {
				// 根据学号查询成绩
				String sql = "select * from electcourse where s_no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, sNo);
				rs = ps.executeQuery();

				while (rs.next()) {
					Elective e = new Elective();
					e.setcNo(rs.getString("c_no"));
					e.setcName(rs.getString("c_name"));
					e.setsName(rs.getString("s_name"));
					e.setGrade(rs.getString("grade"));
					list.add(e);
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
