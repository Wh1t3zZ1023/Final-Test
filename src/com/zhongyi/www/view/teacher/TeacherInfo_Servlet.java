package com.zhongyi.www.view.teacher;

import com.zhongyi.www.po.Teacher;
import com.zhongyi.www.service.ServiceT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TeacherInfo_Servlet")
public class TeacherInfo_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		// 获得教师工号
		String No = (String) request.getSession().getAttribute("UNAME");
		List<Teacher> list = new ArrayList<>();
		// 获得教师信息
		list = new ServiceT().seekT("工号", "精确查询", No, list);
		Teacher t = (Teacher) list.toArray()[0];
		// 把list储存在request对象中
		request.getSession().setAttribute("t", t);
		if (id.equals("info")) {
			request.getRequestDispatcher("/jsp/teacher/t/TeacherInfo.jsp").forward(request, response);
		} else if (id.equals("update")) {
			request.getRequestDispatcher("/jsp/teacher/t/UpdateTeacherInfo.jsp").forward(request, response);
		}
	}
}
