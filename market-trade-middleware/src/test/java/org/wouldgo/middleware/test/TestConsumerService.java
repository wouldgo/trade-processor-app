package org.wouldgo.middleware.test;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wouldgo.common.confs.CommonsConfiguration;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.confs.MiddleWareConfiguration;
import org.wouldgo.middleware.services.ConsumerService;
import org.wouldgo.middleware.test.conf.ConfigurationMock;

/**
 * Tests the {@linkplain ConsumerService} component.
 *
 * @author "wouldgo"
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsConfiguration.class, MiddleWareConfiguration.class, ConfigurationMock.class})
public class TestConsumerService {

	private static final transient Logger logger = LoggerFactory.getLogger(TestConsumerService.class);

	@Autowired
	private ConsumerService consumerService;

	@Test
	public void injectionGoneRigth() {

		if (TestConsumerService.logger.isDebugEnabled()) {

			TestConsumerService.logger.debug("Checking if consumer service is injected");
		}
		Assert.assertNotNull(this.consumerService);
	}

	@Test
	public void consumeElementTest() {

		if (TestConsumerService.logger.isDebugEnabled()) {

			TestConsumerService.logger.debug("calling consume element");
		}

		this.consumerService.consumeElement(new TradeMessage("test-value", "test-value", "test-value", "test-value",
				DateTime.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
	}
}
