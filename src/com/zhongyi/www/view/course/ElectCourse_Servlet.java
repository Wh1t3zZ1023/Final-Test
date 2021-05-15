package com.zhongyi.www.view.course;

import com.zhongyi.www.po.Course;
import com.zhongyi.www.service.ServiceC;
import com.zhongyi.www.service.ServiceE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ElectCourse_Servlet")
public class ElectCourse_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("seekall")) {
			// 查询所有课程分配
			List<Course> list = new ArrayList<>();
			list = new ServiceC().seekAllC(list);
			// 把list储存在request对象中
			request.setAttribute("list", list);
			// 转到/AllBookInfo.jsp页面
			request.getRequestDispatcher("/jsp/course/s/ElectCourse.jsp").forward(request, response);
		} else if (id.equals("elect")) {
			// 选课
			String cNo = request.getParameter("No");
			String sNo = (String) request.getSession().getAttribute("UNAME");
			String message = "";
			message = new ServiceE().electC(cNo, sNo, message);
			if (message == null || message.equals("")) {
				request.getSession().setAttribute("message", "选课成功！");
			} else {
				request.getSession().setAttribute("message", message);

			}
			request.getSession().setAttribute("url", "ElectCourse_Servlet?id=seekall");
			response.sendRedirect("Message.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得表单属性
		String select = request.getParameter("select");
		String seek = request.getParameter("seek");
		String name = request.getParameter("name");
		if (name != null || name == "") {
			List<Course> list = new ArrayList<>();
			// 条件查询课程分配
			list = new ServiceC().seekC(select, seek, name, list);
			// 把list存在request对象中
			request.setAttribute("list", list);
			// 判断搜索结果是否为空
			if (list.size() < 1 || list == null) {
				request.getSession().setAttribute("message", "未找到你要查询的内容！");
				request.getSession().setAttribute("url", "ElectCourse_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			} else {
				request.getRequestDispatcher("/jsp/course/s/ElectCourse.jsp").forward(request, response);
			}
		}
	}
}
