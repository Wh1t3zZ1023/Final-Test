package com.zhongyi.www.view.course;

import com.zhongyi.www.po.Course;
import com.zhongyi.www.po.Student;
import com.zhongyi.www.service.ServiceTC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyTeachCourse_Servlet")
public class MyTeachCourse_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("seekmine")) {
			// 获得该教师工号
			String tNo = (String) request.getSession().getAttribute("UNAME");
			List<Course> list = new ArrayList<>();
			// 查询该教师的所有课程
			list = new ServiceTC().getTeachC(tNo);
			// 把list储存在request对象中
			request.setAttribute("list", list);
			if (list.size() > 0) {
				request.getRequestDispatcher("/jsp/teacher/t/MyTeachCourse.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("message", "没有你的课程！");
				request.getSession().setAttribute("url", "");
				response.sendRedirect("Message.jsp");
			}
		} else if (id.equals("student")) {
			// 获得课程编号
			String cNo = request.getParameter("No");
			List<Student> list = new ArrayList<>();
			// 查询选修该课程的学生
			list = new ServiceTC().seekElectS(cNo);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/teacher/t/MyStudents.jsp").forward(request, response);
		}
	}
}
