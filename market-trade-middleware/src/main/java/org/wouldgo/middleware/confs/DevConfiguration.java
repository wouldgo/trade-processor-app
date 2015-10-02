package org.wouldgo.middleware.confs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>Development environment configuration.</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@Profile("dev")
@PropertySource("classpath:dev/trade-processor.properties")
public class DevConfiguration {

}
