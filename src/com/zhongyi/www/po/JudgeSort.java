package com.zhongyi.www.po;

/**
 *判断用户身份
 * @author Wh1t3zZ
 */
public class JudgeSort {
	private String sort;
	private String tableName;
	private String uname;
	private String pwd;

	public JudgeSort(String userSort) {
		String s1 = "管理员";
		String s2 = "教师";
		String s3 = "学生";

		sort = userSort;
		if (s1.equals(sort)) {
			tableName = "manager";
			uname = "m_name";
			pwd = "m_pwd";
		} else if (s2.equals(sort)) {
			tableName = "teacher";
			uname = "t_no";
			pwd = "t_pwd";
		} else if (s3.equals(sort)) {
			tableName = "student";
			uname = "s_no";
			pwd = "s_pwd";
		}
	}

	public String getTableName() {
		return tableName;
	}

	public String getUname() {
		return uname;
	}

	public String getPwd() {
		return pwd;
	}
}
