package com.zhongyi.www.view.course;

import com.zhongyi.www.po.Course;
import com.zhongyi.www.service.ServiceE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyCourse_Servlet")
public class MyCourse_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("seekall")) {
			//获得该学生学号
			String sNo = (String) request.getSession().getAttribute("UNAME");
			List<Course> list = new ArrayList<>();
			// 查询学生的已选的课程
			list = new ServiceE().seekElectC(sNo);
			// 把list储存在request对象中
			request.setAttribute("list", list);
			if(list.size()>0){
				request.getRequestDispatcher("/jsp/course/s/MyCourse.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("message", "你还没有选课！");
				request.getSession().setAttribute("url", "ElectCourse_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			}
		} else if (id.equals("recede")) {
			//退选
			String cNo = request.getParameter("No");
			String sNo = (String) request.getSession().getAttribute("UNAME");
			Boolean bool = new ServiceE().recedeC(cNo, sNo);
			if (bool) {
				request.getSession().setAttribute("message", "退课成功！");
			} else {
				request.getSession().setAttribute("message", "退课失败！");
			}
			request.getSession().setAttribute("url", "MyCourse_Servlet?id=seekall");
			response.sendRedirect("Message.jsp");
		}
	}
}
