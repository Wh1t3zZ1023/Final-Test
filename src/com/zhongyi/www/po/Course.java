package com.zhongyi.www.po;

/**课程类
 * @author Wh1t3zZ
 */
public class Course {
	private String no;
	private String name;
	private String time;
	private String place;
	private String credit;
	private String teacher;
	private String limitNum;
	private String residueNum;
	// 编号,课程名称，上课时间，上课地点，学分，授课教师，限制人数，可选人数

	public String getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(String limitNum) {
		this.limitNum = limitNum;
	}

	public String getResidueNum() {
		return residueNum;
	}

	public void setResidueNum(String residueNum) {
		this.residueNum = residueNum;
	}

	public String getName() {
		return name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Course [No=" + no + ", name=" + name + ", time=" + time + ", place=" + place + ", credit=" + credit
				+ ", teacher=" + teacher + "]";
	}

}
