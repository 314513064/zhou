package com.chaoshi.web.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class MyWebSocket1 extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	
	{
		System.out.println("正在创建MyWebSocket1");
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(WebSocketPushHandler(),"/webSocketServer").addInterceptors(new MyWebSocketInterceptor());
        registry.addHandler(WebSocketPushHandler(), "/sockjs/webSocketServer").addInterceptors(new MyWebSocketInterceptor())
                .withSockJS();
	}

    @Bean	
    public WebSocketHandler WebSocketPushHandler(){
        return new WebSocketPushHandler();
    }
}
