package com.zhongyi.www.view.student;

import com.zhongyi.www.po.Student;
import com.zhongyi.www.service.ServiceS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/StudentInfo_Servlet")
public class StudentInfo_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		// 获得该学生的学号
		String No = (String) request.getSession().getAttribute("UNAME");
		List<Student> list = new ArrayList<>();
		// 根据学号查询学生信息
		list = new ServiceS().seekS("学号", "精确查询", No, list);
		Student s = (Student) list.toArray()[0];
		// 把list储存在request对象中
		request.getSession().setAttribute("s", s);
		if (id.equals("info")) {
			// 转到StudentInfo.jsp页面
			request.getRequestDispatcher("/jsp/student/s/StudentInfo.jsp").forward(request, response);
		} else if (id.equals("update")) {
			// 转到UpdateStudentInfo.jsp页面
			request.getRequestDispatcher("/jsp/student/s/UpdateStudentInfo.jsp").forward(request, response);
		}

	}
}
