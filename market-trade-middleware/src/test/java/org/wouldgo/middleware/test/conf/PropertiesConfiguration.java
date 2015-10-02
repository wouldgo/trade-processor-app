package org.wouldgo.middleware.test.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * Brings the test properties.
 *
 * @author "wouldgo"
 *
 */
@Configuration
@PropertySource("classpath:trade-processor.properties")
public class PropertiesConfiguration {

}
