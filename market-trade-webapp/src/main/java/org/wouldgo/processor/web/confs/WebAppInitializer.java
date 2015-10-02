package org.wouldgo.processor.web.confs;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.wouldgo.processor.confs.Environment;
import org.wouldgo.processor.confs.ProcessorConfiguration;
import org.wouldgo.processor.web.filters.CORSFilter;

/**
 *	Interface to be implemented in Servlet 3.0+ environments in order to configure the
 * {@link ServletContext} programmatically -- as opposed to (or possibly in conjunction
 * with) the traditional {@code web.xml}-based approach.
 *
 * @see WebAppInitializer
 *
 * @author "wouldgo"
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	private static final Environment currentEnv;

	static {

		Properties properties = new Properties();

		try (InputStream marketTradeProp = WebAppInitializer.class.getClassLoader().getResourceAsStream("market-trade-webapp.properties")) {

			properties.load(marketTradeProp);
		} catch (IOException e) {

			throw new IllegalStateException(e);
		}

		currentEnv = Environment.valueOf(properties.getProperty("target.env"));
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(final ServletContext context) throws ServletException {

		try (AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext()) {
			switch (WebAppInitializer.currentEnv) {
				case PROD:

					applicationContext.getEnvironment().setActiveProfiles("prod");
					break;

				case DEV:

					applicationContext.getEnvironment().setActiveProfiles("dev");
					break;

				default:
					break;
			}
			applicationContext.setConfigLocation(ProcessorConfiguration.class.getPackage().getName());
			DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

			ServletRegistration.Dynamic appServlet = context.addServlet("app-servlet", dispatcherServlet);
			appServlet.setAsyncSupported(true);
			appServlet.setLoadOnStartup(1);
			appServlet.addMapping("/*");

			FilterRegistration.Dynamic corsFilter = context.addFilter("cors-filter", CORSFilter.class);
			corsFilter.setAsyncSupported(true);
			corsFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

			context.addListener(new ContextLoaderListener(applicationContext));
		}
	}
}