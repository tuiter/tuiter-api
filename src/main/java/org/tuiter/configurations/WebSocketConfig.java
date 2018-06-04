package org.tuiter.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.tuiter.util.ServerConstants;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/notif");
		config.setApplicationDestinationPrefixes("/tuitersocket");
	}
 
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
		.addEndpoint(ServerConstants.SERVER_REQUEST + ServerConstants.NOTIFICATION_REQUEST + "/websock")
		.setAllowedOrigins("*")
		.withSockJS();
	}
	
}
