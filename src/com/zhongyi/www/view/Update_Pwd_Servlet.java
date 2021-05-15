package com.zhongyi.www.view;

import com.zhongyi.www.service.ServiceM;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Wh1t3zZ
 */
//注释配置文件
@WebServlet(urlPatterns ="/Update_Pwd_Servlet")
public class Update_Pwd_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//获得用户更改后的信息
		String sort = (String) session.getAttribute("SORT");
		String uname =(String) session.getAttribute("UNAME");
		String password = request.getParameter("password");
				
		String bool=new ServiceM().getUpdatePwd(sort, uname, password);
		if(bool!=null){
			request.getSession().setAttribute("message", "密码修改成功！");
		}
		else {
			request.getSession().setAttribute("message", "密码修改失败！");
		}
		request.getSession().setAttribute("url", "jsp/UpdatePwd.jsp");
		response.sendRedirect("Message.jsp");
	}       
}

