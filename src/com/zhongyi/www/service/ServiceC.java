package com.zhongyi.www.service;


import com.zhongyi.www.dao.*;
import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Student;

import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class ServiceC {
	// 添加课程

	public Boolean addC(String no, String name, String time, String place, String credit, String teacher) {
		// 判断该课程是否已经存在，保证课程编号唯一性
		Boolean bool = new SeekExist().seekExist(no,"课程");
		if (bool) {
			bool = false;
		} else {
			// 添加课程
			bool = new AddC().addC(no, name, time, place, credit, teacher);
		}
		return bool;
	}

	// 查询所有课程

	public List<Course> seekAllC(List<Course> list) {
		return new SeekAllC().seekAllC(list);

	}

	// 删除课程

	public Boolean removeC(String cNo) {
		Boolean bool = false;
		List<Student> list = new ServiceTC().seekElectS(cNo);
		//同时使学生退选该课
		for(int i = 0;i < list.size(); i++){
			String sNo=((Student)list.toArray()[i]).getNo();
			//通过学号删除学生选修该课的记录
			bool=new ServiceE().recedeC(cNo, sNo);
		}
		if(bool){
			//删除该课程
			bool = new RemoveC().removeC(cNo);
		}
		return bool;
	}

	//条件查询课程

	public List<Course> seekC(String select, String seek, String name, List<Course> list) {
		String s1 = "编号";
		String s2 = "名称";
		String s3 = "授课教师";
		if (select.equals(s1)) {
			// 判断select的值
			select = "c_no";
		} else if (select.equals(s2)) {
			select = "c_name";
		} else if (select.equals(s3)) {
			select = "c_teacher";
		}
		return new SeekC().seekC(select, seek, name, list);

	}

	//修改课程

	public Boolean updateC(String no, String time, String place, String credit) {
		return new UpdateC().updateC(no, time, place, credit);

	}
}
