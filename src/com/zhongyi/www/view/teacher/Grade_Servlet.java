package com.zhongyi.www.view.teacher;

import com.zhongyi.www.po.Elective;
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

@WebServlet("/Grade_Servlet")
public class Grade_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("grade")) {
			// 获得课程编号和学号
			String cNo = request.getParameter("cNo");
			String sNo = request.getParameter("sNo");
			Elective e = new Elective();
			// 根据学号和课程编号获得选课成绩
			e = new ServiceTC().getElective(cNo, sNo);
			request.getSession().setAttribute("e", e);
			request.getRequestDispatcher("/jsp/teacher/t/Grade.jsp").forward(request, response);
		} else if (id.equals("seekall")) {
			// 查询该教师的所有学生
			String tNo = (String) request.getSession().getAttribute("UNAME");
			List<Student> list = new ArrayList<>();
			list = new ServiceTC().seekAllMyStudent(tNo);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/teacher/t/MyStudents.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String id = request.getParameter("id");
		if (id.equals("grade")) {
			Elective e = new Elective();
			e = (Elective) request.getSession().getAttribute("e");
			String cNo = e.getcNo();
			String sNo = e.getsNo();
			String grade = request.getParameter("grade");
			// 评分
			Boolean bool = new ServiceTC().grade(cNo, sNo, grade);
			if (bool) {
				request.getSession().setAttribute("message", "评分成功！");
			} else {
				request.getSession().setAttribute("message", "评分失败！");
			}
			request.getRequestDispatcher("Message_2.jsp").forward(request, response);
		} else if (id.equals("seek")) {
			// 获得表单属性
			String select = request.getParameter("select");
			String seek = request.getParameter("seek");
			String name = request.getParameter("name");
			String tNo = (String) request.getSession().getAttribute("UNAME");
			List<Student> list = new ArrayList<>();
			// 条件查询该教师的学生
			list = new ServiceTC().SeekMyStudent(tNo, select, seek, name);
			if (list.size() > 0) {
				// 把list存在request对象中
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/teacher/t/MyStudents.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("message", "未搜索到结果！");
				request.getSession().setAttribute("url", "Grade_Servlet?id=seekall");
				response.sendRedirect("Message.jsp");
			}
		}
	}

}
