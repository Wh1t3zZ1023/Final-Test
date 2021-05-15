package com.zhongyi.www.view.course;

import com.zhongyi.www.service.ServiceC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCourse_Servlet", urlPatterns = "/AddCourse_Servlet")
public class AddCourse_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单中属性值		
		String name = request.getParameter("name");
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String credit = request.getParameter("credit");
		String teacher = request.getParameter("teacher");
		String no = request.getParameter("no");
		Boolean bool = new ServiceC().addC(no, name, time, place, credit, teacher);
		// 分配课程
		if (bool) {
			request.getSession().setAttribute("message", "添加课程分配成功！");
		} else {
			request.getSession().setAttribute("message", "该课程编号已存在，添加课程分配失败！");
		}
		request.getSession().setAttribute("url", "jsp/course/m/AddCourse.jsp");
		request.getRequestDispatcher("Message.jsp").forward(request, response);
	}
}
