package yandiansai;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.chaoshi.web.socket.MyWebSocket;

public class Test22 {
	
	@Test
	public void main1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-socket.xml");
		MyWebSocket my = (MyWebSocket)ac.getBean("myWebServer");
		System.out.println("aaa");
	}
}
