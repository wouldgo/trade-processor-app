package org.wouldgo.processor.confs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * <p>Websocket configuration.</p>
 * <p>This configuration register a STOMP endpoint declaring the usage of sockJS (https://github.com/sockjs),
 * a crossbrowser websocket-like javascript library.</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/market-trade").withSockJS();
	}
}
