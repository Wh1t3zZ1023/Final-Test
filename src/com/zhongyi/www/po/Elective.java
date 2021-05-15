package com.zhongyi.www.po;

/**
 * 选课类
 * @author Wh1t3zZ
 */
public class Elective {
	private String cNo;
	private String cName;
	private String sNo;
	private String sName;
	private String grade;
	// 课程编号，课程名称，学生学号，学生姓名，学生成绩

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

}
