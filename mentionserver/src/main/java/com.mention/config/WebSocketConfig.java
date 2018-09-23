package com.mention.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class  WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // front broker registration to send messages to front
    config.enableSimpleBroker("/queue");
    // back endpoint prefix to obtain messages from front
    config.setApplicationDestinationPrefixes("/back");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // back registration to obtain messages from front
    registry
        .addEndpoint("/ws_0001")
        .setAllowedOrigins("*")
        .withSockJS();
  }

}
