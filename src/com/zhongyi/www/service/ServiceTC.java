package com.zhongyi.www.service;

import com.zhongyi.www.dao.GetElective;
import com.zhongyi.www.dao.Grade;
import com.zhongyi.www.dao.SeekElectStudentNo;
import com.zhongyi.www.dao.SeekTeachC;
import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Elective;
import com.zhongyi.www.po.Student;
import com.zhongyi.www.po.Teacher;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Wh1t3zZ
 */
public class ServiceTC {
	// 查询该教师的所有课程

	public List<Course> getTeachC(String tNo) {
		List<Course> list = new ArrayList<>();
		List<Teacher> tList = new ArrayList<>();
		// 根据教师工号获得教师名称
		tList = new ServiceT().seekT("工号", "精确查询", tNo, tList);
		for (int i = 0; i < tList.size(); i++) {
			String name = ((Teacher) tList.toArray()[i]).getName();
			// 根据教师姓名获得该课程信息
			list = new SeekTeachC().seekTeachC(name, list);
		}
		return list;
	}

	// 查询选修该课程的学生

	public List<Student> seekElectS(String cNo) {
		List<Student> list = new ArrayList<>();
		// 根据选课的课程编号获得学号和课程名称
		List<Elective> listsNo = new SeekElectStudentNo().seekElectStudentNo(cNo);
		List<Student> listAll = new ArrayList<>();
		for (int i = 0; i < listsNo.size(); i++) {
			String sNo = ((Elective) listsNo.toArray()[i]).getsNo();
			String cName = ((Elective) listsNo.toArray()[i]).getcName();
			// 根据学号获得学生信息
			listAll = new ServiceS().seekS("学号", "精确查询", sNo, listAll);
			// 把该学生选的课的课程名称，编号放在Student对象中
			Student s = (Student) listAll.toArray()[i];
			s.setCname(cName);
			s.setcNo(cNo);
			list.add(s);
		}
		return list;
	}

	// 根据学号和课程编号获得选课信息Elective对象

	public Elective getElective(String cNo, String sNo) {
		return new GetElective().getElective(cNo, sNo);
	}

	// 评分

	public Boolean grade(String cNo, String sNo, String grade) {
		return new Grade().grade(cNo, sNo, grade);

	}

	// 查询该教师的所有学生

	public List<Student> seekAllMyStudent(String tNo) {
		List<Student> list = new ArrayList<>();
		// 根据教师工号获得课程编号
		List<Course> cList = new ServiceTC().getTeachC(tNo);
		for (int i = 0; i < cList.size(); i++) {
			String cNo = ((Course) cList.toArray()[i]).getNo();
			// 根据课程编号获得选该课的学生信息
			List<Student> sList = new ServiceTC().seekElectS(cNo);
			list.addAll(sList);
		}
		return list;
	}

	// 条件查询该教师的学生

	public List<Student> SeekMyStudent(String tNo, String select, String seek, String name) {
		List<Student> list = new ArrayList<>();
		// 获得该教师的所有学生
		List<Student> allList = new ServiceTC().seekAllMyStudent(tNo);
		List<Student> seekList = new ArrayList<>();
		// 获得条件查询的学生
		seekList = new ServiceS().seekS(select, seek, name, seekList);
		for (int i = 0; i < seekList.size(); i++) {
			for (int j = 0; j < allList.size(); j++) {
				Student s1 = (Student) seekList.toArray()[i];
				Student s2 = (Student) allList.toArray()[j];
				// 根据学号判断该学生是否选了该教师的课
				if (s1.getNo().equals(s2.getNo())) {
					list.add(s2);
				}
			}
		}
		return list;
	}
}
