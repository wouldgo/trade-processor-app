package org.wouldgo.common.bus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.AsyncEventBus;

/**
 * <p>The default implementation for the application bus.</p>
 * <p>This implementation uses the {@linkplain AsyncEventBus} from google guava.</p>
 *
 * @author "wouldgo"
 *
 */
@Component
final class ApplicationBusImpl implements ApplicationBus {

	private static final transient Logger logger = LoggerFactory.getLogger(ApplicationBusImpl.class);
	private AsyncEventBus eventBus;

	@PostConstruct
	private void postConstruct() {

		try {

			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			this.eventBus = new AsyncEventBus("async-application-bus", cachedThreadPool);
		} catch (NumberFormatException nfe) {

			throw new IllegalStateException(nfe);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.wouldgo.common.bus.ApplicationBus#publish()
	 */
	@Override
	public <T> void publish(T event) {

		if (ApplicationBusImpl.logger.isDebugEnabled()) {

			ApplicationBusImpl.logger.debug("Event {} published", event);
		}
		this.eventBus.post(event);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.wouldgo.common.bus.ApplicationBus#register()
	 */
	@Override
	public <T> void register(T subscriber) {

		this.eventBus.register(subscriber);
		if (ApplicationBusImpl.logger.isDebugEnabled()) {

			ApplicationBusImpl.logger.debug("Subscriber {} registered", subscriber);
		}
	}
}
