package com.zhongyi.www.view.manager;

import com.zhongyi.www.service.ServiceM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddManager_Servlet", urlPatterns = "/AddManager_Servlet")
public class AddManager_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取表单中属性值		
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		// 添加管理员
		Boolean bool = new ServiceM().addM(username, pwd);
		if (bool) {
			request.getSession().setAttribute("message", "添加管理员成功！");
		} else {
			request.getSession().setAttribute("message", "该用户名已存在，添加管理员失败！");
		}
		request.getSession().setAttribute("url", "jsp/manager/AddManager.jsp");
		request.getRequestDispatcher("Message.jsp").forward(request, response);
	}
}
