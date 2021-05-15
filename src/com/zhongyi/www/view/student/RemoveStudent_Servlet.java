package com.zhongyi.www.view.student;

import com.zhongyi.www.po.Student;
import com.zhongyi.www.service.ServiceS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RemoveStudent_Servlet", urlPatterns = "/RemoveStudent_Servlet")
public class RemoveStudent_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String elect = request.getParameter("id");
		if (elect.equals("seekall")) {
			List<Student> list = new ArrayList<>();
			// 查询所有学生信息
			list = new ServiceS().seekAllS(list);
			// 把list储存在request对象中
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/student/m/RemoveStudent.jsp").forward(request, response);
		} else if (elect.equals("remove")) {
			String No = request.getParameter("No");
			// 删除学生信息
			Boolean bool = new ServiceS().removeS(No);
			if (bool) {
				request.getSession().setAttribute("message", "删除成功！");
			} else {
				request.getSession().setAttribute("message", "删除失败！");

			}
			request.getSession().setAttribute("url", "RemoveStudent_Servlet?id=seekall");
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
			List<Student> list = new ArrayList<>();
			// 条件查询学生信息
			list = new ServiceS().seekS(select, seek, name, list);
			// 把list存在request对象中
			request.setAttribute("list", list);
			// 判断搜索结果是否为空
			if (list.size() < 1 || list == null) {
				request.getSession().setAttribute("message", "未找到你要查询的内容！");
				request.getSession().setAttribute("url", "RemoveStudent_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			} else {
				request.getRequestDispatcher("/jsp/student/m/RemoveStudent.jsp").forward(request, response);
			}
		}

	}
}
