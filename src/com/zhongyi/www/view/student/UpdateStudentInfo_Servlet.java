package com.zhongyi.www.view.student;

import com.zhongyi.www.po.Student;
import com.zhongyi.www.service.ServiceS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateStudentInfo_Servlet")
public class UpdateStudentInfo_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得学号
		String No = ((Student) request.getSession().getAttribute("s")).getNo();
		String name = request.getParameter("username");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String collage = request.getParameter("collage");
		String department = request.getParameter("department");
		String pwd = request.getParameter("password");
		// 修改学生信息
		Boolean bool = new ServiceS().updateStuInfo(No, name, sex, age, collage, department, pwd);
		if (bool != null) {
			request.getSession().setAttribute("message", "修改成功！");
		} else {
			request.getSession().setAttribute("message", "修改失败！");
		}
		request.getSession().setAttribute("url", "StudentInfo_Servlet?id=update");
		response.sendRedirect("Message.jsp");
	}

}
