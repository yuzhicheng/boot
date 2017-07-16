package com.yzc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
// 开启使用STOMP协议来传输基于代理（Message Broker）的消息
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/endpointYzc").withSockJS();
		registry.addEndpoint("/endpointChat").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){
		
		registry.enableSimpleBroker("/topic","/queue");
	}
}
