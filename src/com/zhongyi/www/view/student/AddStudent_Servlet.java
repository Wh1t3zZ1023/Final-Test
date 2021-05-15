package com.zhongyi.www.view.student;

import com.zhongyi.www.service.ServiceS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddStudent_Servlet", urlPatterns = "/AddStudent_Servlet")
public class AddStudent_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单属性
		String No = request.getParameter("No");
		String name = request.getParameter("username");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String collage = request.getParameter("collage");
		String department = request.getParameter("department");
		String pwd = request.getParameter("password");
		// 添加学生信息
		Boolean bool = new ServiceS().addS(No, name, sex, age, collage, department, pwd);
		if (bool) {
			request.getSession().setAttribute("message", "学生用户添加成功！");
		} else {
			request.getSession().setAttribute("message", "该用户名已存在，学生用户添加失败！");
		}
		request.getSession().setAttribute("url", "jsp/student/m/AddStudent.jsp");
		request.getRequestDispatcher("Message.jsp").forward(request, response);
	}
}
