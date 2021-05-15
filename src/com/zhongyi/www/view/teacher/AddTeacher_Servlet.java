package com.zhongyi.www.view.teacher;

import com.zhongyi.www.service.ServiceT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddTeacher_Servlet", urlPatterns = "/AddTeacher_Servlet")
public class AddTeacher_Servlet extends HttpServlet {
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
		String pwd = request.getParameter("password");
		// 添加教师
		Boolean bool = new ServiceT().addT(No, name, sex, age, collage, pwd);
		if (bool) {
			request.setAttribute("message", "教师用户添加成功！");
		} else {
			request.setAttribute("message", "该用户名已存在，教师用户添加失败！");
		}
		request.getSession().setAttribute("url", "jsp/teacher/m/AddTeacher.jsp");
		request.getRequestDispatcher("Message.jsp").forward(request, response);
	}
}
