package com.chaoshi.web.socket;

import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class MyWebSocketInterceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		System.out.println("after Handshake");
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("before Handshake");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest  = (ServletServerHttpRequest) request;
			HttpServletRequest httpRequest = servletRequest.getServletRequest();
			//Constants.CURRENT_USER这个是我定义的常量，是request域的key，通过key就可以获取到用户信息了
			TsUser user = (TsUser)httpRequest.getAttribute(Constants.CURRENT_USER);
			//Constants.CURRENT_WEBSOCKET_USER也是常量，用来存储WebsocketSession的key值
			attributes.put(Constants.CURRENT_WEBSOCKET_USER,user.getUserid());
		}
		return false;
	}

}
