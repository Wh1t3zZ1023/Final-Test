package com.zhongyi.www.dao;

import com.zhongyi.www.po.JudgeSort;
import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class Updatepwd {
	public String update(String userSort, String username, String pwd) {
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
		if (conn != null) {
			// 判断用户身份修改管理员、教师、学生的登录密码
			JudgeSort js = new JudgeSort(userSort);
			String tableName = js.getTableName();
			String name = js.getUname();
			String password = js.getPwd();
			try {
				String sql = "update " + tableName + " set " + password + "=? where " + name + "=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, pwd);
				ps.setString(2, username);
				// 执行更新操作
				int count = ps.executeUpdate();
				if (count >= 0) {
					return "ture";
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
		return null;
	}
}
