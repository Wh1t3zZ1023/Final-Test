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

@WebServlet(name = "RemoveTeacher_Servlet", urlPatterns = "/RemoveTeacher_Servlet")
public class RemoveTeacher_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("seekall")) {
			List<Teacher> list = new ArrayList<>();
			list = new ServiceT().seekAllT(list);
			// 把list储存在request对象中
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/teacher/m/RemoveTeacher.jsp").forward(request, response);
		} else if (id.equals("remove")) {
			// 获得教师工号
			String No = request.getParameter("No");
			// 删除该教师
			Boolean bool = new ServiceT().removeT(No);
			if (bool) {
				request.getSession().setAttribute("message", "删除成功！");
			} else {
				request.getSession().setAttribute("message", "删除失败！");

			}
			request.getSession().setAttribute("url", "RemoveTeacher_Servlet?id=seekall");
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
			List<Teacher> list = new ArrayList<>();
			list = new ServiceT().seekT(select, seek, name, list);
			// 把list存在request对象中
			request.setAttribute("list", list);
			// 判断搜索结果是否为空
			if (list.size() < 1 || list == null) {
				request.getSession().setAttribute("message", "未找到你要查询的内容！");
				request.getSession().setAttribute("url", "RemoveTeacher_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			} else {
				request.getRequestDispatcher("/jsp/teacher/m/RemoveTeacher.jsp").forward(request, response);
			}
		}
	}
}
