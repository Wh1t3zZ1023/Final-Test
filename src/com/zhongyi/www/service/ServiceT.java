package com.zhongyi.www.service;

import com.zhongyi.www.dao.*;
import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Teacher;

import java.util.List;


/**
 * @author Wh1t3zZ
 */
public class ServiceT {
	// 添加教师用户

	public Boolean addT(String no, String name, String sex, String age, String collage, String pwd) {
		// 判断该教师是否添加
		Boolean bool = new SeekExist().seekExist(no, "教师");
		if (bool) {
			bool = false;
		} else {// 添加教师
			bool = new AddT().addS(no, name, sex, age, collage, pwd);
		}
		return bool;
	}

	// 查询所有教师信息

	public List<Teacher> seekAllT(List<Teacher> list) {
		return new SeekAllT().seekAllT(list);
	}

	// 删除教师信息

	public Boolean removeT(String tNo) {
		Boolean bool = false;
		//同时删除该教师教的课程和使选这些课学生退选
		List<Course> list=new ServiceTC().getTeachC(tNo);
		for(int i=0;i<list.size();i++){
			String cNo=((Course)list.toArray()[i]).getNo();
			//删除课程
			bool=new ServiceC().removeC(cNo);
		}
		if(bool){
			//删除教师
			bool = new RemoveT().removeT(tNo);
		}
		return bool;
	}

	// 条件查询教师信息

	public List<Teacher> seekT(String select, String seek, String name, List<Teacher> list) {
		String s1 = "工号";
		String s2 = "姓名";
		String s3 = "所在学院";
		if (select.equals(s1)) {
			// 判断select的值
			select = "T_No";
		} else if (select.equals(s2)) {
			select = "T_Name";
		} else if (select.equals(s3)) {
			select = "T_Collage";
		}
		return new SeekT().seekT(select, seek, name, list);
	}

	// 修改教师个人信息

	public Boolean updateTeacherInfo(String no, String name, String sex, String age, String collage, String pwd) {
		return new UpdateTInfo().updateTInfo(no, name, sex, age, collage, pwd);
	}

}
