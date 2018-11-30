package com.chaoshi.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

//tomcat8�Ժ�Ĭ�ϱ����ʽ��utf-8;   tomcat7֮ǰ�Ķ���iso8859-1,   ���Ĭ������£�tomcatʹ�õĵı��뷽ʽ��iso8859-1
// tomcat7�������ȫվ����
public class CharacterEncodingFilter2 implements Filter {

	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		request.setCharacterEncoding("UTF-8");  //post  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		//chain.doFilter(request, response);
		
		chain.doFilter(new MyRequest(request), response);   
	}
	
	/*��װ
	1.дһ���࣬ʵ���뱻��ǿ������ͬ�Ľӿ�
	2.����һ����������ס����ǿ����
	3.����һ�����췽�������ձ���ǿ����
	4.��������ǿ�ķ���
	5.���ڲ�����ǿ�ķ�����ֱ�ӵ��ñ���ǿ����Ŀ����󣩵ķ���
	 */
	
	class MyRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public String getParameter(String name) {
			
			String value = this.request.getParameter(name);
			if(!request.getMethod().equalsIgnoreCase("get")){ //��post���ͷ���
				return value;
			}
			
			if(value==null){
				return null;
			}
			
			try {//�����get����
				return value = new String(value.getBytes("ISO-8859-1"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		
		
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
