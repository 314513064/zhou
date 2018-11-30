package com.chaoshi.web.socket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketPushHandler implements WebSocketHandler{
	
	private static final List<WebSocketSession> users = new ArrayList<>();
	
	//�û�����ϵͳ����
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("�ɹ�������ϵͳ������");
		users.add(session);
	}

	//
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	    //����Ϣ����ת������Ϊ����Ϣ��json���ݣ�������������˷��͸�ĳ���˵���Ϣ��������Ҫ��json��صĹ����ദ��֮���ٷ�װ��TextMessage��
	    //�������û����������Ϣ�ķ�װ��ʽһ����{from:xxxx,to:xxxxx,msg:xxxxx}������������͸�˭��ʲô��Ϣ�ȵ�	    
	    TextMessage msg = (TextMessage)message.getPayload();
	    //�������û�Ⱥ����Ϣ
	    sendMessagesToUsers(msg);
	    //��ָ���û�Ⱥ����Ϣ
	    sendMessageToUser(userId,msg);
		

	}
        
        //��̨������Ϣ������
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

	}

	//�û��˳���Ĵ��������˳�֮��Ҫ���û���Ϣ��websocket��session��remove���������û��ʹ�������״̬�ˣ�Ҳ����ռ��ϵͳ��Դ
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		if(session.isOpen()){
			session.close();
		}
		users.remove(session);
		System.out.println("��ȫ�˳���ϵͳ");
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
	 * �����е��û�������Ϣ
	 */
	public void sendMessagesToUsers(TextMessage message){
		for(WebSocketSession user : users){
			try {
			    //isOpen()���߾ͷ���
				if(user.isOpen()){
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ������Ϣ��ָ�����û�
	 */
	public void sendMessageToUser(String userId,TextMessage message){
		for(WebSocketSession user : users){
			if(user.getAttributes().get(Constants.CURRENT_WEBSOCKET_USER).equals(userId)){
				try {
				    //isOpen()���߾ͷ���
					if(user.isOpen()){
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}