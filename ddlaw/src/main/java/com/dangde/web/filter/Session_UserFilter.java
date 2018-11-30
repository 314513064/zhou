package com.dangde.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dangde.domain.User;

public class Session_UserFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url=request.getRequestURI();
		//检查用户是否已登陆
		//如果登入了
		
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			chain.doFilter(request, response);
			return;
		}
		
		
		if(url.startsWith("/ddlaw/login")||url.startsWith("/ddlaw/register")||url.startsWith("/ddlaw/index")||url.equals("/ddlaw/")){
			chain.doFilter(request, response);
			return;
		}
		//去除静态文件过滤
		if(url.contains("/js/")||url.contains("/images/")||url.contains("/lib/")||url.contains("/css/")||url.contains("/upload/")){
			chain.doFilter(request, response);
			return;
		}
		
		
		//移动端不设置session		
		if(url.startsWith("/ddlaw/api/")){
			chain.doFilter(request, response);
			return;
		}
		
		//移动端设置session	
//		if(url.startsWith("/ddlaw/api/login")||url.startsWith("/ddlaw/api/register")){
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		//移动端没有登入
//		if(url.startsWith("/ddlaw/api/")){
//			
//	    	
//			Map<String,String> code = new HashMap<String,String>();	
//			Gson gson = new Gson();
//			code.put("code","2000");//未登入						
//			response.getWriter().write(gson.toJson(code));
//			return;
//		}
		
		//没有登入		
			response.sendRedirect("/ddlaw/logins.html");
		
		

	}
	

	
	
	

	public void destroy() {
		// TODO Auto-generated method stub

	}

	

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
