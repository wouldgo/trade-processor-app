package org.wouldgo.middleware.test;

import java.math.BigDecimal;
import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.middleware.confs.PersistenceConfiguration;
import org.wouldgo.middleware.entities.TradeMessage;
import org.wouldgo.middleware.repositories.TradeMessageRepository;
import org.wouldgo.middleware.test.conf.PropertiesConfiguration;


/**
 * Tests the {@linkplain TestTradeMessageRepositoryCustom} component.
 *
 * @author "wouldgo"
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfiguration.class, PersistenceConfiguration.class})
public class TestTradeMessageRepositoryCustom {

	@Autowired
	private TradeMessageRepository tradeMessageRepository;

	/**
	 * Setting up method that inserts the entity.
	 */
	@Before
	public void setUp() {

		TradeMessage aTradeMessage = new TradeMessage("test-value", "test-value", "test-value", "test-value", DateTime.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
		this.tradeMessageRepository.save(aTradeMessage);
	}

	@Test
	public void countNationsTest() {

		Collection<NationCounter> countNations = this.tradeMessageRepository.countNations();
		Assert.assertNotNull(countNations);
		Assert.assertTrue(countNations.size() == 1);
	}

	@Test
	public void amountSellByUser() {

		Collection<AmountSellByUser> amountSellByUser = this.tradeMessageRepository.amountSellByUser();
		Assert.assertNotNull(amountSellByUser);
		Assert.assertTrue(amountSellByUser.size() == 1);
	}

	@Test
	public void amountBoughtByUser() {

		Collection<AmountBuyByUser> amountBoughtByUser = this.tradeMessageRepository.amountBoughtByUser();
		Assert.assertNotNull(amountBoughtByUser);
		Assert.assertTrue(amountBoughtByUser.size() == 1);
	}
}
