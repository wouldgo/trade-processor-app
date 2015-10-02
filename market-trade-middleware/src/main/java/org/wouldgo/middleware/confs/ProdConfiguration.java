package org.wouldgo.middleware.confs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>Production environment configuration.</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@Profile("prod")
@PropertySource("classpath:prod/trade-processor.properties")
public class ProdConfiguration {

}