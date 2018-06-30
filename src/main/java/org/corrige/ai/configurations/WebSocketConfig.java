package org.corrige.ai.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.corrige.ai.util.ServerConstants;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	 
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
		.addEndpoint(ServerConstants.SERVER_REQUEST + ServerConstants.NOTIFICATION_REQUEST + "/ws")
		.setAllowedOrigins("*")
		.withSockJS();
	}
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(ServerConstants.SERVER_REQUEST)
        		.enableSimpleBroker("/notification_ch");
    }
}
