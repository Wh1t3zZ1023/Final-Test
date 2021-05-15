package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Wh1t3zZ
 */
public class PlusResidueNum {
	public Boolean plusResidueNum(String cNo) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		Boolean bool = false;
		if (conn != null) {
			try {
				// 根据课程名称查询课程可选人数
				String sql = "select * from course where c_no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, cNo);
				rs = ps.executeQuery();
				int residueNum = 0;
				int limitNum = 0;
				while (rs.next()) {
					residueNum = Integer.valueOf(rs.getString("c_residueNum"));
					limitNum = Integer.valueOf(rs.getString("c_limitNum"));
				}
				if (residueNum > 0 && residueNum <= limitNum) {
					try {
						String sql2 = "update  course set c_residueNum=?  where c_no=?";
						ps2 = conn.prepareStatement(sql2);
						// 课程人数加一
						int re = residueNum + 1;
						ps2.setString(1, String.valueOf(re));
						ps2.setString(2, cNo);
						int count = ps2.executeUpdate();
						if (count >= 1) {
							bool = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(rs,ps,conn);
				JDBCUtils.close(ps2,null);
			}
		} else {
			// 发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
		return bool;
	}

}
