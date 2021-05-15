package com.zhongyi.www.po;

/**
 * 学生类
 * 用户属性：学号，姓名，性别，年龄，所在学院，专业
 * 该学生选的其一课程名称，课程编号
 * @author Wh1t3zZ
 */
public class Student {
	private String no;
	private String name;
	private String sex;
	private String age;
	private String collage;
	private String department;
	private String cname;
	private String cNo;

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCollage() {
		return collage;
	}

	public void setCollage(String collage) {
		this.collage = collage;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [No=" + no + ", name=" + name + ", sex=" + sex + ", age=" + age + ", collage=" + collage
				+ ", department=" + department + ", cname=" + cname + "]";
	}
}
