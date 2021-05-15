package com.zhongyi.www.view;



import com.zhongyi.www.po.User;
import com.zhongyi.www.service.ServiceLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login_Servlet", urlPatterns = "/Login_Servlet") // 注释配置文件
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单中属性值,
		// 获得用户登录类型、用户名、密码
		String userSort = request.getParameter("UserSort");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user = new ServiceLogin().getLogin(userSort, username, pwd);
		if (user != null) {
			String s1 = "管理员";
			String s2 = "学生";
			String s3 = "教师";
			// 用户名
			request.getSession().setAttribute("UNAME", username);
			// 身份
			request.getSession().setAttribute("SORT", userSort);
			if (userSort.equals(s1)) {
				// 用户类型为管理员时转换到M_Home.jsp页面
				request.getRequestDispatcher("/jsp/manager/M_Home.jsp").forward(request, response);
			} else if (userSort.equals(s2)) {
				// 用户类型为学生时转换到R_Home.jsp页面
				request.getRequestDispatcher("/jsp/student/s/S_Home.jsp").forward(request, response);
			} else if (userSort.equals(s3)) {
				// 用户类型为教师时转换到T_Home.jsp页面
				request.getRequestDispatcher("/jsp/teacher/t/T_Home.jsp").forward(request, response);
			}
		} else {// 发出错误提示
			request.getSession().setAttribute("message", "用户名或密码错误，请重新输入！");
			request.getSession().setAttribute("url", "Login.jsp");
			response.sendRedirect("Message.jsp");
		}
	}
}
