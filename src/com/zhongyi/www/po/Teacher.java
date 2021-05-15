package com.zhongyi.www.po;

/**
 *  教师类
 *  用户属性：工号，姓名，性别，年龄，所在学院
 * @author Wh1t3zZ
 */
public class Teacher {
	private String no;
	private String name;
	private String sex;
	private String age;
	private String collage;

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
}
