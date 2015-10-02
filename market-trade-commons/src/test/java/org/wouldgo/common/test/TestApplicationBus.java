package org.wouldgo.common.test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wouldgo.common.bus.ApplicationBus;
import org.wouldgo.common.confs.CommonsConfiguration;
import org.wouldgo.common.dto.TradeMessage;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * Tests the {@linkplain ApplicationBus} component.
 *
 * @author "wouldgo"
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CommonsConfiguration.class)
public class TestApplicationBus {

	static final transient Logger logger = LoggerFactory.getLogger(TestApplicationBus.class);
	private static final int NUMBER_OF_EXECUTOR_THREAD = 200;
	private static ExecutorService executorService;

	@Autowired
	ApplicationBus bus;

	@BeforeClass
	public static void onlyOnce() {

		TestApplicationBus.executorService = Executors.newFixedThreadPool(TestApplicationBus.NUMBER_OF_EXECUTOR_THREAD);
	}

	@Test
	public void injectionGoneRigth() {

		if (TestApplicationBus.logger.isDebugEnabled()) {

			TestApplicationBus.logger.debug("Checking if application bus is injected");
		}
		Assert.assertNotNull(this.bus);
	}

	@Test
	public void publishingTest() {

		if (TestApplicationBus.logger.isDebugEnabled()) {

			TestApplicationBus.logger.debug("Checking application bus subscription & publishing");
		}
		SubscriberTestClass aSubscriber = new SubscriberTestClass();
		this.bus.register(aSubscriber);

		if (TestApplicationBus.logger.isDebugEnabled()) {

			TestApplicationBus.logger.debug("Put subscriber {}", aSubscriber);
		}
		for (int i = 0; i < TestApplicationBus.NUMBER_OF_EXECUTOR_THREAD; i += 1) {

			EventPublisher publisher = new EventPublisher();
			if (TestApplicationBus.logger.isDebugEnabled()) {

				TestApplicationBus.logger.debug("Created a publisher {}", publisher);
			}
			TestApplicationBus.executorService.submit(publisher);
		}
	}
	private class EventPublisher implements Runnable {

		EventPublisher() {
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {

			if (TestApplicationBus.logger.isDebugEnabled()) {

				TestApplicationBus.logger.debug("Ready to publish");
			}
			TradeMessage aGeneratedTradeMessage = new TradeMessage("test-user", "test-value",
					"test-value", "test-value", DateTime.now(), BigDecimal.TEN, BigDecimal.TEN, BigDecimal.ZERO);
			TestApplicationBus.this.bus.publish(aGeneratedTradeMessage);
		}
	}

	private static class SubscriberTestClass {

		SubscriberTestClass() {
		}

		@Subscribe
		@AllowConcurrentEvents
		public static void subscribeToEvent(TradeMessage aTradeMessage) {

			if (TestApplicationBus.logger.isDebugEnabled()) {

				TestApplicationBus.logger.debug("Arrived an Event {}", aTradeMessage);
			}
			Assert.assertNotNull(aTradeMessage);
			Assert.assertEquals(aTradeMessage.getUserId(), "test-user");
			Assert.assertEquals(aTradeMessage.getCurrencyFrom(), "test-value");
			Assert.assertEquals(aTradeMessage.getCurrencyTo(), "test-value");
			Assert.assertEquals(aTradeMessage.getAmountSell(), BigDecimal.TEN);
			Assert.assertEquals(aTradeMessage.getAmountBuy(), BigDecimal.TEN);
			Assert.assertEquals(aTradeMessage.getRate(), BigDecimal.ZERO);
		}
	}
}
