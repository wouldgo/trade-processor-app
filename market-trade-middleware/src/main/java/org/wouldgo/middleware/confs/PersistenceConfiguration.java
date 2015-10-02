package org.wouldgo.middleware.confs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.wouldgo.middleware.entities.TradeMessage;
import org.wouldgo.middleware.repositories.TradeMessageRepository;

/**
 * <p>Persistence configuration.</p>
 * <p>In this scenario was used a derby database.</p>
 * <p>This configuration schema works also on PostgreSQL and Oracle</p>
 *
 * @author "wouldgo"
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( basePackageClasses = TradeMessageRepository.class)
public class PersistenceConfiguration {

	@Autowired
	private Environment env;

	private final static transient String ENTITIES_PACKAGE = TradeMessage.class.getPackage().getName();

	/**
	 * Defines a datasource.
	 *
	 * @return the datasource.
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(this.env.getProperty("jdbc.url"));
		dataSource.setUsername(this.env.getProperty("jdbc.username"));
		dataSource.setPassword(this.env.getProperty("jdbc.password"));
		return dataSource;
	}

	/**
	 * Defines the entity manager.
	 *
	 * @return the entity manager
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Properties jpaProperties = new Properties();
		jpaProperties.put(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, this.env.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put(org.hibernate.cfg.AvailableSettings.CACHE_REGION_FACTORY, SingletonEhCacheRegionFactory.class.getCanonicalName());
		jpaProperties.put("hibernate.cache.use_structured_entries", Boolean.TRUE);
		jpaProperties.put("hibernate.cache.use_second_level_cache", Boolean.TRUE);

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.valueOf(this.env.getProperty("hibernate.ddl_create")).booleanValue());
		vendorAdapter.setShowSql(Boolean.valueOf(this.env.getProperty("hibernate.show_sql")).booleanValue());
		vendorAdapter.getJpaPropertyMap().put("databasePlatform", this.env.getProperty("hibernate.dialect"));

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(this.dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(PersistenceConfiguration.ENTITIES_PACKAGE);
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		factory.setPersistenceProvider(new HibernatePersistenceProvider());

		return factory;
	}

	/**
	 * Defines the transaction manager.
	 *
	 * @return the transaction manager.
	 */
	@Bean
	public PlatformTransactionManager transactionManager()  {
		EntityManagerFactory factory = this.entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}
}
