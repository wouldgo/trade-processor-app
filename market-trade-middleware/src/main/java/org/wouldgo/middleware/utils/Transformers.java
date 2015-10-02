package org.wouldgo.middleware.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wouldgo.common.dto.TradeMessage;

/**
 * Utility class that wraps the transformers method used for conversion.
 *
 * @author "wouldgo"
 *
 */
public final class Transformers {

	private static final transient Logger logger = LoggerFactory.getLogger(Transformers.class);

	/**
	 * Converts a {@linkplain TradeMessage} in {@linkplain org.wouldgo.middleware.entities.TradeMessage}, returning the latter.
	 *
	 * @param dto the data transfer object to convert.
	 * @return the entity from the data transfer object.
	 */
	public static final org.wouldgo.middleware.entities.TradeMessage fromDataTransferObjectToEntity(TradeMessage dto) {

		if (Transformers.logger.isTraceEnabled()) {

			Transformers.logger.trace("Transformer method from dto to entity called for {}", dto);
		}
		if (dto != null) {

			org.wouldgo.middleware.entities.TradeMessage toReturn = new org.wouldgo.middleware.entities.TradeMessage(dto.getUserId(),
					dto.getCurrencyFrom(),
					dto.getCurrencyTo(),
					dto.getOriginatingCountry(),
					dto.getTimePlaced(),
					dto.getAmountSell(),
					dto.getAmountBuy(),
					dto.getRate());

			if (Transformers.logger.isTraceEnabled()) {

				Transformers.logger.trace("Entity created");
			}
			return toReturn;
		}

		throw new IllegalArgumentException("The dto can not be null");
	}

	/**
	 * Converts a {@linkplain org.wouldgo.middleware.entities.TradeMessage} in {@linkplain TradeMessage}, returning the latter.
	 *
	 * @param entity the entity object to convert.
	 * @return the data transfer object from the entity.
	 */
	public static final TradeMessage fromEntityToDataTransferObject(org.wouldgo.middleware.entities.TradeMessage entity) {

		if (Transformers.logger.isTraceEnabled()) {

			Transformers.logger.trace("Transformer method from entity to dto called for {}", entity);
		}

		if (entity != null) {

			TradeMessage toReturn = new TradeMessage(entity.getUserId(),
					entity.getCurrencyFrom(),
					entity.getCurrencyTo(),
					entity.getOriginatingCountry(),
					entity.getTimePlaced(),
					entity.getAmountSell(),
					entity.getAmountBuy(),
					entity.getRate());

			if (Transformers.logger.isTraceEnabled()) {

				Transformers.logger.trace("Data transfer object created");
			}
			return toReturn;
		}

		throw new IllegalArgumentException("The entity can not be null");
	}
}
