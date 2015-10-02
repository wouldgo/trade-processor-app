package org.wouldgo.common.confs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.wouldgo.common.bus.ApplicationBus;

/**
 * <p>Common module configuration.</p>
 * <p>This provide the components from this module.</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@ComponentScan(basePackageClasses = ApplicationBus.class)
@PropertySource("classpath:common-confs.properties")
public class CommonsConfiguration {

}
