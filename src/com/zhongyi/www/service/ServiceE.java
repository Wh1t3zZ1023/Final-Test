package com.zhongyi.www.service;


import com.zhongyi.www.dao.*;
import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Elective;
import com.zhongyi.www.po.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class ServiceE {


	//学生选课

	public String electC(String cNo, String sNo, String message) {
		List<Course> list = new ArrayList<>();
		list = new ServiceC().seekC("编号", "精确查询", cNo, list);
		// 根据课程编号获得课程名称
		String cname = ((Course) list.toArray()[0]).getName();
		List<Student> list2 = new ArrayList<>();
		list2 = new ServiceS().seekS("学号", "精确查询", sNo, list2);
		// 根据学号获得姓名
		String sName = ((Student) list2.toArray()[0]).getName();
		Boolean bool = new SeekElected().seekExist(cNo, sNo);
		// 判断该课是否选过
		if (bool) {
			message = "该课程已选过，请选其它课";
			//bool = false;
		} else {
			Boolean bool2 = new MinusResidueNum().seekResidueNum(cNo);
			// 判断可选人数，并修改
			if (bool2) {
				new ElectC().electC(cNo, cname, sNo, sName);
				// 根据课程编号和学号选课
			} else {
				message = "该课程选修学生已满，请选其它课";
			}
		}
		return message;
	}

	//查询学生的已选的课程

	public List<Course> seekElectC(String sNo) {
		List<Course> list = new ArrayList<>();
		List<Course> list2 = new ArrayList<>();
		// 根据学号获得已选课程的课程编号
		list2 = new SeekElectC().seekElectC(sNo, list2);
		List<Course> list3 = new ArrayList<>();
		for (int i = 0; i < list2.size(); i++) {
			String cNo = ((Course) list2.toArray()[i]).getNo();
			// 获得该课程的详细信息
			list3 = new ServiceC().seekC("编号", "精确查询", cNo, list3);
			Course c = (Course) list3.toArray()[i];
			list.add(c);
		}
		return list;
	}

	//退选课程

	public Boolean recedeC(String cNo, String sNo) {
		// 该课程可选人数加一
		Boolean bool = new PlusResidueNum().plusResidueNum(cNo);
		if (bool) {
			bool = new RecedeC().recedeC(cNo, sNo);
		}
		return bool;
	}

	//查询选课成绩

	public List<Elective> seekScore(String sNo, List<Elective> list) {
		list = new SeekScore().seekScore(sNo);
		return list;
	}
}
