package org.wouldgo.middleware.test;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wouldgo.common.confs.CommonsConfiguration;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.confs.MiddleWareConfiguration;
import org.wouldgo.middleware.services.TradeMessagesService;
import org.wouldgo.middleware.test.conf.ConfigurationMock;

/**
 * Tests the {@linkplain TradeMessagesService} component.
 * @author "wouldgo"
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsConfiguration.class, MiddleWareConfiguration.class, ConfigurationMock.class})
public class TestTradeMessageService {

	private static final transient Logger logger = LoggerFactory.getLogger(TestTradeMessageService.class);

	@Autowired
	private TradeMessagesService tradeMessage;

	@Test
	public void injectionGoneRigth() {

		if (TestTradeMessageService.logger.isDebugEnabled()) {

			TestTradeMessageService.logger.debug("Checking if trade message service is injected");
		}
		Assert.assertNotNull(this.tradeMessage);
	}

	@Test
	public void get100TradeMessages() {

		if (TestTradeMessageService.logger.isDebugEnabled()) {

			TestTradeMessageService.logger.debug("Checking if the trade message service - getLatestHundredTradeMessages works");
		}
		Collection<TradeMessage> latestHundredTradeMessages = this.tradeMessage.getLatestHundredTradeMessages();
		Assert.assertNotNull(latestHundredTradeMessages);
		Assert.assertTrue(latestHundredTradeMessages.size() == 3);
	}

	@Test
	public void countNations() {

		if (TestTradeMessageService.logger.isDebugEnabled()) {

			TestTradeMessageService.logger.debug("Checking if the trade message service - getNationThatOriginateTradeMessages works");
		}

		Collection<NationCounter> nationThatOriginateTradeMessages = this.tradeMessage.getNationThatOriginateTradeMessages();
		Assert.assertNotNull(nationThatOriginateTradeMessages);
		Assert.assertTrue(nationThatOriginateTradeMessages.size() == 1);
	}

	@Test
	public void getAmountBuyByUser() {

		if (TestTradeMessageService.logger.isDebugEnabled()) {

			TestTradeMessageService.logger.debug("Checking if the trade message service - getAmountBuyByUser works");
		}

		Collection<AmountBuyByUser> amountBuyByUser = this.tradeMessage.getAmountBuyByUser();
		Assert.assertNotNull(amountBuyByUser);
		Assert.assertTrue(amountBuyByUser.size() == 1);
	}

	@Test
	public void getAmountSellByUser() {

		if (TestTradeMessageService.logger.isDebugEnabled()) {

			TestTradeMessageService.logger.debug("Checking if the trade message service - getAmountSellByUser works");
		}

		Collection<AmountSellByUser> amountSellByUser = this.tradeMessage.getAmountSellByUser();
		Assert.assertNotNull(amountSellByUser);
		Assert.assertTrue(amountSellByUser.size() == 1);
	}
}
