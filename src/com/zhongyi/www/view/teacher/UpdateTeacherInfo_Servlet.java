package com.zhongyi.www.view.teacher;

import com.zhongyi.www.po.Teacher;
import com.zhongyi.www.service.ServiceT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateTeacherInfo_Servlet")
public class UpdateTeacherInfo_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String No = ((Teacher) request.getSession().getAttribute("t")).getNo();
		String name = request.getParameter("username");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String collage = request.getParameter("collage");
		String pwd = request.getParameter("password");
		// 修改教师信息
		Boolean bool = new ServiceT().updateTeacherInfo(No, name, sex, age, collage, pwd);
		if (bool != null) {
			request.getSession().setAttribute("message", "修改成功！");
		} else {
			request.getSession().setAttribute("message", "修改失败！");
		}
		request.getSession().setAttribute("url", "TeacherInfo_Servlet?id=update");
		response.sendRedirect("Message.jsp");
	}
}
