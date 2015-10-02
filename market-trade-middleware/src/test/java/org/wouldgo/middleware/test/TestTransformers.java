package org.wouldgo.middleware.test;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.utils.Transformers;

/**
 * Tests the {@linkplain Transformers} component.
 * @author "wouldgo"
 *
 */
public class TestTransformers {

	private static final transient Logger logger = LoggerFactory.getLogger(TestTransformers.class);
	private static TradeMessage dtoTradeMessage;
	private static org.wouldgo.middleware.entities.TradeMessage entityTradeMessage;

	@BeforeClass
	public static final void onlyOnce() {
		DateTime now = DateTime.now();
		TestTransformers.dtoTradeMessage = new TradeMessage("test-value",
				"test-value", "test-value", "test-value", now, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
		TestTransformers.entityTradeMessage = new org.wouldgo.middleware.entities.TradeMessage("test-value",
				"test-value", "test-value", "test-value", now, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
	}

	@Test
	@SuppressWarnings("static-method")
	public void fromDTOToEntity() {

		if (TestTransformers.logger.isDebugEnabled()) {

			TestTransformers.logger.debug("Converting from data transfer object to entity");
		}
		org.wouldgo.middleware.entities.TradeMessage fromDataTransferObjectToEntity = Transformers.fromDataTransferObjectToEntity(TestTransformers.dtoTradeMessage);
		Assert.assertNotNull(fromDataTransferObjectToEntity);
		Assert.assertEquals(fromDataTransferObjectToEntity.getUserId(), TestTransformers.dtoTradeMessage.getUserId());
		Assert.assertEquals(fromDataTransferObjectToEntity.getCurrencyFrom(), TestTransformers.dtoTradeMessage.getCurrencyFrom());
		Assert.assertEquals(fromDataTransferObjectToEntity.getCurrencyTo(), TestTransformers.dtoTradeMessage.getCurrencyTo());
		Assert.assertEquals(fromDataTransferObjectToEntity.getOriginatingCountry(), TestTransformers.dtoTradeMessage.getOriginatingCountry());
		Assert.assertEquals(fromDataTransferObjectToEntity.getTimePlaced(), TestTransformers.dtoTradeMessage.getTimePlaced());
		Assert.assertEquals(fromDataTransferObjectToEntity.getAmountSell(), TestTransformers.dtoTradeMessage.getAmountSell());
		Assert.assertEquals(fromDataTransferObjectToEntity.getAmountBuy(), TestTransformers.dtoTradeMessage.getAmountBuy());
		Assert.assertEquals(fromDataTransferObjectToEntity.getRate(), TestTransformers.dtoTradeMessage.getRate());
	}

	@Test
	@SuppressWarnings("static-method")
	public void fromEntityToDTO() {

		if (TestTransformers.logger.isDebugEnabled()) {

			TestTransformers.logger.debug("Converting from entity to data transfer object");
		}
		TradeMessage fromEntityToDataTransferObject = Transformers.fromEntityToDataTransferObject(TestTransformers.entityTradeMessage);
		Assert.assertNotNull(fromEntityToDataTransferObject);
		Assert.assertEquals(fromEntityToDataTransferObject.getUserId(), TestTransformers.entityTradeMessage.getUserId());
		Assert.assertEquals(fromEntityToDataTransferObject.getCurrencyFrom(), TestTransformers.entityTradeMessage.getCurrencyFrom());
		Assert.assertEquals(fromEntityToDataTransferObject.getCurrencyTo(), TestTransformers.entityTradeMessage.getCurrencyTo());
		Assert.assertEquals(fromEntityToDataTransferObject.getOriginatingCountry(), TestTransformers.entityTradeMessage.getOriginatingCountry());
		Assert.assertEquals(fromEntityToDataTransferObject.getTimePlaced(), TestTransformers.entityTradeMessage.getTimePlaced());
		Assert.assertEquals(fromEntityToDataTransferObject.getAmountSell(), TestTransformers.entityTradeMessage.getAmountSell());
		Assert.assertEquals(fromEntityToDataTransferObject.getAmountBuy(), TestTransformers.entityTradeMessage.getAmountBuy());
		Assert.assertEquals(fromEntityToDataTransferObject.getRate(), TestTransformers.entityTradeMessage.getRate());
	}
}
