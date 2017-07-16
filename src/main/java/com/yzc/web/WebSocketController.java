package com.yzc.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yzc.domain.ClientMessage;
import com.yzc.domain.ServiceResponse;

@Controller
public class WebSocketController {
	
	@Autowired  SimpMessagingTemplate  messagingTemplate;
	
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public ServiceResponse say(ClientMessage message) throws InterruptedException{
		
		Thread.sleep(3000);
		return new ServiceResponse("Welcome, "+message.getName()+"!");
	}

	@MessageMapping("/chat")
	public void handleChat(Principal principal,String msg){
		
		if (principal.getName().equals("yzc")) {
			messagingTemplate.convertAndSendToUser("admin", "/queue/notifications",principal.getName()+"-send:"+msg);
		}
		else{
			messagingTemplate.convertAndSendToUser("yzc", "/queue/notifications",principal.getName()+"-send:"+msg);
		}
	}
}
