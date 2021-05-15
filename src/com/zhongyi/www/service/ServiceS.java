package com.zhongyi.www.service;

import com.zhongyi.www.dao.*;
import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Student;

import java.util.List;

/**
 * @author Wh1t3zZ
 */
public class ServiceS {
	// 添加学生

	public Boolean addS(String no, String name, String sex, String age, String collage, String department,
			String pwd) {
		// 判断该学生是否添加
		Boolean bool = new SeekExist().seekExist(no, "学生");
		if (bool) {
			bool = false;
		} else {// 添加学生
			bool = new AddS().addS(no, name, sex, age, collage, department, pwd);
		}
		return bool;
	}

	// 查询所有学生信息

	public List<Student> seekAllS(List<Student> list) {
		return new SeekAllS().seekAllS(list);

	}

	// 删除学生信息

	public Boolean removeS(String sNo) {
		//删除学生
		Boolean bool = new RemoveS().removeS(sNo);
		//获得该学生选的课
		List<Course> list=new ServiceE().seekElectC(sNo);
		for(int i=0;i<list.size();i++){
			String cNo=((Course)list.toArray()[i]).getNo();
			// 该课程可选人数加一
			new PlusResidueNum().plusResidueNum(cNo);
			//同时删除该学生的选课记录
			bool=new ServiceE().recedeC(cNo, sNo);
		}
		return bool;
	}

	// 条件查询学生信息

	public List<Student> seekS(String select, String seek, String name, List<Student> list) {
		String s1 = "学号";
		String s2 = "姓名";
		String s3 = "所在学院";
		// 判断select的值
		if (select.equals(s1)) {
			select = "s_no";
		} else if (select.equals(s2)) {
			select = "s_name";
		} else if (select.equals(s3)) {
			select = "s_collage";
		}
		return new SeekS().seekS(select, seek, name, list);
	}

	// 修改学生个人信息

	public Boolean updateStuInfo(String no, String name, String sex, String age, String collage, String department,
			String pwd) {
		return new UpdateS().updateS(no, name, sex, age, collage, department, pwd);

	}
}
