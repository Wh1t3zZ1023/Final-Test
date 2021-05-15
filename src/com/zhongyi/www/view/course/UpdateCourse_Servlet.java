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

@WebServlet("/UpdateCourse_Servlet")
public class UpdateCourse_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单属性
		String No = request.getParameter("No");
		List<Course> list = new ArrayList<>();
		// 根据课程编号获得课程分配信息
		list = new ServiceC().seekC("编号", "精确查询", No, list);
		// 把list存在对象中
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("c", list.toArray()[0]);
		request.getRequestDispatcher("/jsp/course/m/UpdateCourse.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String No = ((Course) request.getSession().getAttribute("c")).getNo();
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String credit = request.getParameter("credit");
		// 修改课程分配
		Boolean bool = new ServiceC().updateC(No, time, place, credit);
		if (bool) {
			request.getSession().setAttribute("message", "修改课程分配成功！");
		} else {
			request.getSession().setAttribute("message", "修改课程分配失败！");
		}
		request.getSession().setAttribute("url", "RemoveCourse_Servlet?id=seekall");
		request.getRequestDispatcher("Message.jsp").forward(request, response);
	}
}
