package com.zhongyi.www.view.course;

import com.zhongyi.www.po.Course;
import com.zhongyi.www.service.ServiceC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RemoveCourse_Servlet", urlPatterns = "/RemoveCourse_Servlet")
public class RemoveCourse_Servlet extends HttpServlet {
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
			request.getRequestDispatcher("/jsp/course/m/RemoveCourse.jsp").forward(request, response);
		} else if (id.equals("remove")) {
			// 获得课程编号
			String No = request.getParameter("No");
			// 删除课程分配
			Boolean bool = new ServiceC().removeC(No);
			if (bool) {
				request.getSession().setAttribute("message", "删除成功！");
			} else {
				request.getSession().setAttribute("message", "删除失败！");

			}
			request.getSession().setAttribute("url", "RemoveCourse_Servlet?id=seekall");
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
			list = new ServiceC().seekC(select, seek, name, list);
			// 把list存在request对象中
			request.setAttribute("list", list);
			// 判断搜索结果是否为空
			if (list.size() < 1 || list == null) {
				request.getSession().setAttribute("message", "未找到你要查询的内容！");
				request.getSession().setAttribute("url", "RemoveCourse_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			} else {
				request.getRequestDispatcher("/jsp/course/m/RemoveCourse.jsp").forward(request, response);
			}
		}
	}
}
