package com.zhongyi.www.view;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Wh1t3zZ
 */
public class CharaactorFilter implements Filter {

	String encoding = null;

	public CharaactorFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("初始化");
		encoding = fConfig.getInitParameter("encoding");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		encoding = null;
	}

}
