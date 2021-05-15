package com.zhongyi.www.dao;

import com.zhongyi.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Wh1t3zZ
 */
public class UpdateC {
	public Boolean updateC(String no, String time, String place, String credit){
		// 连接数据库
		(new JDBCUtils()).getLink();
		// 得到连接数据库的Connection对象
		Connection conn = JDBCUtils.conn;
		PreparedStatement ps = null;
        Boolean bool=false;
        if (conn != null) {
			try {
				String sql="update  course set c_time=?,c_place=?,c_credit=? where c_no=? ";
				ps = conn.prepareStatement(sql);
				//对SQL语句中的参数动态赋值
				ps.setString(1,time);
				ps.setString(2,place);
				ps.setString(3,credit);
				ps.setString(4,no);
				//执行更新操作
				int count= ps.executeUpdate();					
				if(count>=1){
					bool=true;
				}										
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//释放资源
				JDBCUtils.close(ps,conn);
			}
		} else {
			//发送数据库连接错误提示信息
			System.out.println("数据库连接错误！");
		}
        return bool;
	}

}
