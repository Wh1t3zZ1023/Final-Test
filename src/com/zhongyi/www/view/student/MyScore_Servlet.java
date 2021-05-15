package com.zhongyi.www.view.student;

import com.zhongyi.www.po.Elective;
import com.zhongyi.www.service.ServiceE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyScore_Servlet")
public class MyScore_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String sNo = (String) request.getSession().getAttribute("UNAME");
		List<Elective> list = new ArrayList<>();
		// 查询选课成绩
		list = new ServiceE().seekScore(sNo,list);
		request.setAttribute("list", list);
		if (list.size() > 0) {
			request.getRequestDispatcher("/jsp/student/s/MyScore.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("message", "你还没有选课！");
			request.getSession().setAttribute("url", "ElectCourse_Servlet?id=seekall");
			response.sendRedirect("Message.jsp");
		}
	}
}
