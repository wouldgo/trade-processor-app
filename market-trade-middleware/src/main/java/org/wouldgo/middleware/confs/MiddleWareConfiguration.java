package org.wouldgo.middleware.confs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.wouldgo.middleware.services.ConsumerService;

/**
 * <p>Middleware module configuration.</p>
 * <p>This provide the components from this module.</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@ComponentScan(basePackageClasses = ConsumerService.class)
public class MiddleWareConfiguration {

}
